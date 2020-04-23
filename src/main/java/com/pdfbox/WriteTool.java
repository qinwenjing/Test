package com.pdfbox;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.util.CollectionUtils;

public class WriteTool {
    /**
     * @param doc
     * @param page     从1开始算
     * @param elements 要写的元素们,每个 element 代表的内容不超过一行
     */
    public void writePDF(PDDocument doc, int page, List<PDFWriteElement> elements, PDFont font) throws IOException {
        if (doc == null || page < 1 || CollectionUtils.isEmpty(elements) || doc.getNumberOfPages() < page) {
            return;
        }
        PDPage p = doc.getPage(page - 1);
        for (PDFWriteElement element : elements) {
            writePDF(doc, p, element, font);
        }
    }

    private void writePDF(PDDocument doc, PDPage p, PDFWriteElement element, PDFont font) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(doc, p,
                PDPageContentStream.AppendMode.APPEND, false)) {
            contentStream.beginText();
            contentStream.setFont(font, element.getFontSize());
            contentStream.newLineAtOffset(element.getLeft(),
                    p.getCropBox().getHeight() - element.getTop() - element.getHeight());
            contentStream.showText(element.getContent());
            contentStream.endText();
        }
    }

}
