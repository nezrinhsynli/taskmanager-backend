package com.example.taskmanager_backend.controller.impl;

import com.example.taskmanager_backend.controller.IOrganizationController;
import com.example.taskmanager_backend.dto.request.OrganizationRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.service.IOrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/api/organization")
public class OrganizationControllerImpl implements IOrganizationController {

    @Autowired
    IOrganizationService organizationService;

    @PostMapping(path = "/signup")
    @Override
    public BaseResponse signup(@RequestBody @Valid OrganizationRequest organizationRequest) {
        return organizationService.signup(organizationRequest);
    }



}
