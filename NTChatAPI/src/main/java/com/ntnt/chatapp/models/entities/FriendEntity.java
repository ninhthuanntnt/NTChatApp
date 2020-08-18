package com.ntnt.chatapp.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntnt.chatapp.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ta_friend")
@Getter
@Setter
@NoArgsConstructor
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private Long id;

    @Column(name = "t_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "d_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar createdAt;

    @Column(name = "d_deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_user")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_friend")
    private UserEntity friend;

}
