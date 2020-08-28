package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.ConfirmationToken;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.User;
import com.bzcom.eticket.repository.ConfirmationTokenRepository;
import com.bzcom.eticket.service.EmailService;
import com.bzcom.eticket.service.MemberService;
import com.bzcom.eticket.service.MembershipService;
import com.bzcom.eticket.service.RoleService;
import com.bzcom.eticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequestMapping({"/members"})
@CrossOrigin("http://localhost:3000")
public class MemberController {

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

    // Get All Member
    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.findAll();
    }

    @GetMapping("/sort-up")
    public List<Member> getAllMembersSortUp(){
        return memberService.findAllByCreatedDateAsc();
    }

    @GetMapping("/sort-down")
    public List<Member> getAllMembersSortDown(){
        return memberService.findAllByCreatedDateDesc();
    }

    // Get a Single Member
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable(value = "id") Integer id) {
        return memberService.findById(id);
    }

    //find member by role_id
    @GetMapping("/role/{role_id}")
    public List<Member> searchUserByRole(@PathVariable(value = "role_id") int roleId) {
        return memberService.getMemberByRole(roleId);
    }

    // Create a new Member
    @PostMapping
    public Member saveMember(@RequestBody Member member){
        Member saveMember = new Member();
        if (!memberService.checkExistUsername(member.getUsername())){

            // save user, member, confirmToken to database
            User user = member.getUser();
            userService.save(user);
            member.setUser(user);
            member.setRole(roleService.findById(1));
            member.setMembership(membershipService.findById(1));
            saveMember = memberService.save(member);

            ConfirmationToken confirmationToken = new ConfirmationToken(member);
            confirmationTokenRepository.save(confirmationToken);

            // send email
            emailService.sendEmailConfirmCreateMember(member, confirmationToken);
        }

        return saveMember;
    }

    // confirm create account via email
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmMemberAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Member member = memberService.findById(token.getMember().getId());
            member.setUserStatus(1);
            memberService.save(member);
            return "OK";
        } else {
            return "Confirm create account fail";
        }
    }


    // Update a Seat
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable(value = "id") Integer memberId, @RequestBody Member memberDetail) {
        memberDetail.setId(memberId);
        Member memberUpdate = memberService.save(memberDetail);
        return memberUpdate;
    }

    // Lock an User
    @PutMapping("/{id}/lock")
    public Member lockUser(@PathVariable(value = "id") Integer id) {
        Member member = memberService.findById(id);
        member.setUserStatus(2);
        memberService.save(member);
        return member;
    }
}
