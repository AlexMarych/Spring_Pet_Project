package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.Create.CreateBoardDto;
import org.spring.pet_project.Controller.DTO.Request.Update.UpdateBoardDto;
import org.spring.pet_project.Controller.DTO.Request.Update.UpdateChatDto;
import org.spring.pet_project.Controller.DTO.Response.BoardDto;
import org.spring.pet_project.Controller.DTO.Response.ChatDto;
import org.spring.pet_project.Exception.BoardNotFoundException;
import org.spring.pet_project.Exception.ChatNotFoundException;
import org.spring.pet_project.Mapper.CreateRequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Mapper.UpdateRequestMapper;
import org.spring.pet_project.Model.Chat;
import org.spring.pet_project.Repository.BoardRepository;
import org.spring.pet_project.Repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final BoardRepository boardRepository;

    private final UpdateRequestMapper updateRequestMapper;
    private final ResponseMapper responseMapper;

    public ChatDto createChat(BoardDto boardDto) {
        var modifiedBoard = boardRepository.findById(boardDto.id()).orElseThrow(BoardNotFoundException::new);

        return responseMapper.toChatDto(chatRepository.save(Chat
                .builder()
                .board(modifiedBoard)
                .name(boardDto.title() + " Boards Chat ")
                .messages(new HashSet<>())
                .build()
        ));
    }

    public ChatDto updateChat(UpdateChatDto chatDto) {
        var modifiedChat = chatRepository.findById(chatDto.id()).orElseThrow(ChatNotFoundException::new);
        return responseMapper.toChatDto(chatRepository.save(updateRequestMapper.toChat(chatDto, modifiedChat)));
    }

    public void deleteBoard(UUID Id) {
        chatRepository.deleteById(Id);
    }
}
