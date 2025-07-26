package org.spring.pet_project.Controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.ChatMessageDto;
import org.spring.pet_project.Controller.DTO.Response.CommentMessageDto;
import org.spring.pet_project.Service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/massages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{chatId}")
    public ResponseEntity<List<ChatMessageDto>> getAllChatMessages(@PathVariable UUID chatId) {
        return ResponseEntity.ok(messageService.getAllChatMessages(chatId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentMessageDto>> getAllComments(@PathVariable UUID taskId) {
        return ResponseEntity.ok(messageService.getAllComments(taskId));
    }

    @PostMapping("/{chatId}")
    public ResponseEntity<ChatMessageDto> createChatMessage(@PathVariable UUID chatId,
                                                            @RequestParam @NotBlank @Min(1) @Max(200) String text) {
        return ResponseEntity.ok(messageService.createChatMessage(chatId, text));
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<CommentMessageDto> createComment(@PathVariable UUID taskId,
                                                               @RequestParam @NotBlank @Min(1) @Max(200) String text){
        return ResponseEntity.ok(messageService.createComment(taskId, text));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable UUID messageId,
                                           @RequestParam @NotBlank @Min(1) @Max(200) String text) {
        return ResponseEntity.ok(messageService.updateMessage(messageId, text));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }
}
