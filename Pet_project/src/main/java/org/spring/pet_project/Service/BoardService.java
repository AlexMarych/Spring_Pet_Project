package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.Create.CreateBoardDto;
import org.spring.pet_project.Controller.DTO.Request.Update.UpdateBoardDto;
import org.spring.pet_project.Controller.DTO.Response.BoardDto;
import org.spring.pet_project.Exception.BoardNotFoundException;
import org.spring.pet_project.Mapper.CreateRequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Mapper.UpdateRequestMapper;
import org.spring.pet_project.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CreateRequestMapper createRequestMapper;
    private final UpdateRequestMapper updateRequestMapper;
    private final ResponseMapper responseMapper;

    public BoardDto createBoard(CreateBoardDto boardDto) {
        return responseMapper.toBoardDto(boardRepository.save(createRequestMapper.toBoard(boardDto)));
    }

    public BoardDto updateBoard(UpdateBoardDto boardDto) {
        var tmpBoard = boardRepository.findById(boardDto.id()).orElseThrow(BoardNotFoundException::new);
        return responseMapper.toBoardDto(boardRepository.save(updateRequestMapper.toBoard(boardDto, tmpBoard)));
    }

    public void deleteBoard(UUID Id) {
        boardRepository.deleteById(Id);
    }
}
