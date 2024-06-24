package io.spring.socket.client;

import io.spring.socket.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

@Slf4j
@Component
public class Client {

    public static final int BUFFER_SIZE = 1024;


    public void sendMessage(Member member) {
        try (
            Socket socket = new Socket("192.168.10.95", 59999);
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream())
        ) {
            byte[] bytes = member.toString().getBytes();
            out.write(bytes);
            out.flush();
            log.info("[클라이언트] 서버에게 데이터를 전송했습니다. message = {}", new String(bytes));

            byte[] buffer = new byte[BUFFER_SIZE];
            int read = in.read(buffer);
            String response = new String(buffer, 0, read);
            log.info("[클라이언트] 서버로부터 응답 데이터를 받았습니다. response = {}", response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}