package com.practicesecurity.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    @Getter
    @Setter
    private String message;
}
