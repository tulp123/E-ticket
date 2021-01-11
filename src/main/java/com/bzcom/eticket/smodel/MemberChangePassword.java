package com.bzcom.eticket.smodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberChangePassword {

    private int id;
    private String oldPass;
    private String newPass;
}
