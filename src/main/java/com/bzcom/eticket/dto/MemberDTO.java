package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Member member;
    private int userId;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    private String idCard;
    private Set<Role> roles;

    public MemberDTO(Member member) {
        member.setPassword("");
        this.member = member;
        this.userId = member.getUser().getId();
        this.fullName = member.getUser().getFullName();
        this.email = member.getUser().getEmail();
        this.address = member.getUser().getAddress();
        this.phoneNumber = member.getUser().getPhoneNumber();
        this.idCard = member.getUser().getIdCard();
        this.roles = member.getRoles();
    }
}
