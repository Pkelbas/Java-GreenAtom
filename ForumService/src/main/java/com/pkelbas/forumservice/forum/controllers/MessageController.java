package com.pkelbas.forumservice.forum.controllers;

import com.pkelbas.forumservice.forum.entities.Message;
import com.pkelbas.forumservice.forum.services.MessageService;
import java.nio.file.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")

public class MessageController {

  private final MessageService service;

  @Autowired
  public MessageController(MessageService service){
    this.service = service;
  }

  @PostMapping("/create")
  public Message createMessage(@RequestParam Integer topicId, @RequestParam String text){
    return service.createMessage(topicId, text);
  }

  @PutMapping("/update")
  public Message updateMessage(@RequestParam Integer messageId, @RequestParam String text)
      throws AccessDeniedException {
    return service.updateMessage(messageId, text);
  }

  @GetMapping("/find")
  public Message findMessage(@RequestParam Integer messageId){
    return service.findMessage(messageId);
  }

  @GetMapping("/all")
  public Iterable<Message> findAllMessagesInTopic(@RequestParam Integer topicId){
    return service.findAllMessagesInTopic(topicId);
  }

  @DeleteMapping("/delete")
  public void deleteMessage(@RequestParam Integer messageId) throws AccessDeniedException {
    service.deleteMessage(messageId);
  }

}
