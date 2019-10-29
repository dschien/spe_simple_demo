package com.schien.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;


@Controller
@RequestMapping("/view")
public class LecturerWebController {

    @Autowired
    LecturerRepository lecturerRepository;


    @RequestMapping("/lecturer/{id}")
    public String lecturer(@PathVariable Long id, Model model) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        lecturer.ifPresent(lecturer1 -> model.addAttribute("lecturer", lecturer1));
        return "lecturer";
    }


    @RequestMapping(value = "/lecturers", method = RequestMethod.GET)
    public String lecturerList(Model model) {
        model.addAttribute("lecturers", lecturerRepository.findAll());
        return "lecturers";
    }


}