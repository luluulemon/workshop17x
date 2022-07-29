package com.visa.workshop17x.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather {
    private static Logger logger = LoggerFactory.getLogger(Conditions.class);

    private String city;
    public List<Conditions> conditions = new LinkedList<>();
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }
    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) 
    {   this.city = city;   }


    public void addCondition(String description, String icon){
        Conditions c = new Conditions();
        c.setDescription(description);
        c.setIcon(icon);
        conditions.add(c);
    }

    public static Weather create(String json) throws IOException{
        Weather w = new Weather();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();
        
            w.city = o.getString("name");
            logger.info("city name > " + w.getCity());
            
                        // use getJsonArray, weather in []
            w.conditions = o.getJsonArray("weather").stream()   // weather has id/main/description/icon
                .map(v -> (JsonObject)v)                // take weather Array and convert to a Json Object    
                .map(v -> Conditions.createJson(v))     // create a conditions String: main, description
                .toList();                              
            
            w.temperature = o.getJsonObject("main").getJsonNumber("temp").toString();
        }
        
        return w;
    }



}
