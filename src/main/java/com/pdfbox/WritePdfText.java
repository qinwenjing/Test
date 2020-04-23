package com.pdfbox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.itextpdf.io.font.otf.OpenTypeScript;

public class WritePdfText {
    public static void main(String[] args) {
        File file = new File("./writetest.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDFont pdFont = getDefaultFont(document);

            PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0),
                    PDPageContentStream.AppendMode.APPEND, false);
            contentStream.beginText();
            contentStream.newLineAtOffset(400, 50);
            // contentStream.newLineAtOffset(417, page.getCropBox().getHeight() - 1074 - 62);

            contentStream.setFont(pdFont, 12);
            contentStream.showText("菏泽建筑第四工程ttt局有限公司");
            contentStream.endText();
            contentStream.close();

            PDPageContentStream contentStream2 = new PDPageContentStream(document, document.getPage(0),
                    PDPageContentStream.AppendMode.APPEND, false);
            contentStream2.beginText();
            contentStream2.newLineAtOffset(450, 65);
            // contentStream.newLineAtOffset(417, page.getCropBox().getHeight() - 1074 - 62);

            contentStream2.setFont(pdFont, 12);
            contentStream2.showText("1234567890");
            contentStream2.endText();
            contentStream2.close();

            PDPageContentStream contentStream3 = new PDPageContentStream(document, document.getPage(0),
                    PDPageContentStream.AppendMode.APPEND, false);
            contentStream3.beginText();
            contentStream3.newLineAtOffset(450, 75);
            // contentStream.newLineAtOffset(417, page.getCropBox().getHeight() - 1074 - 62);

            contentStream3.setFont(pdFont, 12);
            contentStream3.showText("qwertyuiop");
            contentStream3.endText();
            contentStream3.close();

            PDPageContentStream contentStream4 = new PDPageContentStream(document, document.getPage(0),
                    PDPageContentStream.AppendMode.APPEND, false);
            contentStream4.beginText();
            contentStream4.newLineAtOffset(450, 85);
            // contentStream.newLineAtOffset(417, page.getCropBox().getHeight() - 1074 - 62);

            contentStream4.setFont(pdFont, 12);
            contentStream4.showText("QWERTYUIOP");
            contentStream4.endText();
            contentStream4.close();

            System.out.println("写入汉字");

            document.save("./writetest.pdf");
            document.close();

            String ss = null;

            System.out.println("ss ？ ：" + (ss == null));

            System.out.println("document close后还是空嘛？ ：" + (document == null));

            // 这时候直接打开创建的文件会发现打不开
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PDFont getDefaultFont(PDDocument doc) {
        String path = "/unicode.ttf";
        PDFont font = null;
        try (InputStream input = OpenTypeScript.class.getResourceAsStream(path)) {
            font = PDType0Font.load(doc, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }
}
