package com.example.schedule1.user.controller;


import com.example.schedule1.user.dto.*;
import com.example.schedule1.user.entity.User;
import com.example.schedule1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> create (@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    //Http를 사용하기 때문에 Controller에서 처리
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        SessionUser sessionUser = new SessionUser(user.getId(), user.getEmail());
        session.setAttribute("loginUser", sessionUser);

        LoginResponse response = new LoginResponse(user.getId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAll (){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOne(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> update(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequest request, @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
