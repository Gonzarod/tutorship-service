package com.evertix.sessionservice.model;


import lombok.Data;
import java.time.LocalDate;


@Data
public class User {

    public User() { }

    public User(String username, String password, String email, String name,
                String lastName, String dni, String phone, LocalDate birthday, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
    }

    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private LocalDate birthday;
    private String address;
    private int totalStar;
    private Boolean active;
    private String linkedin;

}
