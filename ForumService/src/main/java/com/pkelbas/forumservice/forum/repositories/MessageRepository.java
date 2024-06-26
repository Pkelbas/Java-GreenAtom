package com.pkelbas.forumservice.forum.repositories;

import com.pkelbas.forumservice.forum.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
