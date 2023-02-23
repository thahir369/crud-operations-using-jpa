package com.halloWorld.controller;

import com.halloWorld.dto.TopicDto;
import com.halloWorld.entity.Topic;
import com.halloWorld.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
public class TopicController {

  private final TopicService topicService;

  @GetMapping("/home")
  public String homepage() {
    return "Hai! welcome to our website. click here to to see all topics available in this website";
  }

  @GetMapping("/topics")
  @ResponseStatus(HttpStatus.OK)
  public List<Topic> getAllTopics() {
    return topicService.fetchAllTopics();
  }

  @GetMapping("/topics/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Topic> getTopicById(@PathVariable int id) {
    return topicService.fetchTopicById(id);
  }

  @GetMapping("/topics/name/{topicName}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Topic> getTopicByName(@PathVariable String topicName) {
    return topicService.fetchTopicByName(topicName);
  }

  @PostMapping("/topics")
  @ResponseStatus(HttpStatus.CREATED)
  public String addTopic(@RequestBody TopicDto topicDto) {
    topicService.addTopic(topicDto);
    return ("topic with name:" + topicDto.getName() + " is added successfully!");
  }

  @PutMapping("topics/{id}")
  public String updateTopic(@RequestBody TopicDto topicDto, @PathVariable int id) {
    topicService.updateTopic(id, topicDto);
    return ("topic with id:" + id + " is updated successfully!");
  }

  @DeleteMapping("/topics/{id}")
  public String deleteTopic(@PathVariable int id) {
    topicService.deleteTopic(id);
    return ("topic with id:" + id + " is deleted successfully!");
  }
}
