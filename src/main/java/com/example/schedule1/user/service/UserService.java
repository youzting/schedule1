package com.example.schedule1.user.service;

import com.example.schedule1.user.dto.*;
import com.example.schedule1.user.entity.User;
import com.example.schedule1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public CreateUserResponse save(CreateUserRequest request){
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        User userSave = userRepository.save(user);

        return new CreateUserResponse(
                userSave.getId(),
                userSave.getUsername(),
                userSave.getEmail(),
                userSave.getCreatedAt(),
                userSave.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public User login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("비밀번호 또는 이메일이 일치하지 않음")
        );
        if (!request.getPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호 또는 이메일이 일치하지 않음");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll(){
        List<User> users = userRepository.findAll();
        List<GetUserResponse> dtos = new ArrayList<>();
        for(User user : users){
            GetUserResponse dto = new GetUserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("유저가 없습니다")
        );
        return new GetUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UpdateUserResponse update (Long userId, UpdateUserRequest request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("유저가 없습니다")
        );
        user.updateUser(request.getEmail());
        return new UpdateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long userId){
        boolean existence = userRepository.existsById(userId);
        if(!existence){
            throw new IllegalStateException("유저가 없습니다");
        }
        userRepository.deleteById(userId);
    }
}
