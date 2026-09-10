package org.example.validator;

import org.hl7.fhir.common.hapi.validation.support.NpmPackageValidationSupport;
import org.hl7.fhir.r4.model.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class NpmPackageLoader {
    public void loadAllTgzPackagesFromClasspath(NpmPackageValidationSupport npmSupport) {

        try {
            npmSupport.loadPackageFromClasspath("packages/basicPackages/hl7.fhir.uv.xver-r5.r4-0.1.0.tgz");
            npmSupport.loadPackageFromClasspath("packages/basicPackages/hl7.fhir.r4.core-4.0.1.tgz");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path directory = Paths.get("classes/packages");
        try (Stream<Path> list = Files.list(directory)) {

            list.forEach(file -> {
                try {
                    String fileName = file.getFileName().toString();
                    int lastIndex = fileName.lastIndexOf(".");

                    String extension;
                    if(lastIndex != -1)
                        extension = fileName.substring(lastIndex);
                    else extension = null;

                    if(Objects.nonNull(extension) && extension.equals(".tgz")){
                        npmSupport.loadPackageFromClasspath("packages/"+fileName);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
