package com.chatbot.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperService {

    public String scrapeDocumentation(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("p"); // Extract paragraphs
            StringBuilder content = new StringBuilder();

            for (Element p : paragraphs) {
                content.append(p.text()).append("\n");
            }

            return content.toString();
        } catch (IOException e) {
            return "Error fetching data: " + e.getMessage();
        }
    }
}
