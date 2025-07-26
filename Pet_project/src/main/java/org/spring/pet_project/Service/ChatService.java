package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.ChatDto;
import org.spring.pet_project.Exception.BoardNotFoundException;
import org.spring.pet_project.Exception.ChatNotFoundException;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.Chat;
import org.spring.pet_project.Repository.BoardRepository;
import org.spring.pet_project.Repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final BoardRepository boardRepository;

    private final ResponseMapper responseMapper;

    public ChatDto createChat(UUID boardId, String name) {
        var modifiedBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        return responseMapper.toChatDto(chatRepository.save(Chat
                .builder()
                .board(modifiedBoard)
                .name(name)
                .messages(new HashSet<>())
                .build()
        ));
    }

    public Chat createChat(UUID boardId) { // TODO : solve! board is created before and after chat
        var modifiedBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        return chatRepository.save(Chat
                .builder()
                .board(modifiedBoard)
                .name(modifiedBoard.getTitle() + " Boards Chat")
                .messages(new HashSet<>())
                .build()
        );
    }

    public ChatDto updateChat(UUID chatId, String name) {
        var modifiedChat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        modifiedChat.setName(name);
        return responseMapper.toChatDto(chatRepository.save(modifiedChat));
    }

    public void deleteChat(UUID Id) {
        chatRepository.deleteById(Id);
    }

    public ChatDto getById(UUID Id) {
        return responseMapper.toChatDto(chatRepository.findById(Id).orElseThrow(ChatNotFoundException::new));
    }

    public List<ChatDto> getAll(UUID boardId) {
        return chatRepository.findAllByBoardId(boardId).stream().map(responseMapper::toChatDto).toList();
    }
}
