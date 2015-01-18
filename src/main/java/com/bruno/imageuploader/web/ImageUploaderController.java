package com.bruno.imageuploader.web;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.service.ImageUploadService;
import com.bruno.imageuploader.web.form.ImageUploadForm;
import com.bruno.imageuploader.web.form.ImageUploadFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class ImageUploaderController {


    @Autowired
    public ImageUploaderController(ImageUploadFormValidator validator,ImageUploadService service){
        this.validator = validator;
        this.service = service;
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute("form") ImageUploadForm form,
                                   BindingResult bindingResult,
                                   Model model){
        //validation
        validator.validate(form,bindingResult);

        if(bindingResult.hasErrors()) {
            return "upload";
        }
        //saving files
        try {
            List<Image> results = new ArrayList<Image>();
            results.add(createImageInstance(form));

            if(!form.getAdditionalFile().isEmpty()) {
                results.add(createAdditionalImageInstance(form));
            }

            service.save(results);
            model.addAttribute("success","Image Saved");

        } catch (Exception e) {
            model.addAttribute("error","Error in saving images");

        }

      return "upload";
    }

    private Image createImageInstance(ImageUploadForm form) throws IOException {
        return createImage(form.getFile().getOriginalFilename(),
                form.getCaption(),
                form.getAltTag(),
                form.getFile().getBytes(),
                form.isDefaultName());
    }


    private Image createAdditionalImageInstance(ImageUploadForm form) throws IOException {
        return  createImage(form.getAdditionalFile().getOriginalFilename(),
                form.getCaptionForAdditionalImage(),
                form.getAltTagForAdditionalImage(),
                form.getAdditionalFile().getBytes(),
                form.isDefaultNameForAdditionalImage());

    }
    private Image createImage(String filename,String caption,String altTag,byte[] content, boolean isDefaultName) {
        String fileName = generateFileName(filename,
                caption,altTag,isDefaultName);

        return new Image.ImageBuilder().
                withAltTag(altTag).
                withCaption(caption).
                withContent(content).
                withName(fileName).build();
    }

    private String generateFileName(String fileName,String captionForAdditionalImage, String altTagForAdditionalImage, boolean useDefaultName) {
        if(useDefaultName)
            return altTagForAdditionalImage+ "/" +captionForAdditionalImage;
        else
            return fileName;

    }

    private final ImageUploadFormValidator validator;
    private final ImageUploadService service;


}
