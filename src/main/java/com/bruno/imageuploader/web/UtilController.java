package com.bruno.imageuploader.web;

import com.bruno.imageuploader.service.ImageUploadService;
import org.springframework.ui.Model;

public class UtilController {

    public static void checkImagesNumberLimit(Model model, ImageUploadService service, int maxNumberOfImages) {
        long imagesAlreadyUploaded = service.countImages();
        if(imagesAlreadyUploaded==maxNumberOfImages)
            model.addAttribute("permitted",false);
        else
            model.addAttribute("permitted",true);
    }


}
