package vttp.batch5.paf.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.bootstrap.Dataloader;
import vttp.batch5.paf.movies.services.MovieService;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {
	@Autowired
	private Dataloader dLoader;
	@Autowired
	private MovieService mService;
	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "../data/movies_post_2010.zip";
		//NOTE - assumes user knows the path. for deployment to work, user needs to point to the right location.
		// my default path directory points to a zip file in my resources
		if (args.length>0){
			filePath = args[0];
		}
		// run the Dataloader application
		System.out.println(filePath);
		System.out.println("running the data loader...");
		List<JsonObject> jsonList =dLoader.unzipLoader(filePath);
		List<JsonObject> filteredJson = mService.filterOldMovies(jsonList);
		mService.checkandLoad(filteredJson);

		
		

	}

}
