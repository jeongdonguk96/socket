package io.spring.socket.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class TextClient implements Runnable {

    Socket socket = null;
    BufferedReader in = null;
    BufferedWriter out = null;

    String word = "Hi";
    String exitWord = "Bye";

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", 59999);
            log.info("[클라이언트] 소켓이 생성되어 연결이 됐습니다.");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("out = " + out);

            while (true) {
                if (word.equalsIgnoreCase(exitWord)) {
                    log.info("[클라이언트] 연결을 종료합니다.");
                    break;
                }

                out.write(word);
                out.flush();
                log.info("[클라이언트] 서버에게 데이터를 전송했습니다. message = {}", word);

                String response = in.readLine();
                log.info("[클라이언트] 서버로부터 데이터를 응답받았습니다. response = {}", response);
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
