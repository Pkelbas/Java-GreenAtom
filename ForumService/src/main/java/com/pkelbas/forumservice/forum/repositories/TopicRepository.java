package com.pkelbas.forumservice.forum.repositories;

import com.pkelbas.forumservice.forum.entities.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {

}
