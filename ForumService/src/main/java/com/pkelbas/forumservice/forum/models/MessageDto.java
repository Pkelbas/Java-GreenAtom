package com.pkelbas.forumservice.forum.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

  private Integer id;

  private String text;

  private LocalDateTime creationDate;

  private LocalDateTime editDate;

  private String username;

  private Integer topicId;

}
