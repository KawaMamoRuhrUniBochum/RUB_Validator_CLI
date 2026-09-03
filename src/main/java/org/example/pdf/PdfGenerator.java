package org.example.pdf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openpdf.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class PdfGenerator {

    public byte[] generateReport(Report report) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            HtmlTemplateBuilder htmlTemplateBuilder = new HtmlTemplateBuilder();
            String html = htmlTemplateBuilder.generateHtml(report);

            // 1. Sanitize raw HTML string to strict XHTML using Jsoup
            String xhtmlContent = convertToXhtml(html);

            // 2. Parse clean XHTML into W3C Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(xhtmlContent.getBytes(StandardCharsets.UTF_8)));

            // 3. Render PDF using OpenPDF / Flying Saucer
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(doc, null);
            renderer.layout();
            renderer.createPDF(os);
            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error rendering PDF", e);
        }
    }

    private String convertToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        document.outputSettings().charset("UTF-8");
        return document.html();
    }
}