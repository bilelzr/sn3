package com.sofrecom.sn3.entities.DTO;

import java.util.UUID;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String role;
    private boolean accountNonLocked;
    private UUID uuid;


    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String password, String phone, String role, boolean accountNonLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
    }

    public UserDto(String firstName, String lastName, String email, String password, String phone, String role, boolean accountNonLocked, UUID uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
