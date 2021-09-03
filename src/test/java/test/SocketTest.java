package test;

import leetcode.TLog;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTest {
    public static class TcpClient {
        TcpClient(){
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("127.0.0.1", 1001), 3000);
                socket.getOutputStream().write(123);
                socket.close();
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            TLog.e(1);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(1);
            super.finalize();
        }
    }
    @Test
    public void test() throws IOException {
    new Thread(()->{
        {

            new TcpClient();
        }
        TLog.e(2);

    }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        socket.close();
    }
}
