package org.example.pdf;

public class HtmlTemplateBuilder {

    public String generateHtml(Report report){
        String style = setStyle();
        StringBuilder html = new StringBuilder();

        html.append("""
                    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
                       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
                    <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                        <title>FHIR-Validierungs- und Verarbeitungsbericht</title>
                    """).append(style).append("""
                    </head>
                    <body>
                    
                        <!-- Header Section -->
                        <div class="header">
                            <div class="header-title">
                                <h1>FHIR-Validierungs- und Verarbeitungsbericht</h1>
                                <p>Erstellt am""").append(" ").append(escapeXml(report.getGenerationTime())).append("""
                        </p>
                    </div>
                    <!-- Overall Result Status -->
                    <div class="status-banner status-""").append(report.getStatus()).append("""
                            ">
                            Result:""").append(" ").append(escapeXml(report.getValidationStatus())).append("""
                        </div>
                    
                        <!-- Summary Grid -->
                        <div class="grid">
                            <div class="grid-col">
                                <div class="card">
                                    <h3>DETAILS ZUR AUSFÜHRUNG</h3>
                                    <ul class="meta-list">
                                        <li><span class="meta-label">Validierungszeit:</span> <span class="meta-value">""").append(escapeXml(report.getValidationTime())).append("""
                                        </span></li>
                                        <li><span class="meta-label">Bearbeitungszeit:</span> <span class="meta-value">""").append(escapeXml(report.getProcessingTime())).append("""
                                        </span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="grid-col">
                                <div class="card">
                                    <h3>Zusammenfassung der Validierung</h3>
                                    <ul class="meta-list">
                                        <li><span class="meta-label">Errors:</span> <span class="meta-value badge badge-error">""").append(report.getErrorCount()).append("""
                                        </span></li>
                                        <li><span class="meta-label">Warnings:</span> <span class="meta-value badge badge-warning">""").append(report.getWarningCount()).append("""
                                        </span></li>
                                        <li><span class="meta-label">Information:</span> <span class="meta-value badge badge-info">""").append(report.getInfoCount()).append("""
                                        </span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    
                        <!-- Validated Resources Count -->
                        <h2>Validierte Ressourcen</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Ressourcentyp</th>
                                    <th>Count</th>
                                    <th>Validierungsstatus</th>
                                    <th>MustSupport %</th>
                                </tr>
                            </thead>
                            <tbody>
                    """);


        html.append("""
                            </tbody>
                        </table>
                    
                        <!-- Applied Transformations / Operations -->
                        <h2>Angewandte Rohstoffaufbereitung</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Ressource</th>
                                    <th>Durchgeführte Maßnahme</th>
                                    <th>Target Field / Erweiterung</th>
                                </tr>
                            </thead>
                            <tbody>
                    """);

        html.append("""
                            </tbody>
                        </table>
                    
                        <!-- Validation Messages List -->
                        <h2>Validierungsmeldungen</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Type</th>
                                    <th>Ort / Pfad</th>
                                    <th>Nachrichtendetails</th>
                                </tr>
                            </thead>
                            <tbody>
                    """);

        // Loop through validation messages
        if (report.getValidationMessages() != null) {
            for (var msg : report.getValidationMessages()) {
                String badgeClass = getBadgeClass(msg.getType());
                html.append("<tr>")
                        .append("<td><span class=\"badge ").append(badgeClass).append("\">").append(escapeXml(msg.getType())).append("</span></td>")
                        .append("<td><span class=\"code-snippet\">").append(escapeXml(msg.getLocation())).append("</span></td>")
                        .append("<td>").append(escapeXml(msg.getDetails())).append("</td>")
                        .append("</tr>");
            }

        }

        html.append("""
                            </tbody>
                        </table>
                    
                    </body>
                    </html>
                    """);

        return html.toString();
    }


    private String getBadgeClass(String type) {
        if (type == null) return "badge-info";
        return switch (type.toLowerCase()) {
            case "error" -> "badge-error";
            case "warning" -> "badge-warning";
            default -> "badge-info";
        };
    }

    private String escapeXml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private String getCoverageBadgeClass(double percentage) {
        if (percentage >= 80.0) {
            return "badge badge-success";
        } else if (percentage >= 50.0) {
            return "badge badge-warning";
        } else {
            return "badge badge-error";
        }
    }

