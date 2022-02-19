package com.helloWorld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "topics_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic {

  @Id
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "topic_name")
  private String name;

  @Column(name = "description")
  private String description;
}
