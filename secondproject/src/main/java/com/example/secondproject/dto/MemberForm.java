package com.example.secondproject.dto;

import com.example.secondproject.entity.Member;

public class MemberForm {
    private String name;
    private String pass;
    private String email;

    public MemberForm(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null,name,pass,email);
    }
}
