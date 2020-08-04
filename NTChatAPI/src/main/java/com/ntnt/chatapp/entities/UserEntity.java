package com.ntnt.chatapp.entities;

import com.fasterxml.jackson.annotation.*;
import com.ntnt.chatapp.enums.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TA_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private Long id;

    @Column(name = "t_name", length = 100)
    private String name;

    @Column(name = "t_username", length = 100)
    private String username;

    @Column(name = "t_password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "t_email", length = 100)
    private String email;

    @Column(name = "t_phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "t_address")
    private String address;

    @Column(name = "t_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "t_status", length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ta_user_role",
            joinColumns = {@JoinColumn(name = "i_user")},
            inverseJoinColumns = {@JoinColumn(name = "i_role")}
    )
    private Set<RoleEntity> roles;
}
