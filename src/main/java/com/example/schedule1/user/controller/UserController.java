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


    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        LoginResponse login = userService.login(request);
        SessionUser sessionUser = new SessionUser(login.getId(), login.getEmail());
        session.setAttribute("loginUser", sessionUser);

        return ResponseEntity.ok("success");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name ="loginUser", required = false) SessionUser sessionUser,
            HttpSession session
    ){
        if(sessionUser == null){
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
