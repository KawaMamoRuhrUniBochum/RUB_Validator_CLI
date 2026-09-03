package org.example;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;
import org.example.context.FhirCtx;
import org.example.pdf.PdfGenerator;
import org.example.pdf.Report;
import org.example.validator.Validator;
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void main(String[] args) {

        FhirContext fhirContext = FhirCtx.getFhirContext();
        IParser iParser = fhirContext.newJsonParser();
        Validator validator = new Validator(fhirContext);
        validator.init();
        try {
            IBaseResource iBaseResource = iParser.parseResource(Files.newInputStream(Path.of(args[0])));
            long startTime = System.currentTimeMillis();
            ValidationResult validationResult = validator.validate(iBaseResource);

            long endTime = System.currentTimeMillis();
            long validationDurationMs = endTime - startTime;
            Report report = new Report(validationResult, validationDurationMs);
            PdfGenerator pdfGenerator = new PdfGenerator();
            byte[] generateReport = pdfGenerator.generateReport(report);

            try (FileOutputStream fileOutputStream = new FileOutputStream("report.pdf")) {
                fileOutputStream.write(generateReport);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
