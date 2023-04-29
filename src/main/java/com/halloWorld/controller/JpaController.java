package com.halloWorld.controller;

import com.halloWorld.dto.TopicDto;
import com.halloWorld.entity.Order;
import com.halloWorld.entity.Topic;
import com.halloWorld.repository.OrderRepository;
import com.halloWorld.service.OrderService;
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
public class JpaController {

  private final TopicService topicService;
  private final OrderService orderService;

  @PostMapping("/orders")
  public void order(@RequestBody Order order) {
    orderService.addOrder(order);
  }

  @GetMapping("/orders")
  @ResponseStatus(HttpStatus.OK)
  public List<Order> getAllOrders() {
    return orderService.fetchAllOrders();
  }

  @GetMapping("/orders/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Order> getOrderById(@PathVariable String id) {
    return orderService.fetchOrderById(id);
  }

  @PutMapping("orders/{id}")
  public String updateOrder(@RequestBody Order order, @PathVariable String id) {
    orderService.updateOrder(id, order);
    return ("order with id:" + id + " is updated successfully!");
  }

  @DeleteMapping("/orders/{id}")
  public String deleteOrder(@PathVariable String id) {
    orderService.deleteOrder(id);
    return ("order with id:" + id + " is deleted successfully!");
  }

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
