package com.pkelbas.forumservice.forum.repositories;

import com.pkelbas.forumservice.forum.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
