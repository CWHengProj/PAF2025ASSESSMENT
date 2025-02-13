package vttp.batch5.paf.movies.Model;

public class DirectorStats {
    private String director_name;
    private Integer movies_count;
    private Float total_revenue;
    private Float total_budget;

    
    public DirectorStats(String director_name, Integer movies_count, Float total_revenue, Float total_budget) {
        this.director_name = director_name;
        this.movies_count = movies_count;
        this.total_revenue = total_revenue;
        this.total_budget = total_budget;
    }
    public DirectorStats() {
    }
    public String getDirector_name() {
        return director_name;
    }
    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }
    public Integer getMovies_count() {
        return movies_count;
    }
    public void setMovies_count(Integer movies_count) {
        this.movies_count = movies_count;
    }
    public Float getTotal_revenue() {
        return total_revenue;
    }
    public void setTotal_revenue(Float total_revenue) {
        this.total_revenue = total_revenue;
    }
    public Float getTotal_budget() {
        return total_budget;
    }
    public void setTotal_budget(Float total_budget) {
        this.total_budget = total_budget;
    }

    
}
