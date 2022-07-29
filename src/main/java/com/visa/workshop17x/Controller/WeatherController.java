package com.visa.workshop17x.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.visa.workshop17x.Service.WeatherSvc;
import com.visa.workshop17x.models.Weather;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {
    
    @Autowired
    private WeatherSvc weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required=true) String city, Model model){

        Optional<Weather> opt = weatherSvc.getWeather(city);
        if(opt.isEmpty())
            return "weather";

        model.addAttribute("weather", opt.get());
        return "weather";
    }

}
