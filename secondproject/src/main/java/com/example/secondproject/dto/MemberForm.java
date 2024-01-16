package com.example.secondproject.dto;

import com.example.secondproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String name;
    private String pass;
    private String email;

    public Member toEntity() {
        return new Member(id,name,pass,email);
    }
}
