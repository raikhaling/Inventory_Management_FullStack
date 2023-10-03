package com.amnil.invbackend.dto.auth;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto extends RepresentationModel<RegisterDto> {
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Boolean active ;
    private LocalDateTime date;

}
