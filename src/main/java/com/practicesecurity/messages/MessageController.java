package com.practicesecurity.messages;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> findAll() {

        return messageService.AllMessages();
    }



    @GetMapping("/users/{username}/messages")
    public ResponseEntity<List<MessageResponse>> findByUser(@PathVariable String username) {
        return ResponseEntity.ok(messageService.findByUser(username));
    }


    @PostMapping("/users/{username}/messages")
    public ResponseEntity<?> save(@PathVariable String username, @RequestBody MessageRequest req ) {


        messageService.createMessage(username,req);
        return ResponseEntity.ok("Message saved successfully");


    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message deleted successfully");
    }


}
