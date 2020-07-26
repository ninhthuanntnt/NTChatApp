package com.ntnt.chatapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private static final Long serialVersionUID = 3649681269866L;

    private String userMessage;
    private String internalMessage;
    private int code;
}
