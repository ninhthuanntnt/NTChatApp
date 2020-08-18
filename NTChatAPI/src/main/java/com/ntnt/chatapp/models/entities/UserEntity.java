package com.ntnt.chatapp.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntnt.chatapp.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.List;
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

    @Column(name = "t_username", length = 100, unique = true)
    private String username;

    @Column(name = "t_password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password is not empty")
    @NotNull(message = "Password is not null")
    private String password;

    @Column(name = "t_email", length = 100, unique = true)
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "t_phone_number", length = 11, unique = true)
    @Pattern(regexp = "(0|(\\+84))[0-9]{9}", message = "Phone isn't valid")
    private String phoneNumber;

    @Column(name = "t_address")
    private String address;

    @Column(name = "t_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "d_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar createdAt;

    @Column(name = "d_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar updatedAt;

    @Column(name = "t_status", length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "t_avatar_url")
    private String avatarUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ta_user_role",
            joinColumns = {@JoinColumn(name = "i_user")},
            inverseJoinColumns = {@JoinColumn(name = "i_role")}
    )
    private Set<RoleEntity> roles;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private UserVerificationEntity userVerification;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private AccessEntity access;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<MessageEntity> sendedMessages;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<MessageEntity> receivedMessages;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<FriendEntity> friends;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<GroupEntity> groups;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ParticipantEntity> participants;

}
