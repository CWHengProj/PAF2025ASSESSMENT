package vttp.batch5.paf.movies.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import jakarta.json.JsonObject;

@Repository
public class MongoMovieRepository {
@Autowired
MongoTemplate template;
   public boolean isDataLoadedinMongoDB(String title) {
      /*
        db.imdb.findOne({
         title:"<title>"
        })
       */
      Criteria criteria = Criteria.where("title").is(title);
      Query query = Query.query(criteria);
      Document results = template.findOne(query, Document.class, "imdb");
      if(results.size()>0){
         return true;
      }
      return false;
   }
 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 /*
        db.imdb.insertMany([    
        { imdb_id: "", title:"", directors:"",overview:"",tagline:"",genres:"",imdb_rating:"",imdb_votes:""  },
        { imdb_id: "", title:"", directors:"",overview:"",tagline:"",genres:"",imdb_rating:"",imdb_votes:""  },    
        { imdb_id: "", title:"", directors:"",overview:"",tagline:"",genres:"",imdb_rating:"",imdb_votes:""  },    
         ...
         ]);
  */
 //

 @Transactional
 public void batchInsertMovies(List<JsonObject> mongoFilteredExistsList) {
    // insert them in batches of 25 to run
    // if any errors throw exception so that data will roll back
   List<JsonObject> batchOfMovies = new ArrayList<>();
   for (JsonObject j: mongoFilteredExistsList){
      batchOfMovies.add(j);
      if (batchOfMovies.size()%25==0){
         template.insert(batchOfMovies, "imdb");
         batchOfMovies.clear();   
      }
   }
   template.insert(batchOfMovies, "imdb");
}

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    
 /*
      mongosh use imdb
      db.errors.insert({
		   ids: [{},{},{}],
		   error: "",
		   timestamp: ""
      })
  */
 //
 public void logError(List<String> affectedBatch, LocalDateTime timeStamp, String error) {

   Query query = Query.query(new Criteria());
      Update updateOps = new Update()   
      .set("error",error)
                        .set("errors",error)
                        .set("timestamp",timeStamp)
                        .push("ids").each(affectedBatch.toArray());
   UpdateResult result = template.upsert(query, updateOps, "errors");
   System.out.println(result.getModifiedCount());

 }



 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //


}
