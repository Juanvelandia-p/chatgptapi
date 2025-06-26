package edu.escuelaing.arsw.chatgptapi.model;

import java.util.List;
import lombok.Data;


@Data
public class ChatResponse {
    
    private List<Choice> choices;

    @Data
    public static class Choice {
        ChatMessage message;
        
    }


}
