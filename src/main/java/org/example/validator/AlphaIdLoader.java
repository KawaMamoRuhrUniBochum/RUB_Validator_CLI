package org.example.validator;

import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;

public class AlphaIdLoader {

    private final PrePopulatedValidationSupport populatedValidationSupport;

    public AlphaIdLoader(PrePopulatedValidationSupport populatedValidationSupport) {
        this.populatedValidationSupport = populatedValidationSupport;
    }

    public void load(){
        CSVCodeSystemLoader.loadAlphaIdCsv(
                "classes/packages/icd102020_alphaid_edvtxt_20191004.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2020", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2026_alphaidse_edvtxt_20250926.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2026", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2025_alphaidse_edvtxt_20240927.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2025", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2018_alphaid_edvtxt_20171004.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2018", populatedValidationSupport);

        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2019_alphaid_edvtxt_20181005.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2019", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd102020_alphaid_edvtxt_20191004.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2020", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2021_alphaid_edvtxt_20201002.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2021", populatedValidationSupport);

        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2022_alphaidse_edvtxt_20211001_20220114.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2022", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2023_alphaidse_edvtxt_20220930.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2023", populatedValidationSupport);
        CSVCodeSystemLoader.loadAlphaIdCsv("classes/packages/icd10gm2024_alphaidse_edvtxt_20230929.txt",
                "http://fhir.de/CodeSystem/bfarm/alpha-id", "2024", populatedValidationSupport);
    }
}
