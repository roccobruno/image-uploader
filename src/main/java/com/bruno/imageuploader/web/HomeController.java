package com.bruno.imageuploader.web;


import com.bruno.imageuploader.service.ImageUploadService;
import com.bruno.imageuploader.web.form.ImageUploadForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.bruno.imageuploader.web.UtilController.checkImagesNumberLimit;

@Controller
public class HomeController {

    private final int maxNumberOfImages;
    private final ImageUploadService service;

    @Autowired
    public HomeController(@Value("${image.limit}")  int maxNumberOfImages,ImageUploadService service) {
        this.maxNumberOfImages = maxNumberOfImages;
        this.service = service;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model model) throws Exception{
        model.addAttribute("form",new ImageUploadForm());
        checkImagesNumberLimit(model, service, maxNumberOfImages);
        return "upload";
    }




    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout(Model model) throws Exception{
        SecurityContextHolder.clearContext();
        return "login";
    }
}
