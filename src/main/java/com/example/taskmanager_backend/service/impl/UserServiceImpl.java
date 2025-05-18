package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.UserResponse;
import com.example.taskmanager_backend.entities.Organization;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.ErrorMessageEnum;
import com.example.taskmanager_backend.exception.*;
import com.example.taskmanager_backend.repository.OrganizationRepository;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public BaseResponse create(UserRequest userRequest) {
//        if (userRequest.getAdminRole() != Role.ADMIN) {
//            throw new WrongRoleNameException(ErrorMessageEnum.WRONG_ROLE.getMessage());
//        }

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
        user.setRole(userRequest.getUserRole());

        userRepository.save(user);
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                null, // password response-da göstərilmir
                user.getRole()
        );
        return BaseResponse.success("User successfully created");
    }

    @Override
    public BaseResponse<UserResponse> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.getMessage() + id));

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                null,
                user.getRole()
        );

        return BaseResponse.success("User successfully fetched", response);
    }

    @Override
    public BaseResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> responseList = userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        null, // şifrə gizli qalsin
                        user.getRole()
                ))
                .collect(Collectors.toList());

        return BaseResponse.success("Users successfully fetched", responseList);
    }
    @Override
    public BaseResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.format(id)));

        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(ErrorMessageEnum.EMAIL_ALREADY_EXISTS.getMessage());
        }

        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessageEnum.ORGANIZATION_NOT_FOUND.getMessage()));

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setOrganization(organization);
        user.setRole(request.getUserRole());

        userRepository.save(user);

        return BaseResponse.success("User successfully updated");
    }
    @Override
    public BaseResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.format(id)));

        userRepository.delete(user);

        return BaseResponse.builder()
                .success(true)
                .message("User deleted successfully")
                .build();
    }
}



