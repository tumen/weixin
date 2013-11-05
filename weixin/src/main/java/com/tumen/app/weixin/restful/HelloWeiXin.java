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
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWeiXin {

    private static final Logger logger = LogManager.getLogger(HelloWeiXin.class);

    private final static String TOKEN = "helen";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

//    @RequestMapping(value = "/hellowx/{name}", method = RequestMethod.GET)
//    public String greeting(@PathVariable String name) {
//        return "index2";
//    }

    @RequestMapping(value = "/hellowx", method = RequestMethod.GET)
    public @ResponseBody
    void helloWeiXin(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr, HttpServletResponse response) throws IOException {
        logger.error(signature + "," + timestamp + "," + nonce + "," + echostr);
        String[] str = {TOKEN, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = this.encode("SHA1", bigStr);
            logger.error("digest:"+digest);
        // 确认请求来至微信
        if (digest.equals(signature)) {
            logger.error("echostr:"+echostr);
            response.getWriter().print(echostr);
        }
    }

    /**
     * encode string
     *
     * @param algorithm
     * @param str
     * @return String
     */
    private String encode(String algorithm, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Takes the raw bytes from the digest and formats them correct.
     * @param bytes
     * the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    

}
