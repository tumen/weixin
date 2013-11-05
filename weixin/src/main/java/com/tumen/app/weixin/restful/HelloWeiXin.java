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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWeiXin {

    private static final Logger logger = LogManager.getLogger(HelloWeiXin.class);

    @RequestMapping(value = "/hellowx/{name}", method = RequestMethod.GET)
    public String greeting(@PathVariable String name) {
        return "index2";
    }

    @RequestMapping(value = "/hellowx", method = RequestMethod.GET)
    public @ResponseBody
    String helloWeiXin(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        logger.error(signature + "," + timestamp + "," + nonce + "," + echostr);
        return signature + "," + timestamp + "," + nonce + "," + echostr;
    }
}
