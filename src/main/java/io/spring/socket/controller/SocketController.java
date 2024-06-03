package io.spring.socket.controller;

import io.spring.socket.client.Client;
import io.spring.socket.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SocketController {

    private final Client client;

    @PostMapping("/send")
    public void getWhat() {
        Member member = createMember();
        client.sendMessage(member);
    }


    private Member createMember() {
        return Member.builder()
                .name("견민석")
                .age(33)
                .hobby("축구")
                .position("윙어")
                .build();
    }

}