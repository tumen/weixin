/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2010 All Rights Reserved.
 */
package com.tumen.app.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




/**
 * A index controller.
 */
@Controller
@RequestMapping("/index.html")
public class IndexController{
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest httpRequest, ModelMap modelMap) {
        modelMap.put("nick", "test");
        return "index";
    }
}
