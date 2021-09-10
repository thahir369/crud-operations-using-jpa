package com.helloWorld.repository;

import com.helloWorld.model.Topic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TopicRepositoryTest {

  @Autowired TopicRepository topicRepository;

  @Test
  void testFindAll() {
    List<Topic> topicList = new ArrayList<>();
    topicList = topicRepository.findAll();
    assertEquals(3, topicList.size());
    assertEquals("java", topicList.get(0).getName());
    assertEquals(2, topicList.get(1).getId());
  }

  @Test
  void testFindByID() {
    Optional<Topic> topic = topicRepository.findById(2);
    assertEquals("python", topic.get().getName());
  }

  @Test
  void testFindByName() {
    Optional<Topic> topic = topicRepository.findByName("java");
    assertEquals("java", topic.get().getName());
    assertEquals(1, topic.get().getId());
  }

  @Test
  void testAddTopic() {
    Topic topic = Topic.builder().name("groovy").description("new language").build();
    topicRepository.save(topic);
    Optional<Topic> result = topicRepository.findByName("groovy");
    assertEquals("new language", result.get().getDescription());
  }

  @Test
  void testDeleteTopic() {
    Topic topic = Topic.builder().name("groovy").description("new language").build();
    topicRepository.save(topic);
    Optional<Topic> result = topicRepository.findByName("groovy");
    assertEquals("new language", result.get().getDescription());

    topicRepository.delete(topic);
    assertEquals(empty(), topicRepository.findByName("groovy"));
  }

  @Test
  void testUpdateTopic() {
    Topic topic = Topic.builder().name("groovy").description("new language").build();
    topicRepository.save(topic);
    Optional<Topic> result = topicRepository.findByName("groovy");
    assertEquals("new language", result.get().getDescription());

    topic.setDescription("not a new language");
    topicRepository.save(topic);

    assertEquals("not a new language", topicRepository.findByName("groovy").get().getDescription());
  }
}
