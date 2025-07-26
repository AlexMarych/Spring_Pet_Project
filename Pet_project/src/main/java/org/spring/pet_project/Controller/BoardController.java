package org.spring.pet_project.Controller;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Response.BoardDto;
import org.spring.pet_project.Service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable UUID id) {
        return ResponseEntity.ok(boardService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<BoardDto> createBoard(@RequestBody RequestBoardDto requestBoardDto) {
        return ResponseEntity.ok(boardService.createBoard(requestBoardDto));
    }

    @PutMapping("/{id}") // TODO : Add owner check
    public ResponseEntity<BoardDto> updateBoard(@PathVariable UUID id, @RequestBody RequestBoardDto requestBoardDto) {
        return ResponseEntity.ok(boardService.updateBoard(id, requestBoardDto));
    }

    @PutMapping("/{id}/members") // TODO : Add owner check
    public ResponseEntity<BoardDto> updateMembers(@PathVariable UUID id, @RequestBody Set<UUID> memberIds) {
        return ResponseEntity.ok(boardService.setMembers(id, memberIds));
    }

    @DeleteMapping("/{id}") // TODO : Add owner check
    public ResponseEntity<Void> deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