    private String setStyle() {
        return """
                    <style type="text/css">
                            @page {
                                size: A4;
                                margin: 20mm;
                            }
                    
                            body {
                                font-family: Arial, Helvetica, sans-serif;
                                color: #2d3748;
                                background-color: #ffffff;
                                margin: 0;
                                padding: 0;
                                font-size: 13px;
                                line-height: 1.5;
                            }
                    
                            /* Header Section */
                            .header {
                                border-bottom: 2px solid #e2e8f0;
                                padding-bottom: 15px;
                                margin-bottom: 20px;
                            }
                    
                            .header-title h1 {
                                margin: 0;
                                font-size: 22px;
                                color: #1a202c;
                            }
                    
                            .header-title p {
                                margin: 4px 0 0 0;
                                color: #718096;
                                font-size: 12px;
                            }
                    
                            .logo {
                                font-weight: bold;
                                font-size: 18px;
                                color: #2b6cb0;
                                background: #ebf8ff;
                                padding: 8px 16px;
                                border-radius: 6px;
                                border: 1px solid #bee3f8;
                            }
                    
                            /* Status Banner */
                            .status-banner {
                                padding: 12px 16px;
                                border-radius: 6px;
                                font-weight: bold;
                                font-size: 14px;
                                margin-bottom: 20px;
                            }
                    
                            .status-passed {
                                background-color: #f0fff4;
                                color: #22543d;
                                border: 1px solid #c6f6d5;
                            }
                    
                            .status-failed {
                                background-color: #fff5f5;
                                color: #742a2a;
                                border: 1px solid #fed7d7;
                            }
                    
                            /* Summary Cards Grid */
                            .grid {
                                display: table;
                                width: 100%;
                                margin-bottom: 20px;
                            }
                    
                            .grid-col {
                                display: table-cell;
                                width: 50%;
                                vertical-align: top;
                            }
                    
                            .grid-col:first-child {
                                padding-right: 10px;
                            }
                    
                            .grid-col:last-child {
                                padding-left: 10px;
                            }
                    
                            .card {
                                border: 1px solid #e2e8f0;
                                border-radius: 6px;
                                padding: 12px 16px;
                                background-color: #f7fafc;
                            }
                    
                            .card h3 {
                                margin: 0 0 10px 0;
                                font-size: 13px;
                                text-transform: uppercase;
                                letter-spacing: 0.5px;
                                color: #4a5568;
                            }
                    
                            /* Metadata & Stats List */
                            .meta-list {
                                list-style: none;
                                padding: 0;
                                margin: 0;
                            }
                    
                            .meta-list li {
                                padding: 4px 0;
                                border-bottom: 1px dashed #e2e8f0;
                            }
                    
                            .meta-list li:last-child {
                                border-bottom: none;
                            }
                    
                            .meta-label {
                                color: #718096;
                            }
                    
                            .meta-value {
                                font-weight: 600;
                            }
                    
                            /* Tables */
                            h2 {
                                font-size: 16px;
                                color: #2d3748;
                                margin-top: 25px;
                                margin-bottom: 10px;
                                border-bottom: 1px solid #edf2f7;
                                padding-bottom: 5px;
                            }
                    
                            table {
                                width: 100%;
                                border-collapse: collapse;
                                margin-bottom: 20px;
                            }
                    
                            th, td {
                                text-align: left;
                                padding: 8px 12px;
                                border-bottom: 1px solid #e2e8f0;
                            }
                    
                            th {
                                background-color: #edf2f7;
                                color: #4a5568;
                                font-size: 11px;
                                text-transform: uppercase;
                                letter-spacing: 0.5px;
                            }
                    
                            /* Badges */
                            .badge {
                                display: inline-block;
                                padding: 2px 8px;
                                border-radius: 12px;
                                font-size: 11px;
                                font-weight: 600;
                            }
                    
                            .badge-error { background-color: #fed7d7; color: #9b2c2c; }
                            .badge-warning { background-color: #feebc8; color: #9c4221; }
                            .badge-info { background-color: #ebf8ff; color: #2c5282; }
                    
                            .code-snippet {
                                font-family: Consolas, monospace;
                                font-size: 11px;
                                background-color: #edf2f7;
                                padding: 2px 6px;
                                border-radius: 4px;
                            }
                        </style>
                    """;
    }


}
