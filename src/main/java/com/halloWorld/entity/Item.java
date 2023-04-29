package com.halloWorld.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long itemId;

  @Column(name = "item_name")
  private String itemName;

  @Column(name = "item_price")
  private String itemPrice;

  @Column(name = "order_id")
  private String orderId;
}
