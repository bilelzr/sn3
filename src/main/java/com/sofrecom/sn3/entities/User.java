package com.sofrecom.sn3.entities;

import com.sofrecom.sn3.entities.enumeration.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Table(name = "user_sn3")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "user_pk_id")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
    private long userPkId;

    @ManyToMany
    @JoinTable(
            name = "user_application", // Join table for the Many-to-Many relationship
            joinColumns = @JoinColumn(name = "user_pk_id"),
            inverseJoinColumns = @JoinColumn(name = "application_pk_id")
    )
    private List<Application> applications; // The applications to which the user is assigned

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean accountNonLocked = true;
    @ManyToMany(mappedBy = "membres") // This avoids duplicate join tables
    private List<Group> groups;
    private UUID uuid;
    @OneToMany(mappedBy = "porteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
    @Column(name = "image_path", columnDefinition = "TEXT")
    private String imagePath;


    public User(long userPkId, String firstName, String lastName, String email, String password, String phone, Role role, boolean accountNonLocked, List<Group> groups, UUID uuid, List<Task> tasks) {
        this.userPkId = userPkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
        this.groups = groups;
        this.uuid = uuid;
        this.tasks = tasks;
    }

    public User() {
    }

    public User(long userPkId, String firstName, String lastName, String email, String password, String phone, Role role, boolean accountNonLocked) {
        this.userPkId = userPkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
    }

    public User(long userPkId, String firstName, String lastName, String email, String password, String phone, Role role, boolean accountNonLocked, List<Group> groups) {
        this.userPkId = userPkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
        this.groups = groups;
    }

    public User(String firstName, String lastName, String email, String password, Role role, String phone, boolean accountNonLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.accountNonLocked = accountNonLocked;
    }

    public User(long userPkId, String firstName, String lastName, String email, String password, String phone, Role role, boolean accountNonLocked, List<Group> groups, UUID uuid) {
        this.userPkId = userPkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.accountNonLocked = accountNonLocked;
        this.groups = groups;
        this.uuid = uuid;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public long getUserPkId() {
        return userPkId;
    }

    public void setUserPkId(long userPkId) {
        this.userPkId = userPkId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userPkId=" + userPkId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", accountNonLocked=" + accountNonLocked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userPkId == user.userPkId && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPkId, email);
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
