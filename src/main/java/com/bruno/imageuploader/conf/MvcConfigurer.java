package com.bruno.imageuploader.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Locale;

/**
 * Created by ji.patel on 06/01/2015.
 */


@Configuration
@EnableWebMvc
public class MvcConfigurer extends WebMvcConfigurerAdapter {


    @Bean(name="viewResolver")
    public ViewResolver getJspViewResolver() {
        TilesViewResolver resolver = new TilesViewResolver();
        resolver.setOrder(1);
        return resolver;
    }
    @Bean(name="tilesConfigurer")
    public TilesConfigurer getTilesConfig() {
        TilesConfigurer c = new TilesConfigurer();
        c.setDefinitions("/WEB-INF/tiles/layout.xml");
        return c;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.UK);
        return slr;
    }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {



        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }

    }

}
