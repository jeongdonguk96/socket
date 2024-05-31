package io.spring.socket.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ByteClient implements Runnable {

    private static final String REQUEST = "Nice to meet you!";
    public static final int BUFFER_SIZE = 1024;

    @Override
    public void run() {
        try (
                Socket socket = new Socket("127.0.0.1", 59999);
                BufferedOutputStream out = new BufferedOutputStream (socket.getOutputStream());
                BufferedInputStream in = new BufferedInputStream (socket.getInputStream())) {
            while (true) {
                byte[] bytes = REQUEST.getBytes();
                out.write(bytes);
                out.flush();
                log.info("[클라이언트] 서버에게 데이터를 전송했습니다. message = {}", new String(bytes));

                byte[] responseBuffer = new byte[BUFFER_SIZE];
                int read = in.read(responseBuffer);
                String response = new String(responseBuffer, 0, read);
                log.info("[클라이언트] 서버로부터 응답 데이터를 받았습니다. response = {}", response);

                Thread.sleep(10);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
