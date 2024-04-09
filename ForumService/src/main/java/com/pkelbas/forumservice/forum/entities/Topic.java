package com.pkelbas.forumservice.forum.entities;

import com.pkelbas.forumservice.forum.models.MessageDto;
import com.pkelbas.forumservice.forum.models.TopicDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topics")
public class Topic {

  @Id
  @GeneratedValue
  private Integer id;

  private String title;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "topicId")
  private List<Message> messageList = new LinkedList<>();


  public TopicDto toDto() {
    List<MessageDto> list = messageList.stream().map(Message::toDto).toList();
    return new TopicDto(id, title, list);
  }
}
