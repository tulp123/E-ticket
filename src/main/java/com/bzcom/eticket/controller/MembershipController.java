package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Membership;
import com.bzcom.eticket.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/memberships"})
@CrossOrigin("*")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    // Get All Membership
    @GetMapping
    public List<Membership> getAllMemberships(){
        return membershipService.findAll();
    }

    // Get a Single Membership
    @GetMapping("/{id}")
    public Membership getMembershipById(@PathVariable(value = "id") Integer id) {
        return membershipService.findById(id);
    }

    // Create a new Membership
    @PostMapping
    public Membership saveMembership(@RequestBody Membership membership){
        return membershipService.save(membership);
    }

    // Update a Membership
    @PutMapping("/{id}")
    public Membership updateMembership(@PathVariable(value = "id") Integer id, @RequestBody Membership membership) {
        membership.setId(id);
        Membership mUpdate = this.membershipService.save(membership);
        return mUpdate;
    }

}
