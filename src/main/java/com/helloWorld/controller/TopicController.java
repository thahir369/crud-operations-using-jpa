package com.helloWorld.controller;

import com.helloWorld.model.Topic;
import com.helloWorld.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
public class TopicController {

  @Autowired private final TopicService topicService;

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
  public String addTopic(@RequestBody Topic topic) {
    topicService.addTopic(topic);
    return ("topic with name:" + topic.getName() + " is added successfully!");
  }

  @PutMapping("topics/{id}")
  public String updateTopic(@RequestBody Topic topic, @PathVariable int id) {
    topicService.updateTopic(id, topic);
    return ("topic with id:" + id + " is updated successfully!");
  }

  @DeleteMapping("/topics/{id}")
  public String deleteTopic(@PathVariable int id) {
    topicService.deleteTopic(id);
    return ("topic with id:" + id + " is deleted successfully!");
  }
}
