package com.bruno.imageuploader.web.form;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ImageUploadFormValidatorTest {


    private MultipartFile file;
    private MultipartFile additionalfile;
    @Before
    public void setUp() throws Exception {

        file = mock(MultipartFile.class);

        additionalfile = mock(MultipartFile.class);
    }

    @Test
    public void missingFieldsAndValidImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();

        form.setDefaultName(true);
        BindingResult result = mock(BindingResult.class);

        form.setFile(file);
        form.setAdditionalFile(additionalfile); when(additionalfile.isEmpty()).thenReturn(true);
        when(file.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));

        validator.validate(form,result);

        verify(result,times(1)).rejectValue("caption",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(1)).rejectValue("altTag",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(0)).rejectValue("file",ImageUploadFormValidator.ERROR_CODE_FILE_NOT_IMAGE,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FILE_NOT_IMAGE);



    }

    @Test
    public void notMissingFieldsAndValidImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();


        form.setDefaultName(true);
        form.setAltTag("alt");
        form.setCaption("caption");
        BindingResult result = mock(BindingResult.class);

        when(result.getFieldValue("caption")).thenReturn("caption");
        when(result.getFieldValue("altTag")).thenReturn("alt");
        form.setFile(file);
        form.setAdditionalFile(additionalfile);
        when(additionalfile.isEmpty()).thenReturn(true);
        when(file.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));

        validator.validate(form,result);

        verify(result,times(0)).rejectValue("caption",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(0)).rejectValue("altTag",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(0)).rejectValue("file",ImageUploadFormValidator.ERROR_CODE_FILE_NOT_IMAGE,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FILE_NOT_IMAGE);



    }

    @Test
    public void invalidImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();

        BindingResult result = mock(BindingResult.class);

        form.setFile(file);
        form.setAdditionalFile(additionalfile); when(additionalfile.isEmpty()).thenReturn(true);
        when(file.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("test.txt").toURI())));


        validator.validate(form,result);

        verify(result,times(0)).rejectValue("caption",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(0)).rejectValue("altTag",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(1)).rejectValue("file",ImageUploadFormValidator.ERROR_CODE_FILE_NOT_IMAGE,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FILE_NOT_IMAGE);



    }

    @Test
    public void invalidAdditionalImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();

        BindingResult result = mock(BindingResult.class);

        form.setFile(file);
        form.setAdditionalFile(additionalfile);
        when(additionalfile.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("test.txt").toURI())));
        when(file.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));


        validator.validate(form,result);
        verify(result,times(1)).rejectValue("additionalFile",ImageUploadFormValidator.ERROR_CODE_FILE_NOT_IMAGE,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FILE_NOT_IMAGE);



    }

    @Test
    public void missingFieldsForAdditionalAndValidAdditionalImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();

        form.setDefaultName(true);
        BindingResult result = mock(BindingResult.class);

        form.setFile(file);
        form.setDefaultNameForAdditionalImage(true);
        form.setAdditionalFile(additionalfile);
        when(file.isEmpty()).thenReturn(true);
        when(additionalfile.isEmpty()).thenReturn(false);
        when(additionalfile.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));

        validator.validate(form,result);

        verify(result,times(1)).rejectValue("captionForAdditionalImage",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(1)).rejectValue("altTagForAdditionalImage",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);


    }

    @Test
    public void notMissingFieldsForAdditionalAndValidAdditionalImage() throws Exception {

        ImageUploadFormValidator validator = new ImageUploadFormValidator();
        ImageUploadForm form = new ImageUploadForm();

        form.setDefaultName(true);
        BindingResult result = mock(BindingResult.class);

        form.setFile(file);
        form.setDefaultNameForAdditionalImage(true);
        form.setAdditionalFile(additionalfile);
        form.setAltTagForAdditionalImage("alt");
        form.setCaptionForAdditionalImage("caption");
        when(result.getFieldValue("captionForAdditionalImage")).thenReturn("caption");
        when(result.getFieldValue("altTagForAdditionalImage")).thenReturn("alt");
        when(file.isEmpty()).thenReturn(true);
        when(additionalfile.isEmpty()).thenReturn(false);
        when(additionalfile.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));

        validator.validate(form,result);

        verify(result,times(0)).rejectValue("captionForAdditionalImage",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);

        verify(result,times(0)).rejectValue("altTagForAdditionalImage",ImageUploadFormValidator.ERROR_CODE_FIELD_MISSING,null,
                ImageUploadFormValidator.DEFAULT_MESSAGE_FIELD_MISSING);


    }
}