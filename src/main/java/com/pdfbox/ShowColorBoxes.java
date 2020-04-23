package com.pdfbox;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

public class ShowColorBoxes {
    public static void main(String[] args) {
        File file = new File("./en_test.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page,
                    PDPageContentStream.AppendMode.APPEND, true, true);

            PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
            // 设置透明度
            graphicsState.setNonStrokingAlphaConstant(0.2f);
            graphicsState.setAlphaSourceFlag(true);

            contentStream.setGraphicsStateParameters(graphicsState);
            contentStream.addRect(200, 650, 10, 10);
            // 设置不划线颜色
            contentStream.setNonStrokingColor(Color.GREEN);


            contentStream.fill();
            System.out.println("rectangle added");
            //Closing the ContentStream object
            contentStream.close();
            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
