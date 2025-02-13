package vttp.batch5.paf.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.Model.DirectorStats;
import vttp.batch5.paf.movies.services.MovieService;



@Controller
@RequestMapping("")
public class MainController {

  @Autowired
  MovieService mService;

  // TODO: Task 3
   @GetMapping("/api/summary")
   @ResponseBody
   public ResponseEntity<String> search(@RequestParam("count") Integer numberOfDirectors) {
      int status = 200;
      String body;
      JsonArrayBuilder jab =Json.createArrayBuilder();
      try{
        List<DirectorStats> stats =mService.getProlificDirectors(numberOfDirectors);
        for (DirectorStats d: stats){
            JsonObject jObj = Json.createObjectBuilder()
                                            .add("director_name",d.getDirector_name())
                                            .add("movies_count",d.getMovies_count())
                                            .add("total_revenue",d.getTotal_revenue())
                                            .add("total_budget",d.getTotal_budget())
                                            .add("profit/loss", d.getTotal_revenue()-d.getTotal_budget())
                                            .build();
            jab.add(jObj);
        }
        body = jab.build().toString();
      }
      catch (Exception e){
        body=e.getMessage();
        status= 500;
      }
      
       return ResponseEntity.status(status).body(body);
   }
   

  
  // TODO: Task 4
   @GetMapping("/generateReport")
   public String generateReport(@RequestParam String name, @RequestParam String batch) {
      //call the method to generate the report and redirect the user to the report page
      //user should submit a form with the params here before we can generate
      mService.generatePDFReport(name,batch);
      System.out.println("Generating pdf report...");
       return new String();

      //
   }
   

}
