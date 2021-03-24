package com.test.demo.controller;

import com.test.demo.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final DocumentService documentService;

    public MainController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView welcomePage(Model model) {
        ModelAndView retValue = new ModelAndView();
        retValue.setViewName("index");
        return retValue;
    }
}
