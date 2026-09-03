package org.example.validator;

import java.util.Set;

public record ResourceStatistics(
        Long counter,
        double percentage,
        Set<String> missingPaths
) {
}
