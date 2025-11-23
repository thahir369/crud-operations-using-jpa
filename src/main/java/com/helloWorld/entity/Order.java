package com.helloWorld.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

  @Id
  @Column(name = "order_id1")
  private String orderId;

  @Column(name = "amount")
  private String amount;

  @Column(name = "payment")
  private String payment;

  @OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id",referencedColumnName = "order_id1",unique = true)
  private List<Item> itemList;

  //here name is the name to show as foreign key in child table
  //referencedColumnName is column used to connect parent and child tables
}
