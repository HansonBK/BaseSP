package com.practicesecurity.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse{
    private long id;
    private String content;
    private String username;
}