package com.ai_project.MistralAi.Controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static com.ai_project.MistralAi.FormatMaker.ResponseAnalyzer.analyzeResponse;

@RestController
@CrossOrigin("*")
public class ChatController {

    private final MistralAiChatModel chatModel;

    @Autowired
    public ChatController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

//    Generate full response and then pass
    @GetMapping("/ai/generate")
    public Map<String, Object> generate(@RequestParam String prompt) {
        return analyzeResponse(this.chatModel.call(prompt));
    }

    @GetMapping("/status")
    public boolean sendResponse(){
        return true;
    }

//    @GetMapping("/ai/generate")
//    public Mono<List<ChatResponse>> generate(@RequestParam String prompt) {
//        var newPrompt = new Prompt(new UserMessage(prompt));
//        return this.chatModel.stream(newPrompt).collectList();
//    }
}