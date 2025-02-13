package vttp.batch5.paf.movies.repositories;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.Constants.Queries;
import vttp.batch5.paf.movies.Model.Arguments;
import vttp.batch5.paf.movies.Model.DirectorStats;

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
    }
 
    template.batchUpdate(Queries.ADD_TO_MYSQL,batchHolder,25,        
    (PreparedStatement ps, Arguments arg) -> {
      ps.setString(1, arg.getImdb_id());
      ps.setFloat(2, arg.getVote_average());
      ps.setInt(3, arg.getVote_count());
      ps.setDate(4, java.sql.Date.valueOf(arg.getRelease_date()));
      ps.setFloat(5, arg.getRevenue());
      ps.setFloat(6, arg.getBudget());
      ps.setInt(7, arg.getRuntime());
    });


  }

  public boolean isDataLoadedinMySQL(String title) {
    SqlRowSet rs =template.queryForRowSet(Queries.CHECK_IF_DATA_EXISTS, title);
    if(!rs.next()){
      return false;
    }
    return true;
  }

  // TODO: Task 3
public List<DirectorStats> getDirectorStats(Integer numberOfDirectors) {
  List<DirectorStats> stats = new ArrayList<>();
  SqlRowSet rs = template.queryForRowSet(Queries.GET_DIRECTOR_DATA, numberOfDirectors);
  while(rs.next()){
    DirectorStats d = new DirectorStats();
    d.setDirector_name(rs.getString("director_name"));
    d.setMovies_count(rs.getInt("movies_count"));
    d.setTotal_budget(rs.getFloat("total_revenue"));
    d.setTotal_revenue(rs.getFloat("total_budget"));
    stats.add(d);
    if (stats.size()==numberOfDirectors){
      break;
    }
  }
  return stats;
}
  


}
