package vttp.batch5.paf.movies.Model;

import java.time.LocalDate;

public class Arguments {
    private String imdb_id;
    private float vote_average;
    private int vote_count;
    private LocalDate release_date;
    private float revenue;
    private float budget;
    private int runtime;
    
    public Arguments(String imdb_id, float vote_average, int vote_count, LocalDate release_date, float revenue,
            float budget, int runtime) {
        this.imdb_id = imdb_id;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.release_date = release_date;
        this.revenue = revenue;
        this.budget = budget;
        this.runtime = runtime;
    }
    public Arguments() {
    }
    public String getImdb_id() {
        return imdb_id;
    }
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
    public float getVote_average() {
        return vote_average;
    }
    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
    public int getVote_count() {
        return vote_count;
    }
    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
    public LocalDate getRelease_date() {
        return release_date;
    }
    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }
    public float getRevenue() {
        return revenue;
    }
    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    public float getBudget() {
        return budget;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
        
}
