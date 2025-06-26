package edu.escuelaing.arsw.chatgptapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.escuelaing.arsw.chatgptapi.model.ChatMessage;
import edu.escuelaing.arsw.chatgptapi.model.ChatRequest;
import edu.escuelaing.arsw.chatgptapi.model.ChatResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String userMessage) {
        ChatMessage message = new ChatMessage();
        message.setRole("user");
        message.setContent(userMessage);

        ChatRequest request = new ChatRequest();
        request.setModel("gpt-3.5-turbo");
        request.setMessages(List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatResponse> response = restTemplate.postForEntity(apiUrl, entity, ChatResponse.class);

        return response.getBody().getChoices().get(0).getMessage().getContent(); // Replace getContent() with the correct method or field, e.g., getText() or getBody()
        // Example fix if the method is getText():
        // return response.getBody().getChoices().get(0).getMessage().getText();
        // Or if the field is 'content':
        // return response.getBody().getChoices().get(0).getMessage().content;
    }
}
