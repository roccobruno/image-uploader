package com.bruno.imageuploader.web;


import com.bruno.imageuploader.web.form.ImageUploadForm;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model model) throws Exception{
        model.addAttribute("form",new ImageUploadForm());
        return "upload";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout(Model model) throws Exception{
        SecurityContextHolder.clearContext();
        return "login";
    }
}
