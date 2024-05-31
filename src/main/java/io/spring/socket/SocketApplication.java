package io.spring.socket;

import io.spring.socket.client.ByteClient;
import io.spring.socket.client.TextClient;
import io.spring.socket.server.ByteServer;
import io.spring.socket.server.TextServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketApplication.class, args);

//		Thread textServerThread = new Thread(new TextServer());
//		Thread textClientThread = new Thread(new TextClient());
//		textServerThread.start();
//		textClientThread.start();

		Thread byteServerThread = new Thread(new ByteServer());
//		Thread byteClientThread = new Thread(new ByteClient());
		byteServerThread.start();
//		byteClientThread.start();
	}

}
