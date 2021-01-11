package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Organization;
import com.bzcom.eticket.model.User;
import com.bzcom.eticket.service.OrganizationService;
import com.bzcom.eticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/organizations"})
@CrossOrigin("*")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    // Get All Organization
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.findAll();
    }

    // Get All User by Organization-id
    @GetMapping("/list-user")
    public List<User> getAllUserByOrganizationId(@RequestParam int organizationId) {
        return userService.findUserByOrganizationId(organizationId);
    }

    // Get a Single Organization
    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable(value = "id") Integer id) {
        return organizationService.findById(id);
    }

    // Create a new Organization
    @PostMapping
    public Organization saveOrganization(@RequestBody Organization Organization) {
        return organizationService.save(Organization);
    }

    // Update a Organization
    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable(value = "id") Integer OrganizationId, @RequestBody Organization OrganizationDetail) {
        OrganizationDetail.setId(OrganizationId);
        Organization OrganizationUpdate = organizationService.save(OrganizationDetail);
        return OrganizationUpdate;
    }
}
