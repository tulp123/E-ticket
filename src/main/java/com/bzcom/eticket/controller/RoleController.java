package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Role;
import com.bzcom.eticket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/roles"})
@CrossOrigin("http://localhost:3000")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Get all Role
    @GetMapping
    public List<Role> getAllRoles(){
        return roleService.findAll();
    }

    // Get a Single Role
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable(value = "id") int id){
        return roleService.findById(id);
    }

    // Create a new Role
    @PostMapping
    public Role createRole(@RequestBody Role role){
        return roleService.save(role);
    }

    // Update a Role
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable(value = "id") int id, @RequestBody Role role){
        role.setId(id);
        Role updateRole = roleService.save(role);
        return updateRole;
    }

}
