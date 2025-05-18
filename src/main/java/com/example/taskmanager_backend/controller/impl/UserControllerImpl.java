package com.example.taskmanager_backend.controller.impl;

import com.example.taskmanager_backend.controller.IUserController;
import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "rest/api/user")
public class UserControllerImpl implements IUserController {

    private final IUserService userService;

    @PostMapping(path = "/create")
    @Override
    public BaseResponse create(@RequestBody @Valid UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping(path = "/getAll")
    @Override
    public BaseResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    @Override
    public BaseResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);



    }
    @PutMapping("/update/{id}")
    @Override
    public BaseResponse updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }
        @DeleteMapping("/delete/{id}")
        public BaseResponse deleteUser(@PathVariable Long id) {
            return userService.deleteUser(id);
        }

}
