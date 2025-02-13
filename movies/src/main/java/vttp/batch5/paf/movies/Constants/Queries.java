package vttp.batch5.paf.movies.Constants;

public class Queries {
    public static final String CHECK_IF_DATA_EXISTS="""
            select * from imdb where title like ?;
            """;
    public static final String ADD_TO_MYSQL = """
            insert into imdb (imdb_id,vote_average,vote_count,release_date,revenue,budget,runtime)
                        values(?,?,?,?,?,?,?);
            """;
}
