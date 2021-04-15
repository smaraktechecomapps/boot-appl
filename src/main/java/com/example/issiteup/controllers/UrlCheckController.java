package com.example.issiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    //make this REST controller
    private final String IS_SITE_UP = "Site is UP";
    private final String IS_SITE_DN = "Site is Down";
    private final String INC_URL = "Site is incorrect";
    

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url)
    {
         String returnMessahe = "";
         try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responcode1 = conn.getResponseCode();
            int responcode = conn.getResponseCode() / 100 ;
            System.out.println("responcode1 is "+ responcode1);
            System.out.println("responcode is "+ responcode);
            
            if (responcode1 == 200 )
            {
                returnMessahe = IS_SITE_UP;
            }
            else{
                returnMessahe = IS_SITE_DN;
            }

          /*  if (responcode != 2 || responcode != 3)
            {
                returnMessahe = IS_SITE_DN;
            }
            else{
                returnMessahe = IS_SITE_UP;
            }*/
        } catch (MalformedURLException e) {
            returnMessahe = INC_URL;
            //e.printStackTrace();
        } catch(IOException e){
            returnMessahe = IS_SITE_DN;
        }
          


         return returnMessahe;
    }
}
