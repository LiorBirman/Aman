public class Movie implements Comparable<Movie> {
    private String title;
    private double popularity;
    private String posterPath;

    public Movie() {

    }
    public Movie(String title, double popularity, String posterPath) {
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int compareTo(Movie movie) {
        if(this.popularity == movie.popularity)
            return 0;

        if(this.popularity > movie.popularity)
            return 1;

        return -1;
    }
}












//public class Movie {
//    private String title;
//    private String posterURL;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPosterURL() {
//        return posterURL;
//    }
//
//    public void setPosterURL(String posterURL) {
//        this.posterURL = posterURL;
//    }
//}
