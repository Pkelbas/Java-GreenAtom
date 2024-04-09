package com.pkelbas.forumservice.forum.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue
  private Integer id;

  private String text;

  private LocalDateTime creationDate;

  private LocalDateTime editDate;

  private String username;

  @Column(name = "topicId")
  private Integer topicId;
}
