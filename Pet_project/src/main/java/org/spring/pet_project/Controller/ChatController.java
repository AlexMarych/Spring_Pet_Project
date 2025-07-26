package org.spring.pet_project.Controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.ChatDto;
import org.spring.pet_project.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDto> getChat(@PathVariable UUID chatId) {
        return ResponseEntity.ok(chatService.getById(chatId));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<ChatDto>> getAllChats(@PathVariable UUID boardId) {
        return ResponseEntity.ok(chatService.getAll(boardId));
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<ChatDto> createChat(@PathVariable UUID boardId,
                                              @RequestParam @NotBlank @Min(2) @Max(30) String name) {
        return ResponseEntity.ok(chatService.createChat(boardId, name));
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<ChatDto> updateChat(@PathVariable UUID chatId,
                                              @RequestParam @NotBlank @Min(2) @Max(30) String name) {
        return ResponseEntity.ok(chatService.updateChat(chatId, name));
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable UUID chatId) {
        chatService.deleteChat(chatId);
        return ResponseEntity.noContent().build();
    }

}
