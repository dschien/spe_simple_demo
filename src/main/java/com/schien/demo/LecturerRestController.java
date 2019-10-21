package com.schien.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LecturerRestController {

    @Autowired
    LecturerRepository lecturerRepository;


    @RequestMapping("/lecturer/{id}")
    public Lecturer lecturer(@PathVariable Long id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        return lecturer.orElse(null);
    }

    @RequestMapping(value = "/lecturers", method = RequestMethod.GET)
    public Iterable<Lecturer> lecturerList() {
        return lecturerRepository.findAll();
    }

    @RequestMapping(value = "/savelecturer", method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody Lecturer lecturer) {
        lecturerRepository.save(lecturer);
        return lecturer.getId().toString();
    }

}