package com.project.pppmagasinmicroservice.Controllers;

import com.project.pppmagasinmicroservice.Services.TopicService;
import com.project.pppmagasinmicroservice.Utilis.DTO.TopicDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Topic")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Content-Type"})
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.save(topicDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(topicService.getTopic(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getAll());
    }
}
