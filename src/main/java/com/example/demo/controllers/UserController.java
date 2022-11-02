package com.example.demo.controllers;


import com.example.demo.models.InfoPost;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/blog/usermain")
    public String UserMain(Model model)
    {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "blog-usermain";
    }

    @GetMapping("/blog/usermain/useradd")
    public String blogAdd(Model model)
    {
        return "blog-useradd";
    }

    @PostMapping("/blog/usermain/useradd")
    public String blogPostAdd(@RequestParam String login,
                              @RequestParam String password,
                              @RequestParam int phone,
                              @RequestParam float checks,
                              @RequestParam boolean accesss,Model model)
    {
        User user = new User(login, password, phone, checks, accesss);
        userRepository.save(user);
        return "redirect:/blog/usermain";
    }

    @PostMapping("/blog/usermain/useradd/result")
    public String blogResult(@RequestParam String login, Model model)
    {
        List<User> result = userRepository.findByLoginContains(login);
        model.addAttribute("result", result);
        return "blog-useradd";
    }

    @PostMapping("/blog/usermain/useradd/resultnocon")
    public String blogResultNoContains(@RequestParam String login, Model model)
    {
        List<User> resultnocon = userRepository.findByLogin(login);
        model.addAttribute("resultnocon", resultnocon);
        return "blog-useradd";
    }







    @GetMapping("/blog/usermain/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<User> post = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!userRepository.existsById(id)){
            return "redirect:/blog/usermain";
        }
        return "blog-userdetails";
    }

    @GetMapping("/blog/usermain/{id}/edit")
    public String blogEdit(@PathVariable("id") long id, Model model)
    {
        if(!userRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<User> post = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

        return "blog-useredit";
    }

    @PostMapping("/blog/usermain/{id}/edit")
    public String blogPostUpdate(@PathVariable("id") long id,
                                 @RequestParam String login,
                                 @RequestParam String password,
                                 @RequestParam int phone,
                                 @RequestParam float checks,
                                 @RequestParam boolean accesss, Model model)
    {
        User post = userRepository.findById(id).orElseThrow();
        post.setLogin(login);
        post.setPassword(password);
        post.setPhone(phone);
        post.setChecks(checks);
        post.setAccesss(accesss);
        userRepository.save(post);
        return "redirect:../";
    }

    @PostMapping("/blog/usermain/{id}/remove")
    public String blogPostRemove(@PathVariable("id") long id, Model model)
    {
        User post = userRepository.findById(id).orElseThrow();
        userRepository.delete(post);
        return "redirect:/";
    }

}
