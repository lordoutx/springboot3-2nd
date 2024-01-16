package com.example.secondproject.repository;


import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MemberRepository extends CrudRepository<Member,Long> {
    @Override
    ArrayList<Member> findAll();   //원래 Iterable 타입으로 반환하나 이를 ArrayList로 변환

    ArrayList<Member> findAllByOrderById();
}
