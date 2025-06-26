package edu.escuelaing.arsw.chatgptapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class OpenAIServiceProxy {

    @Autowired
    private OpenAIService openAIService;

    // Simple in-memory cache
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public String getChatResponse(String userMessage) {

        if (!isValidMessage(userMessage)) {
            return "Mensaje no válido. Por favor, escribe una consulta clara y significativa.";
        }
        // Check cache first
        if (cache.containsKey(userMessage)) {
            return cache.get(userMessage);
        }
        // If not cached, call the real service
        String response = openAIService.getChatResponse(userMessage);
        cache.put(userMessage, response);
        return response;
    }
    private boolean isValidMessage(String message) {
        if (message == null) return false;
        String trimmed = message.trim();
        if (trimmed.length() < 3) return false; // Muy corto
        if (trimmed.matches("^[\\d\\s]+$")) return false; // Solo números y espacios
        if (trimmed.matches("^[\\p{Punct}\\s]+$")) return false; // Solo símbolos y espacios
        return true;
    }
}