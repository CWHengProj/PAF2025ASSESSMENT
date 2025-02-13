package vttp.batch5.paf.movies.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;
@Service
public class MovieService {

  @Autowired
  private MySQLMovieRepository sqlRepo;
  @Autowired
  private MongoMovieRepository mongoRepo;


  public void checkandLoad(List<JsonObject> jobjList){
    //perform a check whether the files here have been loaded into the database
    List<JsonObject> mongoFilteredExistsList = new ArrayList<>();
    List<JsonObject> sqlFilteredExistsList = new ArrayList<>();
    
    for (JsonObject j : jobjList){
      String title = j.getString("title");
      //make query here to see if there are any matches
      if(!mongoRepo.isDataLoadedinMongoDB(title)){
        mongoFilteredExistsList.add(j);
      }
      if(!sqlRepo.isDataLoadedinMySQL(title)){
        sqlFilteredExistsList.add(j);
      } 
    }
    //collate and send over
    try{
      insertMovies(sqlFilteredExistsList, sqlFilteredExistsList);
    }
    catch (Exception e){
      List<String> affectedBatch = new ArrayList<>();
      affectedBatch.add("Get the batches affected here");
      LocalDateTime timeStamp = LocalDateTime.now();
      String error = e.getMessage();
      mongoRepo.logError(affectedBatch,timeStamp,error);
    }  
  
  }

  @Transactional
  public void insertMovies(List<JsonObject> sqlList, List<JsonObject> mongoList){
    sqlRepo.batchInsertMovies(sqlList);

    mongoRepo.batchInsertMovies(mongoList);
  }

public List<JsonObject> filterOldMovies(List<JsonObject> jsonList) {
  // filter out movies that are before 2018 (not including 2018)
  // use release_date to find the values needed
  // input any erroneous from each document with 0 , "" or false, if they are number, string or boolean
  List<JsonObject> filteredList = new ArrayList<>();
  for (JsonObject j : jsonList){
    String releaseDate = j.getString("release_date");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    LocalDate date = LocalDate.parse(releaseDate, formatter);
    LocalDate limit = LocalDate.parse("2017-12-31");
    if(date.isAfter(limit)){
      filteredList.add(j);
    }
  }
  return filteredList;
}

  

  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void getProlificDirectors() {
    
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

  }




}
