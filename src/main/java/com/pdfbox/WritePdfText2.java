package com.pdfbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.itextpdf.io.font.otf.OpenTypeScript;

public class WritePdfText2 {
    public static void main(String[] args) {
        File file = new File("./writetest.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDFont pdFont = getDefaultFont(document);




            WriteTool writeTool = new WriteTool();
            document.addPage(new PDPage());


            List<PDFWriteElement> elements = new ArrayList<>();
            PDFWriteElement element = new PDFWriteElement();
            element.setContent("山东菏泽");
            element.setFontSize(12);
            element.setHeight(20);
            element.setWidth(20);
            element.setLeft(50);
            element.setTop(50);
            elements.add(element);
            writeTool.writePDF(document, 1, elements, pdFont);
            document.save(file);


            List<PDFWriteElement> elements2 = new ArrayList<>();
            PDFWriteElement element2 = new PDFWriteElement();
            element2.setContent("河南洛阳");
            element2.setFontSize(12);
            element2.setHeight(20);
            element2.setWidth(20);
            element2.setLeft(100);
            element2.setTop(100);
            elements2.add(element2);
            writeTool.writePDF(document, 2, elements2, pdFont);
            document.save(file);

            System.out.println("写入汉字");

            document.save("./writetest.pdf");
            document.close();

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

//        // 写不出来
//        String path = "/Users/qinwenjing/Documents/Projects/Test/src/main/resources/unicode.ttf";
//        PDFont font = null;
//        try {
//            font = PDType0Font.load(doc, new FileInputStream(path), false);
//        } catch (IOException e) {
//
//        }
//
//
//
//        // IOException: The TrueType font does not contain a 'cmap' table
//
//        String path = "/unicode.ttf";
//        PDFont font = null;
//        InputStream input = OpenTypeScript.class.getResourceAsStream(path);
//        try {
//            font = PDType0Font.load(doc, input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return font;

    }
}
