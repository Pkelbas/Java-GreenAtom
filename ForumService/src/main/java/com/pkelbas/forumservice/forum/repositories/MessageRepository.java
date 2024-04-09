package com.pkelbas.forumservice.forum.repositories;

import com.pkelbas.forumservice.forum.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
