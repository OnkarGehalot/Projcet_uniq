package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	


	@GetMapping("/")
	public String shoage() {
        return "index";  
    }
    

    @GetMapping("/success")
    public String showPage() {
        return "success";  
    }
    
    @GetMapping("/dash")
    public String Page() {
        return "dashboard";  
    }
    

}