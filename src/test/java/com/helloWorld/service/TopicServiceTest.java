package com.helloWorld.service;

import com.helloWorld.exception.ResourceNotFoundException;
import com.helloWorld.model.Topic;
import com.helloWorld.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TopicServiceTest {

  @Mock TopicRepository topicRepository;

  @InjectMocks TopicService topicService;

  @Test
  void fetchAllTopicsTest() {
    List<Topic> topicList = new ArrayList<>();
    topicList.add(new Topic(1, "Javascript", "front-end"));
    topicList.add(new Topic(2, "C#", "back-end"));
    topicList.add(new Topic(3, "python", "data science"));
    when(topicRepository.findAll()).thenReturn(topicList);

    assertEquals(3, topicService.fetchAllTopics().size());
  }

  @Test
  void fetchAllTopicsTest_Exception() {
    List<Topic> topicList = new ArrayList<>();
    topicList.add(new Topic(1, "Javascript", "front-end"));
    topicList.add(new Topic(2, "C#", "back-end"));
    topicList.add(new Topic(3, "python", "data science"));
    when(topicRepository.findAll()).thenThrow(ResourceNotFoundException.class);

    assertThrows(ResourceNotFoundException.class,()->topicService.fetchAllTopics());
  }

  @Test
  void fetchTopicById() {
    Topic topic = new Topic(11, "Javascript", "front-end");
    when(topicRepository.findById(any())).thenReturn(java.util.Optional.of(topic));

    assertEquals("Javascript", topicService.fetchTopicById(11).get().getName());
    assertEquals("front-end", topicService.fetchTopicById(11).get().getDescription());
  }

  @Test
  void fetchTopicByName() {
    Topic topic = new Topic(11, "javascript", "front-end");
    when(topicRepository.findByName(anyString())).thenReturn(java.util.Optional.of(topic));

    assertEquals(11, topicService.fetchTopicByName("javascript").get().getId());
    assertEquals("front-end", topicService.fetchTopicByName("javascript").get().getDescription());
  }

  @Test
  void addTopic() {}

  @Test
  void updateTopic() {}

  @Test
  void deleteTopic() {}
}
