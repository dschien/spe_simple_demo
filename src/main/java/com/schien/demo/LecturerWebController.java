package com.schien.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/view/")
public class LecturerWebController {

    @Autowired
    LecturerRepository lecturerRepository;


    @RequestMapping("/view_lecturer/{id}")
    public String lecturer(@PathVariable Long id, Model model) {
        model.addAttribute("lecturer", lecturerRepository.findById(id).get());
        return "lecturer";
    }

    @RequestMapping(value = "/view_lecturers", method = RequestMethod.GET)
    public String lecturerList(Model model) {
        model.addAttribute("lecturers", lecturerRepository.findAll());
        return "lecturers";
    }


}