package vttp.batch5.paf.movies.repositories;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.Constants.Queries;
import vttp.batch5.paf.movies.Model.Arguments;

@Repository
public class MySQLMovieRepository {

  @Autowired
  JdbcTemplate template;
  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  @Transactional
  public void batchInsertMovies(List<JsonObject> jsonObjects) {
    //batch insert by 25
    List<Arguments> batchHolder = new ArrayList<>();
    for (JsonObject j: jsonObjects){
      String imdb_id = j.getString("imdb_id");
      float vote_average = Float.parseFloat(j.getString("vote_average"));
      int vote_count = j.getInt("vote_count");
      String rd = j.getString("release_date");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
      LocalDate release_date= LocalDate.parse(rd, formatter);
      float revenue = Float.parseFloat(j.getString("revenue"));
      float budget = Float.parseFloat(j.getString("budget"));
      int runtime = j.getInt("runtime");
      Arguments args = new Arguments(imdb_id,vote_average,vote_count,release_date,revenue,budget,runtime);
      batchHolder.add(args);
      if (((batchHolder.size())%25)==0){
        //perform a batch update
        template.batchUpdate(Queries.ADD_TO_MYSQL,batchHolder);
        batchHolder.clear();
      }
    }
    //perform one more batch update here
    template.batchUpdate(Queries.ADD_TO_MYSQL,batchHolder);
  }

  public boolean isDataLoadedinMySQL(String title) {
    SqlRowSet rs =template.queryForRowSet(Queries.CHECK_IF_DATA_EXISTS, title);
    if(!rs.next()){
      return false;
    }
    return true;
  }
  
  // TODO: Task 3


}
