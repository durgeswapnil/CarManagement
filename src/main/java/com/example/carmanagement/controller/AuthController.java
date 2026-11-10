package com.example.carmanagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @GetMapping("/")
    public String home(HttpSession session) {
        return session.getAttribute("loggedInUser") == null ? "redirect:/login" : "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        return session.getAttribute("loggedInUser") != null ? "redirect:/dashboard" : "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, org.springframework.ui.Model model) {
        // Demo credentials only. Replace with Spring Security and hashed passwords for production.
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
