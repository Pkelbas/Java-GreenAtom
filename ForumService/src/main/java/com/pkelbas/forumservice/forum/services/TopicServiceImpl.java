package com.pkelbas.forumservice.forum.services;

import com.pkelbas.forumservice.forum.entities.Message;
import com.pkelbas.forumservice.forum.entities.Topic;
import com.pkelbas.forumservice.forum.models.TopicDto;
import com.pkelbas.forumservice.forum.repositories.TopicRepository;
import com.pkelbas.forumservice.forum.abstractions.TopicService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

  private final TopicRepository repository;


  @Autowired
  public TopicServiceImpl(TopicRepository repository) {
    this.repository = repository;
  }

  public TopicDto createTopic(String title, String messageText) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    Topic topic = new Topic();
    Message message = new Message();

    topic.setTitle(title);
    message.setText(messageText);
    message.setUsername(username);
    message.setCreationDate(LocalDateTime.now());
    topic.getMessageList().add(message);

    return repository.save(topic).toDto();
  }

  public TopicDto updateTopic(Integer topicId, String title) {
    Topic topic = repository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("No topic by this id:" + topicId));

    topic.setTitle(title);

    return repository.save(topic).toDto();
  }

  public TopicDto findTopic(Integer topicId) {
    return repository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("No topic by this id:" + topicId)).toDto();
  }

  public void deleteTopic(Integer topicId) {
    Topic topic = repository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("No topic by this id:" + topicId));

    repository.delete(topic);
  }

  public List<TopicDto> findAllTopics() {
    return repository.findAll().stream().map(Topic::toDto).toList();
  }
}
