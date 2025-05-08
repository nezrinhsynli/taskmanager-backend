package com.example.taskmanager_backend.service;

import com.example.taskmanager_backend.dto.request.OrganizationRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;

public interface IOrganizationService {

    BaseResponse signup(OrganizationRequest organizationRequest);

}
