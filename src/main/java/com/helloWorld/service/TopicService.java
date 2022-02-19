package com.helloWorld.service;

import com.helloWorld.dto.TopicDto;
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

  public List<Topic> fetchAllTopics() {
    List<Topic> topics;
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

  public void addTopic(TopicDto topicDto) {
    topicRepository.save(this.topicDtoToTopic(topicDto));
  }

  public void updateTopic(int id, TopicDto topicDto) {
    if (topicRepository.findById(id).isEmpty()) {
      throw new ResourceNotFoundException("topic with id " + id + " is not found");
    } else {
      topicRepository.save(this.topicDtoToTopic(topicDto));
    }
  }

  public void deleteTopic(int id) {
    topicRepository.deleteById(id);
  }

  private Topic topicDtoToTopic(TopicDto topicDto) {
    return Topic.builder()
        .id(topicDto.getId())
        .name(topicDto.getName())
        .description(topicDto.getDescription())
        .build();
  }
}
