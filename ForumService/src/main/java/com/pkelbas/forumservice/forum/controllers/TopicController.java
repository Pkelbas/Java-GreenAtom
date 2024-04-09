package com.pkelbas.forumservice.forum.controllers;

import com.pkelbas.forumservice.forum.abstractions.TopicService;
import com.pkelbas.forumservice.forum.models.TopicDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

  private final TopicService service;

  @Autowired
  public TopicController(TopicService service) {
    this.service = service;
  }

  @PostMapping("/create")
  public TopicDto createTopic(@RequestParam String title, @RequestParam String text) {
    return service.createTopic(title, text);
  }

  @PutMapping("/update")
  public TopicDto updateTopic(@RequestParam Integer topicId, @RequestParam String title) {
    return service.updateTopic(topicId, title);
  }

  @GetMapping("/find")
  public TopicDto findTopic(@RequestParam Integer topicId) {
    return service.findTopic(topicId);
  }

  @DeleteMapping("/delete")
  public void deleteTopic(@RequestParam Integer topicId) {
    service.deleteTopic(topicId);
  }

  @GetMapping("/all")
  public List<TopicDto> findAllTopics() {
    return service.findAllTopics();
  }
}
