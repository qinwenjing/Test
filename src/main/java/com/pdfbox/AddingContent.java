package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;

public class AddingContent {
    public static void main(String[] args) {
        File file = new File("./doc_attributes.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            // contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.setFont(PDType1Font.COURIER, 12);
            // 设置文本前导
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 725);
            String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
            String text2 = "as we want like this using the ShowText()  method of the ContentStream class";
            String text3 = "as3333 we want like this using the ShowText()  method of the ContentStream class";
            String text4 = "我是中文dddd";
            contentStream.showText(text1);
            contentStream.newLine();
            contentStream.showText(text2);
            contentStream.newLine();
            contentStream.showText(text3);
            contentStream.newLine();
            contentStream.showText(text4);
            System.out.println("Content added");
            contentStream.close();
            document.save("./doc_attributes.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
