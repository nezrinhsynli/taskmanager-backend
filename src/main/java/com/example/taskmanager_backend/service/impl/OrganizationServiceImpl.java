package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.OrganizationRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.Organization;
import com.example.taskmanager_backend.exception.EmailAlreadyExist;
import com.example.taskmanager_backend.exception.OrganizationAlreadyExist;
import com.example.taskmanager_backend.repository.OrganizationRepository;
import com.example.taskmanager_backend.service.IOrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public BaseResponse signup(OrganizationRequest organizationRequest) {

        if(organizationRepository.existsByAdminEmail(organizationRequest.getAdminEmail())){
            throw new EmailAlreadyExist("Email Already Exist");
        }
        if(organizationRepository.existsByOrganizationName(organizationRequest.getOrganizationName())){
            throw new OrganizationAlreadyExist("Organization Name Already Exist");
        }

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationRequest, organization);
        organizationRepository.save(organization);
        return BaseResponse.getSuccessMessage();
    }


}
