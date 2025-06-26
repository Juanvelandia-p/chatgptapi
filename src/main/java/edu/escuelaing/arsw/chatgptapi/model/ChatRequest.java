package edu.escuelaing.arsw.chatgptapi.model;

import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {
    private String model;
    private List<ChatMessage> messages;
}
