package com.practicesecurity.messages;


import com.practicesecurity.user.User;
import com.practicesecurity.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserService userService;

    public MessageService(MessageRepo messageRepo, UserService userService) {
        this.messageRepo = messageRepo;
        this.userService = userService;
    }

    public List<MessageResponse> findByUser(String username) {

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

    public void createMessage(String username ,MessageRequest req) {

        User user = userService.getUserByUsername(username);
        Message msg = new Message();
        msg.setMessage(req.getMessage());
        msg.setUser(user);

        messageRepo.save(msg);

    }

    public void deleteMessage(Long id) {

        Message msg = messageRepo.findById(id).orElseThrow(()->new RuntimeException( "Message Not Found"));

        messageRepo.delete(msg);


    }


}
