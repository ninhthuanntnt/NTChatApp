package com.ntnt.chatapp.models.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final Long serialVersionUID = 1326498421159L;

    private String token;
    private String prefix;
}
