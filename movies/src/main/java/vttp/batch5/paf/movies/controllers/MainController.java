package vttp.batch5.paf.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp.batch5.paf.movies.services.MovieService;



@Controller
@RequestMapping("")
public class MainController {

  @Autowired
  MovieService mService;

  // TODO: Task 3
   @GetMapping("/search")
   @ResponseBody
   public ResponseEntity<String> search() {
      int status = 200;
      String body;
      String queryResults = mService.getProlificDirectors();
      try {body= Json.createArrayBuilder()
                      .add()
                      .add()
                      .add()
                      .add()
                      .add()
                      .build().toString;}
      catch (Exception e){
        body=e.getMessage();
        status= 500;
      }
      String body = jab.toString();
       return ResponseEntity.status(status).body(body);
   }
   

  
  // TODO: Task 4


}
