package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.Organization;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.ErrorMessageEnum;
import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.exception.EmailAlreadyExistException;
import com.example.taskmanager_backend.exception.OrganizationNotFoundException;
import com.example.taskmanager_backend.exception.WrongRoleNameException;
import com.example.taskmanager_backend.repository.OrganizationRepository;
import com.example.taskmanager_backend.exception.ResourceNotFoundException;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public BaseResponse create(UserRequest userRequest) {
        if (userRequest.getAdminRole() != Role.ADMIN) {
            throw new WrongRoleNameException(ErrorMessageEnum.WRONG_ROLE.getMessage());
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExistException(ErrorMessageEnum.EMAIL_ALREADY_EXISTS.getMessage());
        }

        Organization organization = organizationRepository.findById(userRequest.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessageEnum.ORGANIZATION_NOT_FOUND.getMessage()));

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setOrganization(organization);
        user.setUserRole(userRequest.getUserRole());

        userRepository.save(user);

        return BaseResponse.getSuccessMessage();
    }

    @Override
    public BaseResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.getMessage() + id));

        return new BaseResponse(
                "User successfully fetched",
                LocalDateTime.now(),
                true,
                user
        );
    }

    @Override
    public BaseResponse getAllUsers() {
        List<User> users = userRepository.findAll();

        return new BaseResponse(
                "Users successfully fetched",
                LocalDateTime.now(),
                true,
                users
        );
    }


}
