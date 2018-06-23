package com.it14031380.imageuploder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageUploadController {

    @RequestMapping(value = {"/"})
    public String loadIndex(){
        return "index";
    }
}
