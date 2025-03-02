package com.chatbot.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {
    
    private final WebScraperService webScraperService;
    
    private final Map<String, String> documentationUrls = new HashMap<>();

    public ChatbotService(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;

        // Define official documentation URLs
        documentationUrls.put("segment", "https://segment.com/docs/");
        documentationUrls.put("mparticle", "https://docs.mparticle.com/");
        documentationUrls.put("lytics", "https://docs.lytics.com/");
        documentationUrls.put("zeotap", "https://docs.zeotap.com/home/en-us/");
    }

    public String getAnswer(String query) {
        String platform = detectCDP(query);

        if (platform == null) {
            return "I'm sorry, I can only answer questions about Segment, mParticle, Lytics, and Zeotap.";
        }

        String url = documentationUrls.get(platform);
        String content = webScraperService.scrapeDocumentation(url);

        return searchForAnswer(query, content);
    }

    private String detectCDP(String query) {
        for (String key : documentationUrls.keySet()) {
            if (query.toLowerCase().contains(key)) {
                return key;
            }
        }
        return null;
    }

    private String searchForAnswer(String query, String content) {
        // Simple keyword search
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.toLowerCase().contains(query.toLowerCase())) {
                return line;
            }
        }
        return "I couldn't find an exact answer. Please check the documentation: " + content.substring(0, 500) + "...";
    }
}
