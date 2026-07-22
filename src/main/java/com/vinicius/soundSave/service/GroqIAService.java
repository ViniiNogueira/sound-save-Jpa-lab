package com.vinicius.soundSave.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class GroqIAService {

    private static final OpenAIClient client = OpenAIOkHttpClient.builder()
            .apiKey(System.getenv("GROQ_API_KEY"))
            .baseUrl("https://api.groq.com/openai/v1")
            .build();

    public static String obterResumo(String texto) {
        try {
            ChatCompletionCreateParams params =
                    ChatCompletionCreateParams.builder()
                            .model("llama-3.3-70b-versatile")
                            .addUserMessage("Fale sobre o artista: " + texto)
                            .build();

            ChatCompletion completion = client.chat().completions().create(params);

            return completion
                    .choices()
                    .getFirst()
                    .message()
                    .content()
                    .orElse("Sem resposta.");

        } catch (Exception e) {
            return "Não foi possível obter o resumo no momento. Erro: " + e.getMessage();
        }
    }
}