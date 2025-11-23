package com.halloWorld.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloWorld.controller.JpaController;
import com.helloWorld.entity.Topic;
import com.helloWorld.service.OrderService;
import com.helloWorld.service.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JpaController.class)
@ExtendWith(SpringExtension.class)
class JpaControllerTest {

  @Autowired MockMvc mockMvc;
  @MockBean TopicService topicService;
  @MockBean OrderService orderService;
  @Autowired ObjectMapper objectMapper;

  @Test
  void testGetAllTopics() throws Exception {
    List<Topic> topicList = new ArrayList<>();
    topicList.add(new Topic(1, "Javascript", "front-end"));
    topicList.add(new Topic(2, "C#", "back-end"));
    topicList.add(new Topic(3, "python", "data science"));
    when(topicService.fetchAllTopics()).thenReturn(topicList);

    MvcResult result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/topics").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    assertEquals(
        3, objectMapper.readValue(result.getResponse().getContentAsString(), List.class).size());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/topics").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id").value(1));
  }

  @Test
  void testGetTopicById() throws Exception {
    when(topicService.fetchTopicById(anyInt()))
        .thenReturn(java.util.Optional.of(new Topic(3, "python", "data science")));
    mockMvc
        .perform(MockMvcRequestBuilders.get("/topics/{id}", 3).accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$.id").value(3));

    MvcResult result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/topics/{id}", 3).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    assertEquals(
        3, objectMapper.readValue(result.getResponse().getContentAsString(), Topic.class).getId());
    assertEquals(
        "python",
        objectMapper.readValue(result.getResponse().getContentAsString(), Topic.class).getName());
  }

  @Test
  void testGetTopicByName() throws Exception {
    when(topicService.fetchTopicByName(anyString()))
        .thenReturn(java.util.Optional.of(new Topic(3, "python", "data science")));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/topics/name/{topicName}", "python")
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("python"));
  }

  @Test
  void testAddTopic() throws Exception {
    String reqBody = objectMapper.writeValueAsString(new Topic(3, "python", "data science"));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/topics")
                .accept(MediaType.APPLICATION_JSON)
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());

    ResultActions resultActions =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/topics")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(reqBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    assertEquals(
        "topic with name:python is added successfully!",
        resultActions.andReturn().getResponse().getContentAsString());
  }

  @Test
  void testUpdateTopic() throws Exception {
    String reqBody = objectMapper.writeValueAsString(new Topic(3, "python", "data science"));

    ResultActions resultActions =
        mockMvc
            .perform(
                MockMvcRequestBuilders.put("/topics/{id}", 3)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(reqBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    assertEquals(
        "topic with id:3 is updated successfully!",
        resultActions.andReturn().getResponse().getContentAsString());
  }

  @Test
  void testDeleteTopic() throws Exception {
    String reqBody = objectMapper.writeValueAsString(new Topic(3, "python", "data science"));

    ResultActions resultActions =
        mockMvc
            .perform(
                MockMvcRequestBuilders.delete("/topics/{id}", 3)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(reqBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    assertEquals(
        "topic with id:3 is deleted successfully!",
        resultActions.andReturn().getResponse().getContentAsString());
  }
}
