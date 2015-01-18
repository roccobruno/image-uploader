package com.bruno.imageuploader.web;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.service.ImageUploadService;
import com.bruno.imageuploader.web.form.ImageUploadForm;
import com.bruno.imageuploader.web.form.ImageUploadFormValidator;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ImageUploaderControllerTest {

    @Test
    public void testHandleFileUpload() throws Exception {

        ImageUploadFormValidator validator = mock(ImageUploadFormValidator.class);
        ImageUploadService service = mock(ImageUploadService.class);
        BindingResult result = mock(BindingResult.class);
        ModelMap model = new ExtendedModelMap();

        ImageUploaderController controller = new ImageUploaderController(validator,service);

        ImageUploadForm form = buildMockForm();

        String res = controller.handleFileUpload(form,result, (org.springframework.ui.Model) model);
        List<Image> images = new ArrayList<>();
        images.add(createImage(form));
        images.add(createAdditionalImage(form));
        verify(service, times(1)).save(images);
        assertNotNull(model.get("success"));

        assertEquals("upload", res);

    }

    private Image createAdditionalImage(ImageUploadForm form) throws IOException {
        return new Image.ImageBuilder().withAltTag(form.getAltTagForAdditionalImage()).withCaption(form.getCaptionForAdditionalImage()).
                withContent(form.getFile().getBytes()).withName(form.getAltTagForAdditionalImage()+"/"+form.getCaptionForAdditionalImage()).build();
    }

    private Image createImage(ImageUploadForm form) throws IOException {
        return new Image.ImageBuilder().withAltTag(form.getAltTag()).withCaption(form.getCaption()).
                withContent(form.getFile().getBytes()).withName(form.getFile().getOriginalFilename()).build();
    }

    private ImageUploadForm buildMockForm() throws IOException, URISyntaxException {
        ImageUploadForm form =new ImageUploadForm();
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(new FileInputStream(new File(ClassLoader.getSystemResource("image.jpg").toURI())));

        when(file.isEmpty()).thenReturn(false);
        form.setFile(file);
        form.setDefaultNameForAdditionalImage(true);
        form.setAdditionalFile(file);
        form.setCaption("caption");
        form.setAltTag("alt");


        return form;
    }
}