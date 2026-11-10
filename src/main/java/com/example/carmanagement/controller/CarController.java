package com.example.carmanagement.controller;

import com.example.carmanagement.model.Car;
import com.example.carmanagement.service.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    private boolean loggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!loggedIn(session)) return "redirect:/login";
        model.addAttribute("carCount", carService.count());
        model.addAttribute("username", session.getAttribute("loggedInUser"));
        return "dashboard";
    }

    @GetMapping("/cars/add")
    public String addPage(HttpSession session, Model model) {
        if (!loggedIn(session)) return "redirect:/login";
        model.addAttribute("car", new Car());
        return "add-car";
    }

    @PostMapping("/cars/add")
    public String addCar(@Valid @ModelAttribute("car") Car car, BindingResult result,
                         @RequestParam("image") MultipartFile image,
                         HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (!loggedIn(session)) return "redirect:/login";
        if (result.hasErrors()) return "add-car";
        try {
            carService.save(car, image);
            redirectAttributes.addFlashAttribute("success", "Car added successfully.");
            return "redirect:/cars";
        } catch (IOException ex) {
            model.addAttribute("uploadError", "Could not save the image. Please try another file.");
            return "add-car";
        }
    }

    @GetMapping("/cars")
    public String viewCars(HttpSession session, Model model) {
        if (!loggedIn(session)) return "redirect:/login";
        model.addAttribute("cars", carService.findAll());
        return "view-cars";
    }

    @PostMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!loggedIn(session)) return "redirect:/login";
        carService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Car deleted.");
        return "redirect:/cars";
    }
}
