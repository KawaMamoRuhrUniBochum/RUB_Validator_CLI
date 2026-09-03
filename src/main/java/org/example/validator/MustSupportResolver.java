package org.example.validator;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.fhirpath.IFhirPath;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MustSupportResolver {
    private final FhirContext fhirContext;
    private final Validator validator;
    private final IFhirPath fhirPath;

    public record CoverageResult(
            String canonicalUrl,
            int totalMustSupport,
            int populatedMustSupport,
            double percentage,
            Set<String> missingPaths
    ) {}

    public MustSupportResolver(FhirContext fhirContext, Validator validator) {
        this.fhirContext = fhirContext;
        this.validator = validator;
        this.fhirPath = fhirContext.newFhirPath();
    }

    public Set<String> getMustSupportPaths(String canonicalUrl) {

        StructureDefinition sd = validator.getValidationSupportChain()
                .fetchResource(StructureDefinition.class, canonicalUrl);

        if (sd == null) {
            return Set.of();
        }

        // Prefer snapshot element definitions over differential
        List<ElementDefinition> elements = sd.hasSnapshot() ?
                sd.getSnapshot().getElement() :
                sd.getDifferential().getElement();

        return elements.stream()
                .filter(ElementDefinition::getMustSupport)
                .map(ElementDefinition::getPath)
                .collect(Collectors.toSet());
    }

    public CoverageResult calculateCoverage(IBaseResource resource, String canonicalUrl) {
        Set<String> mustSupportPaths = getMustSupportPaths(canonicalUrl);

        if (mustSupportPaths.isEmpty()) {
            return new CoverageResult(canonicalUrl, 0, 0, 100.0, Set.of());
        }

        String resourceType = fhirContext.getResourceDefinition(resource).getName();
        int matchCount = 0;
        Set<String> missingPaths = new HashSet<>();

        for (String fullPath : mustSupportPaths) {
            // Trim resource prefix: "Patient.name.family" -> "name.family"
            String relativePath = fullPath.startsWith(resourceType + ".") ?
                    fullPath.substring(resourceType.length() + 1) : fullPath;

            if (relativePath.equalsIgnoreCase(resourceType)) {
                continue; // Skip root node
            }

            // Clean slice names if present in path (e.g. extension:gkv -> extension)
            String cleanPath = relativePath.replaceAll(":[a-zA-Z0-9_-]+", "");

            boolean exists = fhirPath.evaluateFirst(resource, cleanPath + ".exists()", BooleanType.class)
                    .map(BooleanType::getValue)
                    .orElse(false);

            if (exists) {
                matchCount++;
            } else {
                missingPaths.add(fullPath);
            }
        }

        double percentage = ((double) matchCount / mustSupportPaths.size()) * 100.0;
        double roundedPercentage = Math.round(percentage * 100.0) / 100.0;

        return new CoverageResult(canonicalUrl, mustSupportPaths.size(), matchCount, roundedPercentage, missingPaths);
    }
}
