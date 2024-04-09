package com.pkelbas.forumservice.forum.abstractions;

import com.pkelbas.forumservice.forum.models.MessageDto;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface MessageService {

  MessageDto createMessage(Integer topicId, String messageText);

  MessageDto updateMessage(Integer messageId, String messageText) throws AccessDeniedException;

  List<MessageDto> findAllMessagesInTopic(Integer topicId);

  MessageDto findMessage(Integer messageId);

  void deleteMessage(Integer messageId) throws AccessDeniedException;
}
