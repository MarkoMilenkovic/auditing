package com.auditing.lemi.web;

import com.auditing.lemi.entity.UserEntity;
import com.auditing.lemi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController implements Serializable {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserEntity save(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @PutMapping(path = "{id}")
    public UserEntity update(@PathVariable("id") Long id, @RequestBody UserEntity user) {
        return userService.update(id, user);
    }

}
