package internetTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 30000);
            // 启动一个线程负责处理服务端的返回的数据
            new Thread(new ClientThread(s)).start();

            PrintStream ps = new PrintStream(s.getOutputStream());
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while((line = br.readLine()) != null){
                // 将用户键盘输入的数据写入到socket对应的输出流中
                // 必须是println然后才可以看到正常的结果，如果是print, 则不会看到正常的打印结果。原因是如果没有换行，相当于没有把输出流刷出，服务器不会认为是一行数据的结束
                ps.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
