package sockettest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程的测试
 * 每一个客户端连接进来后，服务端都会单独起个一线程，与客户端进行数据交互
 * 但是实际生产环境中，这种写法对于客户端连接少的的情况下是没有问题，但是如果有大批量的客户端连接进行，那我们服务端估计就要歇菜了。
 */
public class ServerSocketTest3 {
    public static void main(String[] args) {
        try {
            // 初始化服务端socket并且绑定9999端口
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader bufferedReader = null;
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(socket
                                    .getInputStream(),
                                    "UTF-8"));
                            String str;
                            while ((str = bufferedReader.readLine()) != null) {
                                System.out.println(Thread.currentThread().getName() + "____" + str);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
