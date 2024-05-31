package io.spring.socket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Member {
    private String name;
    private int age;
    private String hobby;
}
