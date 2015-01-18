package com.bruno.imageuploader.web;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.service.ImageUploadService;
import com.bruno.imageuploader.web.form.ImageUploadForm;
import com.bruno.imageuploader.web.form.ImageUploadFormValidator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.bruno.imageuploader.web.UtilController.checkImagesNumberLimit;

@Controller
public class ImageUploaderController {

    private final int maxNumberOfImages;
    Logger logger = LoggerFactory.getLogger(ImageUploaderController.class);


    @Autowired
    public ImageUploaderController(ImageUploadFormValidator validator,ImageUploadService service,
                                   @Value("${image.limit}") int maxNumberOfImages){
        this.maxNumberOfImages = maxNumberOfImages;
        this.validator = validator;
        this.service = service;
    }

    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    public void loadImage(HttpServletResponse response,@PathVariable String imageId){

        response.setContentType("image/jpeg");

        try {
            byte[] image = service.loadImage(imageId);

            ByteArrayInputStream bis = new ByteArrayInputStream(image);
            OutputStream out = response.getOutputStream();
            IOUtils.copy(bis, out);
            out.close();
        } catch (IOException e) {
            logger.error("Error in reading the image with id:"+imageId);
            logger.debug("Error:"+e);

        }
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public String loadImageMetadata(Model model) {

        try {
            model.addAttribute("images",service.loadImageMetadata());
        } catch (IOException e) {
            logger.error("Error in reading the images");
            logger.debug("Error:" + e);
        }

        return "view_images";
    }

    @RequestMapping(value = "/delete/image/{imageId}", method = RequestMethod.POST)
    public String deleteImage(@PathVariable String imageId) {
         service.deleteImage(imageId);
         return "redirect:/images";
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
            model.addAttribute("form",new ImageUploadForm());
            model.addAttribute("success","Image Saved");

        } catch (Exception e) {
            model.addAttribute("error","Error in saving images");

        }

      checkImagesNumberLimit(model, service, maxNumberOfImages);
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
