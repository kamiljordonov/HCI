package org.kamil.schedule.controller;

import org.kamil.schedule.model.Product;
import org.kamil.schedule.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController  {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/home")
    public String getHomePage(ModelMap model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/")
    public String getPage(){
        return "login";
    }


    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/aboutus")
    public String getAboutUsPage (){
      return "aboutus";
    }
}
