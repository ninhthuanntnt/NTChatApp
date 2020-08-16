package com.ntnt.chatapp.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ta_user_verification")
@Getter
@Setter
@NoArgsConstructor
public class UserVerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private Long id;

    @Setter
    @Column(name = "t_verification_code", length = 45)
    private String verifiationCode;

    @Column(name = "d_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar createdAt;

    @OneToOne
    @JoinColumn(name = "i_id")
    @JsonIgnore
    private UserEntity user;
}
