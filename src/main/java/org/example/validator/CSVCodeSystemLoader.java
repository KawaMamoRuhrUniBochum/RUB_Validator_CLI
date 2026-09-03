package org.example.validator;

import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.Enumerations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CSVCodeSystemLoader {
    public static void loadAlphaIdCsv(String csvFilePath,String url, String version, PrePopulatedValidationSupport populatedValidationSupport){
        CodeSystem alphaIds = new CodeSystem();
        alphaIds.setUrl(url);
        alphaIds.setVersion(version);
        alphaIds.setStatus(Enumerations.PublicationStatus.ACTIVE);
        alphaIds.setContent(CodeSystem.CodeSystemContentMode.COMPLETE);

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8)
            );

            String line;
            while ((line= reader.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if(parts.length>=3){
                    String code = parts[1].trim();
                    String display = parts[2].trim();

                    CodeSystem.ConceptDefinitionComponent concept = alphaIds.addConcept();
                    concept.setCode(code);
                    concept.setDisplay(display);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        populatedValidationSupport.addCodeSystem(alphaIds);
    }
}
