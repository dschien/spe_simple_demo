package com.schien.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class LecturerRestController {

    @Autowired
    LecturerRepository lecturerRepository;


    @RequestMapping("/lecturer/{id}")
    public String lecturer(@PathVariable Long id, Model model) {
        model.addAttribute("lecturer", lecturerRepository.findById(id));
        return "lecturer";
    }

    @RequestMapping(value = "/lecturers", method = RequestMethod.GET)
    public Iterable<Lecturer> lecturerList(Model model) {
        model.addAttribute("lecturers", lecturerRepository.findAll());
        return lecturerRepository.findAll();
    }

    @RequestMapping(value = "/savelecturer", method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody Lecturer lecturer) {
        lecturerRepository.save(lecturer);
        return lecturer.getId().toString();
    }

}