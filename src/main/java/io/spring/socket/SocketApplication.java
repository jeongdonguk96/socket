package io.spring.socket;

import io.spring.socket.server.ByteServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketApplication.class, args);

		Thread byteServerThread = new Thread(new ByteServer());
		byteServerThread.start();
	}

}
