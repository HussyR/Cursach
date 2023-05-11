package com.example.cursach.Controllers;

import com.example.cursach.DTO.UserRegistrationDTO;
import com.example.cursach.Models.TableOrder;
import com.example.cursach.Models.User;
import com.example.cursach.Repositories.TableOrderRepository;
import com.example.cursach.Repositories.UserRepository;
import com.example.cursach.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("order")
    public TableOrder order() {
        return new TableOrder();
    }

    @GetMapping("/page1")
    public String getMenuPage1() {
        return "index";
    }
    @GetMapping("/page2")
    public String getMenuPage2() {
        return "index2";
    }
    @GetMapping("/page3")
    public String getMenuPage3() {
        return "index3";
    }
    @GetMapping("/reports")
    public String getReports() { return "index4"; }
    @GetMapping("/about")
    public String getAbout() {
        return "index5";
    }
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        UserRegistrationDTO user = new UserRegistrationDTO();
        model.addAttribute("user", user);
        return "register";
    }
    @GetMapping("/order")
    public String getRecords() { return "record"; }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO user) {
        userService.save(user);
        return "redirect:/registration?success";
    }
    @PostMapping("/order")
    public String createATask(@ModelAttribute("order") TableOrder tableOrder) {

        System.out.println(tableOrder.toString());
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());

        tableOrder.setUser(user);
        tableOrderRepository.save(tableOrder);


        return "redirect:/userOrders";
    }

    @Autowired
    private TableOrderRepository tableOrderRepository;

    @GetMapping("/userOrders")
    public String userOrders(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email);
        var list = tableOrderRepository.findAllByUser(user);

        model.addAttribute("orders", list);

        return "allRecords";
    }

    @GetMapping("/allOrders")
    public String adminOrders(Model model) {
        var list = tableOrderRepository.findAll();
        model.addAttribute("orders", list);
        return "admin";
    }

}
