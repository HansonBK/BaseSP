package com.practicesecurity.messages;


import com.practicesecurity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findByUser_Username(String username);

    List<Message> findByUser(User user);
}
