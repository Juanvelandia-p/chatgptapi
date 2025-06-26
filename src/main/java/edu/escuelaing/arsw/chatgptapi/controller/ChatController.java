package edu.escuelaing.arsw.chatgptapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arsw.chatgptapi.service.OpenAIService;
import edu.escuelaing.arsw.chatgptapi.service.OpenAIServiceProxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OpenAIServiceProxy openAIService;

    public ChatController(OpenAIServiceProxy openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody String message) {
        String response = openAIService.getChatResponse(message);
        return ResponseEntity.ok(response);
    }
}
