package com.test.springbootdocs.controller;

import com.test.springbootdocs.dto.PermissionDto;
import com.test.springbootdocs.dto.UserDto;
import com.test.springbootdocs.entity.Document;
import com.test.springbootdocs.enums.Mask;
import com.test.springbootdocs.service.UserService;
import com.test.springbootdocs.service.impl.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;
    private final UserService userService;

    public PermissionController(PermissionService permissionService, UserService userService) {
        this.permissionService = permissionService;
        this.userService = userService;
    }

    @GetMapping(path = "/list/{docId}")
    public ModelAndView getList(@PathVariable(name = "docId") Long documentId) {
        ModelAndView retValue =
                new ModelAndView("permissions", "command",
                        new PermissionDto(documentId));
        List<PermissionDto> list = this.permissionService.getListPermissions(Document.class, documentId);
        retValue.addObject("list", list);
        retValue.addObject("listUsers", userService.getList());
        retValue.addObject("listPermissions", Mask.values());

        return retValue;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPermission(@ModelAttribute PermissionDto permissionDto,
                            ModelMap model) {

        permissionService.addPermissionForUser(Document.class,
                permissionDto.getDocumentId(),
                permissionDto.getMask().getPermission(),
                permissionDto.getPrincipal());

        return "redirect:/permission/list/" + permissionDto.getDocumentId();
    }
}
