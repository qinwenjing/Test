package com.pdfbox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfToImage {
    public static void main(String[] args) {
        File file = new File("./sample.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            // 名为PDFRenderer的类将PDF文档呈现为AWT BufferedImage 。
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImage(0);
            ImageIO.write(image, "JPEG",new File("./immm.jpg"));
            System.out.println("Image created");
            //Closing the document
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
