package com.example.controller;

import com.example.model.Student;
import com.example.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller

public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewHomePage(Model model) {
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);
        System.out.print("Get / ");
        return "index";
    }

//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @GetMapping("/users")
//    public String getOne(@PathVariable(value = "id") Long id, Model model){
//        Optional<Student> listone= service.findById(id);
//        model.addAttribute("listone", listone);
//        return "user";
//    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public String viewPage(Model model) {
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);
        System.out.print("Get / ");
        return "user";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student std) {
        service.save(std);
        return "redirect:/list";
    }

    @RequestMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Optional<Student> std = service.get(id);
        mav.addObject("student", std);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletestudent(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}