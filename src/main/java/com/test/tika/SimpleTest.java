package com.test.tika;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

public class SimpleTest {
    public static void main(String[] args) {
        //二进制文件路径
        String fileName = "/Users/qinwenjing/Documents/tika.txt";

        //二进制文件
        File file1 = new File(fileName);

        //通过tika获取文件内容
        Tika tika = new Tika();
        String filecontent = null;

        {
            try {
                filecontent = tika.parseToString(file1);
                System.out.println("Extracted Content: " + filecontent);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (TikaException e1) {
                e1.printStackTrace();
            }
        }

        //打印文件内容

        //要转换到的文件
        File file2 = new File("/Users/qinwenjing/Documents/tika.txt");

        //文件不存在就新建
        if (!file2.exists())
        {
            try {
                file2.createNewFile();
                //把二进制文件内容写入doc文件
                FileWriter fw = new FileWriter(file2.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(filecontent);
                bw.close();

                System.out.println("Done");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}
