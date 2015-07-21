package com.makingdevs

import javax.persistence.*

@Entity
class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id
  String description

}
