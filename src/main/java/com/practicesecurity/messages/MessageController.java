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

    @GetMapping("/all/messages")
    public List<Message> findAll() {

        return messageService.AllMessages();
    }



    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> findByUser() {
        return ResponseEntity.ok(messageService.findByUser());
    }


    @PostMapping("/messages")
    public ResponseEntity<?> save( @RequestBody MessageRequest req ) {


        messageService.createMessage(req);
        return ResponseEntity.ok("Message saved successfully");


    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message deleted successfully");
    }


}
