package com.ntnt.chatapp.entities;

import com.fasterxml.jackson.annotation.*;
import com.ntnt.chatapp.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank @NotNull
    private String password;

    @Column(name = "t_email", length = 100)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)+$", message = "Email isn't valid")
    private String email;

    @Column(name = "t_phone_number", length = 11)
    @Pattern(regexp = "(0|(\\+84))[0-9]{9}", message = "Phone isn't valid")
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
