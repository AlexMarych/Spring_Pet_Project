package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.ChatDto;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.Chat;
import org.spring.pet_project.Repository.BoardRepository;
import org.spring.pet_project.Repository.ChatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        var modifiedBoard = boardRepository.findById(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found!"));
        return responseMapper.toChatDto(chatRepository.save(Chat
                .builder()
                .board(modifiedBoard)
                .name(name)
                .messages(new HashSet<>())
                .build()
        ));
    }

    public Chat createChat(UUID boardId) { // TODO : solve! board is created before and after chat
        var modifiedBoard = boardRepository.findById(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found!"));

        return chatRepository.save(Chat
                .builder()
                .board(modifiedBoard)
                .name(modifiedBoard.getTitle() + " Boards Chat")
                .messages(new HashSet<>())
                .build()
        );
    }

    public ChatDto updateChat(UUID chatId, String name) {
        var modifiedChat = chatRepository.findById(chatId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found!"));
        modifiedChat.setName(name);
        return responseMapper.toChatDto(chatRepository.save(modifiedChat));
    }

    public void deleteChat(UUID Id) {
        chatRepository.deleteById(Id);
    }

    public ChatDto getById(UUID Id) {
        return responseMapper.toChatDto(chatRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found!")));
    }

    public List<ChatDto> getAll(UUID boardId) {
        return chatRepository.findAllByBoardId(boardId).stream().map(responseMapper::toChatDto).toList();
    }
}
