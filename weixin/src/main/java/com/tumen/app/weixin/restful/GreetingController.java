/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tumen.app.weixin.restful;

/**
 *
 * @author xin.lx
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public String greeting(@PathVariable String name) {
        return "index2";
    }


    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> helloWorld1() {
        String message = "Hello World, Spring 3.0";
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", message);
        map.put("2", "22222");
        return map;
    }
}
