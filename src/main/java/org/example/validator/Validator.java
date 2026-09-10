package org.example.validator;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
import jakarta.annotation.PostConstruct;
import org.hl7.fhir.common.hapi.validation.support.*;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;


public class Validator {

    private FhirValidator fhirValidator;
    private final FhirContext fhirContext;
    private ValidationSupportChain validationSupportChain;
    private String terminologyServerUrl;

    public Validator(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @PostConstruct
    public void init(){

        PrePopulatedValidationSupport populatedValidationSupport = new PrePopulatedValidationSupport(fhirContext);

        ICD10GmLoader icd10GmLoader = new ICD10GmLoader(populatedValidationSupport);
        OPSLoader opsLoader = new OPSLoader(populatedValidationSupport);
        AlphaIdLoader alphaIdLoader = new AlphaIdLoader(populatedValidationSupport);

        icd10GmLoader.load();
        opsLoader.load();
        alphaIdLoader.load();

        NpmPackageValidationSupport npmSupport = new NpmPackageValidationSupport(fhirContext);
        NpmPackageLoader npmPackageLoader = new NpmPackageLoader();
        npmPackageLoader.loadAllTgzPackagesFromClasspath(npmSupport);

        ValidationSupportChain chain = getSupportChain(npmSupport, populatedValidationSupport);
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(chain);

        fhirValidator = fhirContext.newValidator();
        fhirValidator.registerValidatorModule(instanceValidator);

    }

    private ValidationSupportChain getSupportChain(NpmPackageValidationSupport npmSupport, PrePopulatedValidationSupport populatedValidationSupport) {
        RemoteTerminologyServiceValidationSupport remoteTerminologyServiceValidationSupport = new RemoteTerminologyServiceValidationSupport(fhirContext);
        remoteTerminologyServiceValidationSupport.setBaseUrl(terminologyServerUrl);
        validationSupportChain = new ValidationSupportChain(
                npmSupport,
                populatedValidationSupport,
                new DefaultProfileValidationSupport(fhirContext),
                new CommonCodeSystemsTerminologyService(fhirContext),
                new InMemoryTerminologyServerValidationSupport(fhirContext),
                new SnapshotGeneratingValidationSupport(fhirContext),
                remoteTerminologyServiceValidationSupport);
        return validationSupportChain;
    }

    public ValidationResult validate(IBaseResource resource){
        return fhirValidator.validateWithResult(resource);
    }

    public IValidationSupport getValidationSupportChain() {
        return validationSupportChain;
    }

    public String getTerminologyServerUrl() {
        return terminologyServerUrl;
    }

    public void setTerminologyServerUrl(String terminologyServerUrl) {
        this.terminologyServerUrl = terminologyServerUrl;
    }
}
