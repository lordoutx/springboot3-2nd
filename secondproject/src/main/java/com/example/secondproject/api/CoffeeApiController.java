package com.example.secondproject.api;

import com.example.secondproject.dto.CoffeeDto;
import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Coffee;
import com.example.secondproject.repository.CoffeeRepository;
import com.example.secondproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {
    @Autowired
    CoffeeService coffeeService;

    //GET
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }

    //POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto dto){
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,@RequestBody CoffeeDto dto){
        Coffee updated = coffeeService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
