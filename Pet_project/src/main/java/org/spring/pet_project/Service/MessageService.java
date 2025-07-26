package org.spring.pet_project.Service;

import lombok.AllArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.ChatMessageDto;
import org.spring.pet_project.Controller.DTO.Response.CommentMessageDto;
import org.spring.pet_project.Exception.ChatNotFoundException;
import org.spring.pet_project.Exception.MessageNotFoundException;
import org.spring.pet_project.Exception.TaskNotFoundException;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.Messages.ChatMessage;
import org.spring.pet_project.Model.Messages.CommentMessage;
import org.spring.pet_project.Model.Messages.Message;
import org.spring.pet_project.Repository.ChatRepository;
import org.spring.pet_project.Repository.MessageRepository;
import org.spring.pet_project.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository<ChatMessage> chatMessageRepository;
    private final MessageRepository<CommentMessage> commentRepository;
    private final MessageRepository<Message> messageRepository;
    private final ResponseMapper responseMapper;
    private final ChatRepository chatRepository;
    private final TaskRepository taskRepository;

    public List<ChatMessageDto> getAllChatMessages(UUID chatId) {
        return chatMessageRepository.findAllByChatId(chatId).stream().map(responseMapper::toChatMessageDto).toList();
    }

    public List<CommentMessageDto> getAllComments(UUID taskId) {
        return commentRepository.findAllByTaskId(taskId).stream().map(responseMapper::toCommentDto).toList();
    }


    public ChatMessageDto createChatMessage(UUID chatId, String text) {
        var tmpChat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);

        return responseMapper.toChatMessageDto(chatMessageRepository.save(ChatMessage
                .builder()
                .chat(tmpChat)
                .text(text)
                .build()
        ));
    }

    public CommentMessageDto createComment(UUID taskId, String text) {
        var tmpTask = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        return responseMapper.toCommentDto(commentRepository.save(CommentMessage
                .builder()
                .task(tmpTask)
                .text(text)
                .build()
        ));
    }

    public Record updateMessage(UUID messageId, String text) {
        var tmpMessage = messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);
        tmpMessage.setText(text);

        if (tmpMessage instanceof ChatMessage)
            return responseMapper.toChatMessageDto(messageRepository.save(tmpMessage));
        else
            return responseMapper.toCommentMessageDto(messageRepository.save(tmpMessage));
    }

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}
