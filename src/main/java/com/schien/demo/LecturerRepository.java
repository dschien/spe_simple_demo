package com.schien.demo;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shakeelosmani on 24/12/16.
 */
public interface LecturerRepository extends CrudRepository<Lecturer, Long> {
    List<Lecturer> findByLastName(String lastName);
}
