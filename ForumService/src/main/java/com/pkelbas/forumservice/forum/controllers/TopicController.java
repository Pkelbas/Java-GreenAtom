package com.pkelbas.forumservice.forum.controllers;

import com.pkelbas.forumservice.forum.entities.Topic;
import com.pkelbas.forumservice.forum.services.TopicService;
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
  public TopicController(TopicService service){
    this.service = service;
  }

  @PostMapping("/create")
  public Topic createTopic(@RequestParam String title, @RequestParam String text){
    return service.createTopic(title, text);
  }

  @PutMapping("/update")
  public Topic updateTopic(@RequestParam Integer topicId, @RequestParam String title){
    return service.updateTopic(topicId, title);
  }

  @GetMapping("/find")
  public Topic findTopic(@RequestParam Integer topicId){
    return service.findTopic(topicId);
  }

  @DeleteMapping("/delete")
  public void deleteTopic(@RequestParam Integer topicId){
    service.deleteTopic(topicId);
  }

  @GetMapping("/all")
  public Iterable<Topic> findAllTopics(){
    return service.findAllTopics();
  }
}
