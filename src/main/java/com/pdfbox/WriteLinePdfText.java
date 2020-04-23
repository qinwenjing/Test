package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class WriteLinePdfText {
    public static void main(String[] args) {
        File file = new File("./writetest.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDPage page = document.getPage(0);
            // 如果resetContext位置用false，执行完成后无法打开
            PDPageContentStream contentStream =
                    new PDPageContentStream(document, document.getPage(0), PDPageContentStream.AppendMode.APPEND,
                            false);
            System.out.println(page.getCropBox().getHeight());
            contentStream.setStrokingColor(21, 27, 30);
            contentStream.moveTo(39, page.getCropBox().getHeight() - 720 );
            contentStream.lineTo(180, page.getCropBox().getHeight()-824);
            contentStream.stroke();

            contentStream.setStrokingColor(121, 127, 30);
            contentStream.moveTo(1040, page.getCropBox().getHeight() - 1012);
            contentStream.lineTo(1040, page.getCropBox().getHeight()-1074);
            contentStream.stroke();

            contentStream.moveTo(20, page.getCropBox().getHeight() - 1012);
            contentStream.lineTo(40, page.getCropBox().getHeight()-1074);
            contentStream.stroke();

         /*   contentStream.moveTo(50, 40);
            contentStream.lineTo(300, 200);
            contentStream.stroke();
            contentStream.setStrokingColor(66, 177, 230);
            contentStream.drawLine(100, 100, 200, 100);
            contentStream.drawLine(20, 20, 800, 800);*/
            contentStream.close();
            document.save(file);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
