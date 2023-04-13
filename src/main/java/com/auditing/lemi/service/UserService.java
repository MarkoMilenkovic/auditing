package com.auditing.lemi.service;

import com.auditing.lemi.entity.UserEntity;
import com.auditing.lemi.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Getter
@Setter
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity update(Long id, UserEntity user) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        userEntity.setName(user.getName());
        return userEntity;
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

}
