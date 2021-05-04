package com.test.springbootdocs.controller;

import com.test.springbootdocs.service.DocumentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final DocumentService documentService;

    public MainController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView indexPage(@RequestParam(value = "sort", required = false) String columnSort) {
        ModelAndView retValue = new ModelAndView();
        List<String> columns = new ArrayList<>();
        Optional.ofNullable(columnSort)
                .map(columns::add);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        retValue.addObject("documents",
                this.documentService.getList(authentication.getName(),
                columns.toArray(new String[0])));
        retValue.setViewName("index");

        return retValue;
    }
}
