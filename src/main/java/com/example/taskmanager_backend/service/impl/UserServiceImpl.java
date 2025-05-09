package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.Organization;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.exception.EmailAlreadyExistException;
import com.example.taskmanager_backend.exception.OrganizationNotFoundException;
import com.example.taskmanager_backend.exception.WrongRoleNameException;
import com.example.taskmanager_backend.repository.OrganizationRepository;
import com.example.taskmanager_backend.exception.ResourceNotFoundException;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public BaseResponse create(UserRequest userRequest) {

        if (userRequest.getAdminRole() != Role.ADMIN) {
            throw new WrongRoleNameException("Only Admin can create Users.");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExistException("The include email already exist.");
        }

        Optional<Organization> optionalOrganization = organizationRepository.findById(userRequest.getOrganizationId());
        
        if (optionalOrganization.isEmpty()) {
            throw new OrganizationNotFoundException("The provided organization ID was not found.");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setOrganization(optionalOrganization.get());
        user.setUserRole(userRequest.getUserRole());

        userRepository.save(user);
        return BaseResponse.getSuccessMessage();
    }

    @Override
    public BaseResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        BaseResponse response = new BaseResponse();
        response.setMessage("User successfully fetched");
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setData(user);
        return response;
    }

    @Override
    public BaseResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        BaseResponse response = new BaseResponse();
        response.setMessage("Users successfully fetched");
        response.setSuccess(true);
        response.setData(users);
        return response;
    }


}
