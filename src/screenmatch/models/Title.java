package screenmatch.models;
import java.util.List;

public class Title {
    protected String title;
    protected String year;
    protected String rated;
    protected String genre;
    protected String runtime;
    protected String director;
    protected String writer;
    protected String actors;
    protected String country;
    protected String type;
    protected List<String> ratings;
    public Title(Omdb searchResult, List<String> ratings) {
        this.title = searchResult.title();
        this.year = searchResult.year();
        this.rated = searchResult.rated();
        this.genre = searchResult.genre();
        this.runtime = searchResult.runtime();
        this.director = searchResult.director();
        this.writer = searchResult.writer();
        this.actors = searchResult.actors();
        this.country = searchResult.country();
        this.type = searchResult.type();
        this.ratings = ratings;
    }

    public String printRatings (){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< this.ratings.size(); i++){
            sb.append(this.ratings.get(i));
            if (i < this.ratings.size() - 1) {
                sb.append("; ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
