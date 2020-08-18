package com.ntnt.chatapp.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntnt.chatapp.enums.ParticipantType;
import com.ntnt.chatapp.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ta_participant")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "t_type")
    @Enumerated(EnumType.STRING)
    private ParticipantType type;

    @Column(name = "d_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar createdAt;

    @Column(name = "d_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar updatedAt;

    @Column(name = "d_deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_user")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_group")
    private GroupEntity group;
}
