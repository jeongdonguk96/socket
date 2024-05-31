package io.spring.socket.server;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class TextServer implements Runnable {

    ServerSocket server = null;
    Socket socket = null;
    BufferedReader in = null;
    BufferedWriter out = null;

    String exitWord = "Bye";
    String response = "good";

    @Override
    public void run() {
        try {
            server = new ServerSocket(59999);
            log.info("[서버] 연결 대기 중......");

            socket = server.accept();
            log.info("[서버] 소켓이 생성되어 연결이 됐습니다.");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String message = in.readLine();

                if (message.equalsIgnoreCase(exitWord)) {
                    log.info("[서버] 연결이 종료되었습니다.");
                    break;
                }

                log.info("[서버] 클라이언트로부터 데이터가 전송되었습니다. message = {}", message);
                out.write(response);
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
