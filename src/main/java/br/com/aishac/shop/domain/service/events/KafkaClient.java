package br.com.aishac.shop.domain.service.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaClient {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final transient ObjectMapper objectMapper;

    public void sendMessage(Object payload) {
        try {
            var json = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(SHOP_TOPIC_NAME, json);
        } catch (Exception e) {
            log.error("Erro ao enviar a mensagem para Fila: {}", SHOP_TOPIC_NAME, e);
        }
    }
}
