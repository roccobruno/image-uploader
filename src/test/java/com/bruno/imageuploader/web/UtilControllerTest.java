package com.bruno.imageuploader.web;

import com.bruno.imageuploader.service.ImageUploadService;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilControllerTest {

    @Test
    public void testCheckImagesNumberLimit() throws Exception {

        ModelMap model = new ExtendedModelMap();

        ImageUploadService service = mock(ImageUploadService.class);
        when(service.countImages()).thenReturn(2l);
        UtilController.checkImagesNumberLimit((org.springframework.ui.Model) model,service,2);

        assertFalse((Boolean) model.get("permitted"));
    }

    @Test
    public void testCheckImagesNumberLimitResultOk() throws Exception {

        ModelMap model = new ExtendedModelMap();

        ImageUploadService service = mock(ImageUploadService.class);
        when(service.countImages()).thenReturn(2l);
        UtilController.checkImagesNumberLimit((org.springframework.ui.Model) model,service,3);

        assertTrue((Boolean) model.get("permitted"));
    }


}