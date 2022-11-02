package com.example.demo.controllers;


import com.example.demo.models.InfoPost;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repo.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OnController {

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping("/blog/info")
    public String info(Model model)
    {
        Iterable<InfoPost> infos = infoRepository.findAll();
        model.addAttribute("infos", infos);
        return "blog-info";
    }

//    @GetMapping("/")
//    public String blogInfo(Model model)
//    {
//        Iterable<InfoPost> infos = infoRepository.findAll();
//        model.addAttribute("infos", infos);
//        return "blog-info";
//    }

    @PostMapping("/blog/info")
    public String infoPostAdd(@RequestParam String shapka,
                              @RequestParam String name,
                              @RequestParam String info,
                              @RequestParam double maintext,
                              @RequestParam int stat, Model model)
    {
        InfoPost infopost = new InfoPost(shapka, name, info, maintext, stat);
        infoRepository.save(infopost);
        return "redirect:/";
    }

    @PostMapping("/blog/info/result")
    public String blogResult(@RequestParam String name, Model model)
    {
        List<InfoPost> result = infoRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "blog-info";
    }

    @PostMapping("/blog/info/resultnocon")
    public String blogResultNoContains(@RequestParam String name, Model model)
    {
        List<InfoPost> resultnocon = infoRepository.findByName(name);
        model.addAttribute("resultnocon", resultnocon);
        return "blog-info";
    }



    @GetMapping("/blog/info/{id}")
    public String infoDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<InfoPost> post = infoRepository.findById(id);
        ArrayList<InfoPost> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!infoRepository.existsById(id)){
            return "redirect:/blog/info";
        }
        return "blog-infodetails";
    }

    @GetMapping("/blog/info/{id}/edit")
    public String blogEdit(@PathVariable("id") long id, Model model)
    {
        if(!infoRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<InfoPost> post = infoRepository.findById(id);
        ArrayList<InfoPost> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

        return "blog-infoedit";
    }

    @PostMapping("/blog/info/{id}/edit")
    public String blogPostUpdate(@PathVariable("id") long id,
                                 @RequestParam String shapka,
                                 @RequestParam String name,
                                 @RequestParam String info,
                                 @RequestParam double maintext,
                                 @RequestParam int stat, Model model)
    {
        InfoPost post = infoRepository.findById(id).orElseThrow();
        post.setShapka(shapka);
        post.setName(name);
        post.setInfo(info);
        post.setMaintext(maintext);
        post.setStat(stat);
        infoRepository.save(post);
        return "redirect:../";
    }

    @PostMapping("/blog/info/{id}/remove")
    public String blogPostRemove(@PathVariable("id") long id, Model model)
    {
        InfoPost post = infoRepository.findById(id).orElseThrow();
        infoRepository.delete(post);
        return "redirect:/";
    }

}
