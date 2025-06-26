package com.example.demo.Controller;

import com.example.demo.Model.MLInput;
import com.example.demo.Service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MLController {

    @Autowired
    private MLService mlService;

    @PostMapping("/predict")
    public ResponseEntity<String> predict(@RequestBody MLInput input) {
        String result = mlService.callModel(input);
        return ResponseEntity.ok(result);
    }
}
