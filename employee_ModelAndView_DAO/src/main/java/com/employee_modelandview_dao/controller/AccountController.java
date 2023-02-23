package com.employee_modelandview_dao.controller;

import com.employee_modelandview_dao.model.Account;
import com.employee_modelandview_dao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private static final String EMAIL_REGEX = "^[A-Z_.a-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    @Autowired
    HttpSession session;

    @Autowired
    AccountService accountService;

    @GetMapping("login")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public String accountLogin(@RequestParam String username, String password, Model model) {
        if (!accountService.checkLogin(username, password)) {
            model.addAttribute("loginFail", "Sai thông tin tài khoản");
            return "login";
        }
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/home.html";
    }
}
