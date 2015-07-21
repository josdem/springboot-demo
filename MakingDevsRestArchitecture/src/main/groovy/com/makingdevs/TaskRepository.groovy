package com.makingdevs

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="tasks")
interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

}
