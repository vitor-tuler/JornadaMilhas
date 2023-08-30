package br.com.jornadamilhas.viagens.infra.services;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAI {
    public String generateAIText(String prompt){
        String token = System.getenv("OPENAI_KEY");
        OpenAiService service = new OpenAiService(token);
        List<ChatMessage> mensagens = new ArrayList<>();
        mensagens.add(new ChatMessage(ChatMessageRole.USER.value(), "faca um resumo sobre a cidade "+ prompt +" de modo informal, com dois paragrafos e possuindo no maximo 100 caracteres"));
        ChatCompletionRequest request = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(mensagens)
                .maxTokens(300)
                .build();
        ChatMessage resposta = service.createChatCompletion(request).getChoices().get(0).getMessage();
        return resposta.getContent();
    }
}
