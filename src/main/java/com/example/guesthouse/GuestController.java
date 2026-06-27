package com.example.guesthouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    GuestRepository repo;
    
    //welcome
    @GetMapping("/")
    public String welcomePage() {
    	return "welcome";
    }
    
    //login
    @GetMapping("/login")
    public String loginPage() {
    	
    	return "login"; }
    	
    @PostMapping("/login")
    public String doLogin() {
    	
    	return "redirect:/guest/dashboard";
    }
    

    
    // dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("guestsList", repo.findAll());

        return "dashboard";
    }

    
    // Add Form
    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("guest", new Guest());

        return "addguest";
    }

    
    // Save Guest
    @PostMapping("/save")
    public String saveGuest(@ModelAttribute Guest guest) {

        repo.save(guest);

        return "redirect:/guest/dashboard";
    }

    
    // Delete Guest
    @GetMapping("/delete/{id}")
    public String deleteGuest(@PathVariable int id) {

        repo.deleteById(id);

        return "redirect:/guest/dashboard";
    }

    
    // Edit Form
    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable int id, Model model) {

        Guest guest = repo.findById(id).orElse(null);

        model.addAttribute("guest", guest);

        return "editguest";
    }

}