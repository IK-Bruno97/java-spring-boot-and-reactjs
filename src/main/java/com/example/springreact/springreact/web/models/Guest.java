package com.example.springreact.springreact.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guest {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    
}
