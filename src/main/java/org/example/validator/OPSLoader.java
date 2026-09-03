package org.example.validator;

import org.example.parser.Parser;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;

public class OPSLoader {

    private final PrePopulatedValidationSupport prePopulatedValidationSupport;

    public OPSLoader(PrePopulatedValidationSupport prePopulatedValidationSupport) {
        this.prePopulatedValidationSupport = prePopulatedValidationSupport;
    }

    public void load(){
        CodeSystem ops2010 = Parser.loadCodeSystem("classes/packages/ops2010syst_claml_20091028.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2010");
        prePopulatedValidationSupport.addCodeSystem(ops2010);
        CodeSystem ops2011 = Parser.loadCodeSystem("classes/packages/ops2011syst_claml_20101021.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2011");
        prePopulatedValidationSupport.addCodeSystem(ops2011);
        CodeSystem ops2012 = Parser.loadCodeSystem("classes/packages/ops2012syst_claml_20111103.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2012");
        prePopulatedValidationSupport.addCodeSystem(ops2012);
        CodeSystem ops2013 = Parser.loadCodeSystem("classes/packages/ops2013syst_claml_20121012.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2013");
        prePopulatedValidationSupport.addCodeSystem(ops2013);
        CodeSystem ops2014 = Parser.loadCodeSystem("classes/packages/ops2014syst_claml_20131104.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2014");
        prePopulatedValidationSupport.addCodeSystem(ops2014);
        CodeSystem ops2015 = Parser.loadCodeSystem("classes/packages/ops2015syst_claml_20141017.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2015");
        prePopulatedValidationSupport.addCodeSystem(ops2015);
        CodeSystem ops2016 = Parser.loadCodeSystem("classes/packages/ops2016syst_claml_20151016.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2016");
        prePopulatedValidationSupport.addCodeSystem(ops2016);
        CodeSystem ops2017 = Parser.loadCodeSystem("classes/packages/ops2017syst_claml_20161019.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2017");
        prePopulatedValidationSupport.addCodeSystem(ops2017);
        CodeSystem ops2018 = Parser.loadCodeSystem("classes/packages/ops2018syst_claml_20171018.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2018");
        prePopulatedValidationSupport.addCodeSystem(ops2018);
        CodeSystem ops2019 = Parser.loadCodeSystem("classes/packages/ops2019syst_claml_20181019.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2019");
        prePopulatedValidationSupport.addCodeSystem(ops2019);
        CodeSystem ops2020 = Parser.loadCodeSystem("classes/packages/ops2020syst_claml_20191018.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2020");
        prePopulatedValidationSupport.addCodeSystem(ops2020);
        CodeSystem ops2021 = Parser.loadCodeSystem("classes/packages/ops2021syst_claml_20201016.json",
                "http://fhir.de/CodeSystem/bfarm/ops", "2021");
        prePopulatedValidationSupport.addCodeSystem(ops2021);
    }
}
