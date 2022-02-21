import MovieDB.MovieDB;

import java.io.IOException;

public class MovieDBMain {
    public static void main(String[] args) throws IOException {
        MovieDB movieDB = new MovieDB();
        movieDB.getMovies();
    }
}
