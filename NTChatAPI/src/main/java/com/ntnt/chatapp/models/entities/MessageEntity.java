package com.ntnt.chatapp.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntnt.chatapp.enums.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ta_message")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private Long id;

    @Column(name = "i_conversation")
    private int conversationId;

    @Column(name = "i_sender")
    private int senderId;

    @Column(name = "i_receiver")
    private int receiverId;

    @Column(name = "t_message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(name = "t_message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "d_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar createdAt;

    @Column(name = "d_deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar deletedAt;

}
