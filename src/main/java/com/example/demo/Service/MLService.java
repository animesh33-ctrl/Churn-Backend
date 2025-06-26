package com.example.demo.Service;

import com.example.demo.Model.MLInput;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MLService {

    public String callModel(MLInput input) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://localhost:5000/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = new HashMap<>();
        payload.put("creditScore", input.creditScore);
        payload.put("geography", input.geography);
        payload.put("gender", input.gender);
        payload.put("age", input.age);
        payload.put("tenure", input.tenure);
        payload.put("balance", input.balance);
        payload.put("numOfProducts", input.numOfProducts);
        payload.put("hasCrCard", input.hasCrCard);
        payload.put("isActiveMember", input.isActiveMember);
        payload.put("estimatedSalary", input.estimatedSalary);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Object result = response.getBody().get("result");
            return result.toString();
        } else {
            return "Error: Unable to get prediction";
        }
    }
}
