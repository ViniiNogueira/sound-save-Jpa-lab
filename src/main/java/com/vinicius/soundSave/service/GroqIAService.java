package com.vinicius.soundSave.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.stereotype.Service;

@Service
public class GroqIAService {

    private final OpenAIClient client = OpenAIOkHttpClient.builder()
            .apiKey(System.getenv("GROQ_API_KEY"))
            .baseUrl("https://api.groq.com/openai/v1")
            .build();

    public String obterResumo(String texto , String musicas) {

        try {
            ChatCompletionCreateParams params =
                    ChatCompletionCreateParams.builder()
                            .model("llama-3.3-70b-versatile")
                            .addUserMessage("""
                            Fale brevemente sobre o artista musical abaixo.

                            Nome: %s
                            Músicas cadastradas: %s

                            Use essas músicas como contexto para identificar corretamente o artista.
                            Utilize tambem fontes externas. Não utilize apenas as musicas como referencia.
                            Se houver ambiguidade, diga que pode haver mais de um artista com esse nome.
                            Não invente informações se não tiver certeza.
                            Responda em português do Brasil.
                            """.formatted(texto, musicas))
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