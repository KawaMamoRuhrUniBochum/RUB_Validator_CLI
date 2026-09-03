package org.example.context;

import ca.uhn.fhir.context.FhirContext;

import java.util.Objects;


public class FhirCtx {

    private static FhirContext fhirContext;

    public static FhirContext getFhirContext(){
        if(Objects.isNull(fhirContext))
            fhirContext = FhirContext.forR4();
        return fhirContext;
    }
}
