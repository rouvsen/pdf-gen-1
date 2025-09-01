package com.rouvsen.pdfgen1.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.font.FontProvider;
import com.rouvsen.pdfgen1.dao.entity.StatementEntity;
import com.rouvsen.pdfgen1.model.dto.StatementRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.itextpdf.kernel.geom.PageSize.A4;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileGenerator {

    private final TemplateEngine templateEngine;

    public byte[] generateStatementPdf(List<StatementEntity> statements, StatementRequestDto requestDto) {
        Map<String, Object> attributes = Map.of(
                "statementList", statements,
                "beginDate", requestDto.getBeginDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                "endDate", requestDto.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                "currDate", new Date()
        );

        Context context = new Context(LocaleContextHolder.getLocale(), attributes);

        String html = templateEngine.process("pages/pdf/accountStatementPdf.html", context);

        return generatePdfAsLandscape(html);
    }

    @SneakyThrows
    private byte[] generatePdfAsLandscape(String html) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(output,
                     new WriterProperties()
                             .setFullCompressionMode(true)
                             .setCompressionLevel(5)
             );
             PdfDocument pdfDocument = new PdfDocument(writer)) {

            pdfDocument.setFlushUnusedObjects(true);
            pdfDocument.setDefaultPageSize(A4.rotate());

//            FontProvider fontProvider = new FontProvider();
//            fontProvider.addStandardPdfFonts();
//            fontProvider.addSystemFonts();

            ConverterProperties props = new ConverterProperties()
//                    .setFontProvider(fontProvider)
                    .setCharset("UTF-8");

            HtmlConverter.convertToPdf(html, pdfDocument, props);

            return output.toByteArray();
        }
    }

    @SneakyThrows
    private byte[] generatePdfAsLandscape2(String html) {
        WriterProperties wp = new WriterProperties()
                .setFullCompressionMode(true)
                .setCompressionLevel(6);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(out, wp);
             PdfDocument pdf = new PdfDocument(writer)) {

            pdf.setDefaultPageSize(PageSize.A4.rotate());
            pdf.setFlushUnusedObjects(true);

//            FontProvider fontProvider = new FontProvider();
//            fontProvider.addFont("/app/fonts/NotoSans-Regular.ttf"); // explicit fonts only

            ConverterProperties props = new ConverterProperties();
//            props.setFontProvider(fontProvider);
            props.setCharset("UTF-8");
//            props.setBaseUri("file:/app/templates/"); // if your HTML references images/css with relative paths

            try (InputStream htmlStream = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8))) {
                HtmlConverter.convertToPdf(htmlStream, pdf, props);
            }

            return out.toByteArray();
        }
    }
}
