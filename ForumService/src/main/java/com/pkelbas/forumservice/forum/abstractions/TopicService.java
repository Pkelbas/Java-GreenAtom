package com.pkelbas.forumservice.forum.abstractions;

import com.pkelbas.forumservice.forum.models.TopicDto;
import java.util.List;

public interface TopicService {

  TopicDto createTopic(String title, String messageText);

  TopicDto updateTopic(Integer topicId, String title);

  TopicDto findTopic(Integer topicId);

  void deleteTopic(Integer topicId);

  List<TopicDto> findAllTopics();
}
