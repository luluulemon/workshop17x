package com.visa.workshop17x.Service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.visa.workshop17x.models.Weather;

@Service
public class WeatherSvc {
    private static Logger logger = LoggerFactory.getLogger(WeatherSvc.class);
    private static String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${open.weather.map}")       // pluck value from application.properties
    private String apiKey;

    private boolean hasKey;

    @PostConstruct      // method to execute after dependency injection, and before init
    private void init(){
        hasKey = null != apiKey;
        logger.info(" >>> API KEY set: " + hasKey);     // logger check if apiKey is present

    }

    public Optional<Weather> getWeather (String city){      // returns Optional, so return page will not crash
                                                            // if API endpoint not working
        String weatherUrl = UriComponentsBuilder.fromUriString(URL)
            .queryParam("appid", apiKey)
            .queryParam("units", "metric")
            .queryParam("q", city.replaceAll(" ", "+"))
            .toUriString();

        logger.info( ">>> Compleate Weather URI API add " + weatherUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try
        {   resp = template.getForEntity(weatherUrl, String.class);
            Weather w = Weather.create(resp.getBody());  // create weather object from the response body
            return Optional.of(w); 
        }catch(Exception e)
        {   logger.error(e.getMessage());   
            e.printStackTrace(); }

        return Optional.empty();

    }
}
