package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.OrganizationRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.Organization;
import com.example.taskmanager_backend.exception.EmailAlreadyExistException;
import com.example.taskmanager_backend.exception.OrganizationAlreadyExistException;
import com.example.taskmanager_backend.repository.OrganizationRepository;
import com.example.taskmanager_backend.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private final OrganizationRepository organizationRepository;

    @Override
    public BaseResponse signup(OrganizationRequest organizationRequest) {

        if (organizationRepository.existsByAdminEmail(organizationRequest.getAdminEmail())) {
            throw new EmailAlreadyExistException("Email Already Exist");
        }
        if (organizationRepository.existsByOrganizationName(organizationRequest.getOrganizationName())) {
            throw new OrganizationAlreadyExistException("Organization Name Already Exist");
        }

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationRequest, organization);
        organizationRepository.save(organization);
        return BaseResponse.getSuccessMessage();
    }


}
