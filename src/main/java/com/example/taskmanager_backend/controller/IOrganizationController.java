package com.example.taskmanager_backend.controller;

import com.example.taskmanager_backend.dto.request.OrganizationRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;

public interface IOrganizationController {

    BaseResponse signup(OrganizationRequest organizationRequest);

}
