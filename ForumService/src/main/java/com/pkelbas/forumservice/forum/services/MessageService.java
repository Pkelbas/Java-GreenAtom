package com.pkelbas.forumservice.forum.services;

import com.pkelbas.forumservice.forum.entities.Message;
import com.pkelbas.forumservice.forum.entities.Topic;
import com.pkelbas.forumservice.forum.repositories.MessageRepository;
import com.pkelbas.forumservice.forum.repositories.TopicRepository;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageRepository messageRepository;

  private final TopicRepository topicRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository, TopicRepository topicRepository){
    this.messageRepository = messageRepository;
    this.topicRepository = topicRepository;
  }

  public Message createMessage(Integer topicId, String messageText){
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("No topic by this id:" + topicId));
    Message message = new Message();

    message.setText(messageText);
    message.setUsername(username);
    message.setTopicId(topicId);
    message.setCreationDate(LocalDateTime.now());

    return messageRepository.save(message);
  }

  public Message updateMessage(Integer messageId, String messageText) throws AccessDeniedException {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    boolean hasAdminRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

    Message message = messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("No message by this id:" + messageId));

    if(!message.getUsername().equals(username) && !hasAdminRole){
      throw new AccessDeniedException("This user lacks access rights to update this message");
    }

    message.setText(messageText);
    message.setEditDate(LocalDateTime.now());

    return messageRepository.save(message);
  }

  public Iterable<Message> findAllMessagesInTopic(Integer topicId){
    Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("No topic by this id:" + topicId));
    return topic.getMessageList();
  }

  public Message findMessage(Integer messageId){
    return messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("No message by this id:" + messageId));
  }

  public void deleteMessage(Integer messageId) throws AccessDeniedException {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    boolean hasAdminRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

    Message message = messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("No message by this id:" + messageId));

    if(!message.getUsername().equals(username) && !hasAdminRole){
      throw new AccessDeniedException("This user lacks access rights to delete this message");
    }

    messageRepository.delete(message);
  }

}
