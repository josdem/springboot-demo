package com.makingdevs

import javax.persistence.*

@Entity
class Task {
  @Id
  @GeneratedValue(strategy = GeberationType=AUTO)
  Long id
  String description


}
