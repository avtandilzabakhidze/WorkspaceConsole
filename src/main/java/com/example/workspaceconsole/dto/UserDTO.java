package com.example.workspaceconsole.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String personalNo;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
}
