package edu.escuelaing.arsw.chatgptapi.model;

import lombok.Data;

@Data
public class ChatMessage {
    
    private String role;
    private String content;

}
