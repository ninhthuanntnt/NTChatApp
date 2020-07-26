package com.ntnt.chatapp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final Long serialVersionUID = 1326498421159L;

    private String token;
    private String prefix;

}
