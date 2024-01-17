package com.example.secondproject.service;

import com.example.secondproject.dto.CoffeeDto;
import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Coffee;
import com.example.secondproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAllByOrderById();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getId() !=null) return null;
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        log.info("id: {}, coffee: {}",id,coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != coffee.getId()){
            log.info("잘못된 요청! id: {}, article: {}",id,coffee.toString());
            return null;
        }
        target.patch(coffee);
        Coffee updated=coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null){
            log.info("잘못된 요청! id: {}, coffee: {}",id,target.toString());
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
