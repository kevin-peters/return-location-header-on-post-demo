package com.example.some;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SomeEntityRepository extends CrudRepository<SomeEntity, Long> {
}
