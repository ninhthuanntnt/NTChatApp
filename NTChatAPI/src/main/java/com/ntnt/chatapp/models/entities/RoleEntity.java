package com.ntnt.chatapp.models.entities;

import com.ntnt.chatapp.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ta_role")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private int id;

    @Column(name = "t_role", length = 50)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ta_user_role",
            joinColumns = {@JoinColumn(name = "i_role")},
            inverseJoinColumns = {@JoinColumn(name = "i_user")}
    )
    private Set<UserEntity> users;
}
