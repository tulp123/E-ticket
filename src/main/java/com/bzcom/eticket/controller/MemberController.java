package com.bzcom.eticket.controller;

import com.bzcom.eticket.dto.MemberDTO;
import com.bzcom.eticket.model.*;
import com.bzcom.eticket.payloadLogin.request.SignupRequest;
import com.bzcom.eticket.payloadLogin.response.MessageResponse;
import com.bzcom.eticket.repository.ConfirmationTokenRepository;
import com.bzcom.eticket.repository.MemberRepository;
import com.bzcom.eticket.service.EmailService;
import com.bzcom.eticket.service.MemberService;
import com.bzcom.eticket.service.MembershipService;
import com.bzcom.eticket.service.RoleService;
import com.bzcom.eticket.service.UserService;
import com.bzcom.eticket.smodel.MemberChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@RestController
@RequestMapping({"/members"})
@CrossOrigin("*")
public class MemberController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get All Member
    @GetMapping
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberService.findAll();
        return this.mapDataToMemberDTO(members);
    }

    private List<MemberDTO> mapDataToMemberDTO (List<Member> members) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        for (Member member : members) {
            MemberDTO memberDTO = new MemberDTO(member);
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }

    @GetMapping("/sort-up")
    public List<Member> getAllMembersSortUp() {
        return memberService.findAllByCreatedDateAsc();
    }

    @GetMapping("/sort-down")
    public List<Member> getAllMembersSortDown() {
        return memberService.findAllByCreatedDateDesc();
    }

    // Get a Single Member
    @GetMapping("/{id}")
    public MemberDTO getMemberById(@PathVariable(value = "id") Integer id) {
        Member member = memberService.findById(id);
        return new MemberDTO(member);
    }

    //find member by role_id
    @GetMapping("/role/{role_id}")
    public List<Member> searchUserByRole(@PathVariable(value = "role_id") int roleId) {
        return memberService.getMemberByRole(roleId);
    }

    // Create a new Member
//    @PostMapping
//    public Member saveMember(@RequestBody Member member){
//        Member saveMember = new Member();
//        if (!memberService.checkExistUsername(member.getUsername())){
//
//            // save user, member, confirmToken to database
//            User user = member.getUser();
//            userService.save(user);
//            member.setUser(user);
//            member.setRole(roleService.findById(1));
//            member.setMembership(membershipService.findById(1));
//            saveMember = memberService.save(member);
//
//            ConfirmationToken confirmationToken = new ConfirmationToken(member);
//            confirmationTokenRepository.save(confirmationToken);
//
//            // send email
//            emailService.sendEmailConfirmCreateMember(member, confirmationToken);
//        }
//
//        return saveMember;
//    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Member registerAdmin(@RequestBody SignupRequest signupRequest) {
        Member admin = new Member();
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        userService.save(user);

        admin.setUser(user);
        admin.setUsername(signupRequest.getUsername());
        admin.setPassword(encoder.encode(signupRequest.getPassword()));
        Set<Role> roles = new HashSet<>();

        Role userRole = roleService.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        admin.setRoles(roles);

        return memberService.save(admin);
    }

    @PostMapping
    public Member registerUser(@RequestBody SignupRequest signupRequest) {
        Member member = new Member();
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        userService.save(user);

        member.setUser(user);
        member.setUsername(signupRequest.getUsername());
        member.setPassword(encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        Role userRole = roleService.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        member.setRoles(roles);
        return memberService.save(member);
    }

    @PostMapping("/check")
    public boolean checkMember(@RequestBody SignupRequest signupRequest) {
        return !memberService.checkExistUsername(signupRequest.getUsername());
    }

    // confirm create account via email
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmMemberAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Member member = memberService.findById(token.getMember().getId());
            member.setStatus(1);
            memberService.save(member);
            return "OK";
        } else {
            return "Confirm create account fail";
        }
    }


    // Update a Seat
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable(value = "id") Integer memberId, @RequestBody Member memberDetail) {
        Member member = memberService.findById(memberId);
        User user = member.getUser();
//        if (memberDetail.getEmail() != null) {
//            user.setEmail(memberDetail.getEmail());
//        }
//        if (memberDetail.getFullName() != null) {
//            user.setFullName(memberDetail.getFullName());
//        }
//        if (memberDetail.getPhoneNumber() != null) {
//            user.setPhoneNumber(memberDetail.getPhoneNumber());
//        }
//        if (memberDetail.getAddress() != null) {
//            user.setAddress(memberDetail.getAddress());
//        }
        member.setUser(user);
        Member memberUpdate = memberService.save(member);
        return memberUpdate;
    }

    // Lock an User
    @PutMapping("/{id}/lock")
    public Member lockUser(@PathVariable(value = "id") Integer id) {
        Member member = memberService.findById(id);
        member.setStatus(2);
        memberService.save(member);
        return member;
    }

    @GetMapping("/search")
    public List<MemberDTO> findAllByConditions(@RequestParam String searchKey) {
        List<Member> members = memberService.findAllByConditions(searchKey);
        return this.mapDataToMemberDTO(members);
    }

    @GetMapping("/imei")
    public Member findByImeiMember(@RequestParam Long imei) {
        return memberService.findMemberByImeiMember(imei);
    }

    @PostMapping("/changepassword")
    public Map<String, Integer> changePassword(@RequestBody MemberChangePassword model) {
        Map<String, Integer> result = new HashMap<>();
        Member member = memberService.findById(model.getId());
        boolean checkOldPassword = passwordEncoder.matches(model.getOldPass(), member.getPassword());
        if(checkOldPassword) {
            member.setPassword(passwordEncoder.encode(model.getNewPass()));
            memberService.save(member);
            result.put("status", 200);
        } else {
            result.put("status", 401);
        }
        return result;
    }
}

