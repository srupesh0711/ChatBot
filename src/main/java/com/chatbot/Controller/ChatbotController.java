package com.chatbot.Controller;

import com.chatbot.Service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String query) {
        return chatbotService.getAnswer(query);
    }
}
