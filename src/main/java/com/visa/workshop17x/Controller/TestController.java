// package com.visa.workshop17x.Controller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.MediaType;
// import org.springframework.http.RequestEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.visa.workshop17x.models.Conditions;

// @RestController
// public class TestController {
//     private static Logger logger = LoggerFactory.getLogger(Conditions.class);

//     @RequestMapping(path="/", consumes="application/json", produces = "application/json")
//     public RequestEntity<Conditions> GetWeather(){
//         RequestEntity<String> req = RequestEntity
//             .get(url) 
//             .accept(MediaType.APPLICATION_JSON)
//             .build();
//     }

    
// }
