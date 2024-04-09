package com.pkelbas.forumservice.forum.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

  private Integer id;

  private String title;

  private List<MessageDto> messages;

}
