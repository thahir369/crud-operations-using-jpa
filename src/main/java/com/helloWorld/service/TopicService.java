package com.helloWorld.service;

import com.helloWorld.exception.ResourceNotFoundException;
import com.helloWorld.model.Topic;
import com.helloWorld.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {

  @Autowired private final TopicRepository topicRepository;

  List<Topic> topics;

  public List<Topic> fetchAllTopics() {
    topics = topicRepository.findAll();
    if (topics.isEmpty()) {
      throw new ResourceNotFoundException("no topics are found");
    } else {
      return topics;
    }
  }

  public Optional<Topic> fetchTopicById(int id) {
    if (topicRepository.findById(id).isEmpty()) {
      throw new ResourceNotFoundException("topic with id " + id + " is not found");
    } else {
      return topicRepository.findById(id);
    }
  }

  public Optional<Topic> fetchTopicByName(String topicName) {
    if (topicRepository.findByName(topicName).isEmpty()) {
      throw new ResourceNotFoundException("topic with name " + topicName + " is not found");
    } else {
      return topicRepository.findByName(topicName);
    }
  }

  public void addTopic(Topic topic) {
    topicRepository.save(topic);
  }

  public void updateTopic(int id, Topic topic) {
    if (topicRepository.findById(id).isEmpty())
      throw new ResourceNotFoundException("topic with id " + id + " is not found");
    else topicRepository.save(topic);
  }

  public void deleteTopic(int id) {
    topicRepository.deleteById(id);
  }
}
