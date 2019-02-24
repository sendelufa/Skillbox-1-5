/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sendel
 */
public class Cookies {
    Cookie[] cookies = {};
    HashMap<String, String> reqCookies = new HashMap<>();
    public Cookies(Cookie[] cookies){
        if (cookies != null){
        this.cookies = cookies.clone();
        }
    }
    
    public void setCookies(Map<String, String[]> cookiesMap, HttpServletResponse resp) {
        for (String key : cookiesMap.keySet()) {

            String value = cookiesMap.get(key)[0];

            try {
                String value_enc = java.net.URLEncoder.encode(value, "UTF-8");

                resp.addCookie(new Cookie(key, value_enc));
                reqCookies.put(key, value);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Cookies.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
      public void getCookies(HttpServletRequest req){
          reqCookies.clear();
   for (Cookie c : cookies){
       try {
           String value = java.net.URLDecoder.decode(c.getValue(), "UTF-8");
           reqCookies.put(c.getName(), value);
           
       } catch (UnsupportedEncodingException ex) {
           Logger.getLogger(Cookies.class.getName()).log(Level.SEVERE, null, ex);
       }

           
        }
   
   
    }

    public HashMap<String, String> getReqCookies() {
        return reqCookies;
    }
      
      
}
