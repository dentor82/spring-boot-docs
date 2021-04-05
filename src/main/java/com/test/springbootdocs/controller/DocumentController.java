package com.test.springbootdocs.controller;

import com.test.springbootdocs.dto.PostDocumentDto;
import com.test.springbootdocs.dto.UserDto;
import com.test.springbootdocs.service.DocumentService;
import com.test.springbootdocs.service.StorageService;
import com.test.springbootdocs.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        ModelAndView retValue = new ModelAndView("document", "document", new PostDocumentDto());

        return retValue;
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public ModelAndView addDocument(@ModelAttribute PostDocumentDto document,
                              BindingResult bindingResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userName = this.userService.findByUsername(authentication.getName());
        document.setUser(userName);
        storageService.store(document.getFile());
        this.documentService.save(document);

        return new ModelAndView("redirect:/");
    }
}
