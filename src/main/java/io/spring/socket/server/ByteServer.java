package io.spring.socket.server;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ByteServer implements Runnable {

    public static final int PORT = 59999;
    public static final int BUFFER_SIZE = 1024;
    private static final String RESPONSE = "Good Connection!";

    @Override
    public void run() {
        try (
            ServerSocket server = new ServerSocket(PORT)
        ) {
            log.info("[서버] 소켓 서버가 준비되었습니다.");

            while (true) {
                try (
                    Socket socket = server.accept();
                    BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                    BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())
                ) {
                    log.info("[서버] 클라이언트가 연결되어 소켓이 생성되었습니다.");
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int read = in.read(buffer);

                    if (read != -1) {
                        String message = new String(buffer, 0, read);
                        log.info("[서버] 클라이언트로부터 데이터를 요청받았습니다. 요청메시지 = {}", message);

                        out.write(RESPONSE.getBytes());
                        out.flush();
                        log.info("[서버] 클라이언트에게 데이터를 응답했습니다. 응답메시지 = {}", RESPONSE);
                    }
                } catch (IOException e) {
                    log.error("[서버] 클라이언트 처리 중 오류가 발생했습니다.", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}