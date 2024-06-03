package com.example.userauth.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReqistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
