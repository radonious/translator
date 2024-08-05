package edu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.model.RequestDTO;
import edu.model.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor
public class TranslateService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service.apiKey}")
    private String apiKey;
    @Value("${service.translatorURL}")
    private String url;

    public String translate(RequestDTO requestDTO) {
        StringBuilder result = new StringBuilder("http ");
        HttpStatusCode statusCode = HttpStatusCode.valueOf(200);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Api-Key " + apiKey);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        String[] words = requestDTO.getText().trim().split(" ");
        for (int id = 0; id < words.length; ++id) {
            try {
                RequestDTO splitRequest = new RequestDTO(requestDTO.getFrom(), requestDTO.getTo(), words[id]);
                String body = mapper.writeValueAsString(splitRequest);
                HttpEntity<String> request = new HttpEntity<>(body, headers);

                ResponseEntity<String> response = executor.submit(() -> {
                    try {
                        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
                    } catch (HttpClientErrorException.BadRequest e) {
                        return new ResponseEntity<String>(e.getStatusCode());
                    }
                }).get();

                if (!response.getStatusCode().is2xxSuccessful()) {
                    statusCode = response.getStatusCode();
                    break;
                } else {
                    ResponseDTO responseDTO = mapper.readValue(response.getBody(), ResponseDTO.class);
                    words[id] = responseDTO.toString();
                }

            } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
        }

        if (statusCode.is2xxSuccessful()) {
            result.append(statusCode.value()).append(' ');
            for (String word : words) {
                result.append(word).append(' ');
            }
        } else result.append(statusCode).append(' ');

        return result.toString();
    }
}
