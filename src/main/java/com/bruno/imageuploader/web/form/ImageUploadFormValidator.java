package com.bruno.imageuploader.web.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageUploadFormValidator implements Validator {
    public static final String ERROR_CODE_FIELD_MISSING = "upload.image.errors.field.missing";
    public static final String DEFAULT_MESSAGE_FIELD_MISSING = "Field needed to be used as default name";
    public static final String ERROR_CODE_FILE_NOT_IMAGE = "upload.image.errors.file.not.image";
    public static final String DEFAULT_MESSAGE_FILE_NOT_IMAGE = "The file selected is not an image (only BMP, GIF, JPG and PNG types are supported)";


    @Override
    public boolean supports(Class<?> clazz) {
        return ImageUploadForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ImageUploadForm form =  (ImageUploadForm)target;

        if(form.isDefaultName()) {
            validateField(errors,"caption");
            validateField(errors, "altTag");
        }

        if(form.getFile().isEmpty()) {
            errors.rejectValue("file", "required.file", "no file specified");
        } else {
            //check if the file is an image
            validateImage(errors,"file", form.getFile());
        }

        //check additionalImage
        if(!form.getAdditionalFile().isEmpty()) {

            validateImage(errors,"additionalFile", form.getAdditionalFile());

            if(form.isDefaultNameForAdditionalImage()) {
                validateField(errors,"captionForAdditionalImage");
                validateField(errors, "altTagForAdditionalImage");
            }
        }


    }

    private void validateField(Errors errors, String fieldName) {

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, fieldName, ERROR_CODE_FIELD_MISSING, DEFAULT_MESSAGE_FIELD_MISSING);
    }

    private void validateImage(Errors errors,String fieldName, MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null) {

                rejectFile(errors,fieldName);
            }
        } catch (IOException e) {
            rejectFile(errors,fieldName);
        }
    }

    private void rejectFile(Errors errors,String fieldName) {

        errors.rejectValue(fieldName, ERROR_CODE_FILE_NOT_IMAGE, DEFAULT_MESSAGE_FILE_NOT_IMAGE);
    }
}
