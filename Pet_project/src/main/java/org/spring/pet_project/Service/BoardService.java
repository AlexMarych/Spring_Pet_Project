package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Response.BoardDto;
import org.spring.pet_project.Exception.BoardNotFoundException;
import org.spring.pet_project.Mapper.RequestMapper;
import org.spring.pet_project.Mapper.IdMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ChatService chatService;
    private final RequestMapper createRequestMapper;
    private final ResponseMapper responseMapper;
    private final IdMapper idMapper;
    private final RequestMapper requestMapper;

    public BoardDto createBoard(RequestBoardDto boardDto) {
        var tmpBoard = createRequestMapper.toBoard(boardDto);
        tmpBoard.setChats(Set.of(chatService.createChat(tmpBoard.getId())));
        return responseMapper.toBoardDto(boardRepository.save(tmpBoard));
    }

    public BoardDto updateBoard(UUID id, RequestBoardDto boardDto) {
        var tmpBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return responseMapper.toBoardDto(boardRepository.save(requestMapper.toBoard(boardDto, tmpBoard)));
    }

    public void deleteBoard(UUID id) {
        boardRepository.deleteById(id);
    }

    public BoardDto getById(UUID id) {
        return responseMapper.toBoardDto(boardRepository.findById(id).orElseThrow(BoardNotFoundException::new));
    }

    public BoardDto setMembers(UUID id, Set<UUID> memberIds) {
        var tmpBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        tmpBoard.setMemberAppUsers(idMapper.toAppUserSet(memberIds));
        return responseMapper.toBoardDto(boardRepository.save(tmpBoard));
    }

}
