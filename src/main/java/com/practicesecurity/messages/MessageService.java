package com.practicesecurity.messages;


import com.practicesecurity.user.User;
import com.practicesecurity.user.UserService;
import org.springframework.stereotype.Service;
import com.practicesecurity.security.CurrentUserService;

import java.util.List;

@Service
public class MessageService {


    private final MessageRepo messageRepo;
    private final UserService userService;

    private final CurrentUserService currentUserService;

    public MessageService(MessageRepo messageRepo, UserService userService, CurrentUserService currentUserService) {
        this.messageRepo = messageRepo;
        this.userService = userService;
        this.currentUserService = currentUserService;
    }

    public List<MessageResponse> findByUser() {


        String username = currentUserService.getUsername();
        User user = userService.getUserByUsername(username); // throws if not found

        return messageRepo.findByUser(user).stream()
                .map(m -> new MessageResponse(
                        m.getMessageId(),
                        m.getMessage(),
                        m.getUser().getUsername()
                ))
                .toList();
    }




    public List<Message> AllMessages() {
        return messageRepo.findAll();
    }

    public Message createMessage(MessageRequest req) {

        String Username = currentUserService.getUsername();


        User user = userService.getUserByUsername(Username);


        Message msg = new Message();
        msg.setMessage(req.getMessage());
        msg.setUser(user);

        messageRepo.save(msg);

        return msg;

    }

    public void deleteMessage(Long id) {

        String Username = currentUserService.getUsername();

        User currentUser = userService.getUserByUsername(Username);

        Message msg = messageRepo.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));

        if(!msg.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Forbidden");
        }

        messageRepo.delete(msg);


    }


}
