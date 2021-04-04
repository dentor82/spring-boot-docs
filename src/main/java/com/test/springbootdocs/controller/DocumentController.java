package com.test.springbootdocs.controller;

import com.test.springbootdocs.dto.PostDocumentDto;
import com.test.springbootdocs.dto.UserDto;
import com.test.springbootdocs.service.DocumentService;
import com.test.springbootdocs.service.StorageService;
import com.test.springbootdocs.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {
    private final DocumentService documentService;
    private final UserService userService;
    private final StorageService storageService;

    public DocumentController(DocumentService documentService, UserService userService, StorageService storageService) {
        this.documentService = documentService;
        this.userService = userService;
        this.storageService = storageService;
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public ModelAndView document() {
        ModelAndView retValue = new ModelAndView("document", "command", new PostDocumentDto());
        retValue.addObject("list", userService.getList());

        return retValue;
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public String addDocument(@RequestParam(value = "author") Long authorId,
                            @ModelAttribute PostDocumentDto document,
                            ModelMap model) {

        document.setUser(new UserDto(authorId));
        storageService.store(document.getFile());
        this.documentService.save(document);

        return "redirect:/";
    }
}
