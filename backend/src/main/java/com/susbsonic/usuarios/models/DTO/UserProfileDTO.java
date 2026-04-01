package com.susbsonic.usuarios.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String personalLink;
    private Date birthday;
    private String bio;

    public UserProfileDTO(String username, String name, String email, String personalLink, Date birthday, String bio) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.personalLink = personalLink;
        this.birthday = birthday;
        this.bio = bio;
    }
}
