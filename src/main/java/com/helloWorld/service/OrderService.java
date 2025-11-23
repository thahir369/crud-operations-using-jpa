package com.helloWorld.service;

import com.helloWorld.entity.Order;
import com.helloWorld.exception.ResourceNotFoundException;
import com.helloWorld.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public List<Order> fetchAllOrders() {
    List<Order> orderList = orderRepository.findAll();
    log.info("");
    return orderList;
  }

  public Optional<Order> fetchOrderById(String id) {
    if (orderRepository.findById(id).isEmpty()) {
      throw new ResourceNotFoundException("order with id " + id + " is not found");
    } else {
      return orderRepository.findById(id);
    }
  }

  public void addOrder(Order order) {
    orderRepository.save(order);
  }

  public void updateOrder(String id, Order order) {
    if (orderRepository.findById(id).isEmpty()) {
      throw new ResourceNotFoundException("order with id " + id + " is not found");
    } else {
      orderRepository.save(order);
    }
  }

  public void deleteOrder(String id) {
    orderRepository.deleteById(id);
  }
}
