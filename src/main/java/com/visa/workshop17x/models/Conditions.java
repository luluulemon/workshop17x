package com.visa.workshop17x.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Conditions {
    
    private static Logger logger = LoggerFactory.getLogger(Conditions.class);
    
    private String description;
    private String icon;
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static Conditions createJson(JsonObject o){
        Conditions c = new Conditions();
        c.description = "%s - %s".formatted
            (o.getString("main"), o.getString("description"));
        
        return c;

    }

}
