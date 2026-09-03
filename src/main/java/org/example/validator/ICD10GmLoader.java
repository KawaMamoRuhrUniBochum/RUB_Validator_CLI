package org.example.validator;

import org.example.parser.Parser;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;


public class ICD10GmLoader {

    private final PrePopulatedValidationSupport prePopulatedValidationSupport;

    public ICD10GmLoader(PrePopulatedValidationSupport prePopulatedValidationSupport) {
        this.prePopulatedValidationSupport = prePopulatedValidationSupport;
    }

    public void load(){

        CodeSystem icd10gm2009 = Parser.loadCodeSystem("classes/packages/icd10gm2009syst_claml_20080929.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2009");
        CodeSystem icd10gm2010 = Parser.loadCodeSystem("classes/packages/icd10gm2010syst_claml_20091019.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2010");
        CodeSystem icd10gm2011 = Parser.loadCodeSystem("classes/packages/icd10gm2011syst_claml_20100924.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2011");
        CodeSystem icd10gm2012 = Parser.loadCodeSystem("classes/packages/icd10gm2012syst_claml_20110923.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2012");
        CodeSystem icd10gm2013 = Parser.loadCodeSystem("classes/packages/icd10gm2013syst_claml_20120921.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2013");
        CodeSystem icd10gm2014 = Parser.loadCodeSystem("classes/packages/icd10gm2014syst_claml_20130920.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2014");
        CodeSystem icd10gm2015 = Parser.loadCodeSystem("classes/packages/icd10gm2015syst_claml_20140919.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2015");
        CodeSystem icd10gm2016 = Parser.loadCodeSystem("classes/packages/icd10gm2016syst_claml_20150925.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2016");
        CodeSystem icd10gm2017 = Parser.loadCodeSystem("classes/packages/icd10gm2017syst_claml_20160923.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2017");
        CodeSystem icd10gm2018 = Parser.loadCodeSystem("classes/packages/icd10gm2018syst_claml_20170922.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2018");
        CodeSystem icd10gm2019 = Parser.loadCodeSystem("classes/packages/icd10gm2019syst_claml_20180921.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2019");
        CodeSystem icd10gm2020 = Parser.loadCodeSystem("classes/packages/icd10gm2020syst_claml_20190920.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2020");
        CodeSystem icd10gm2021 = Parser.loadCodeSystem("classes/packages/icd10gm2021syst_claml_20200918_20201111.json",
                "http://fhir.de/CodeSystem/bfarm/icd-10-gm", "2021");


        prePopulatedValidationSupport.addCodeSystem(icd10gm2009);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2010);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2011);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2012);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2013);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2014);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2015);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2016);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2017);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2018);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2019);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2020);
        prePopulatedValidationSupport.addCodeSystem(icd10gm2021);
    }
}
