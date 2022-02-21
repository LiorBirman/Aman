package MovieDB;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MovieDB {

    private static final String API_KEY = "f1a4f22a8f558c9215ae3f85d69e4132";
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String POSTER_PREFIX = "poster_path\":\"";
    private static final String TITLE_PREFIX = "original_title\":\"";
    private static final String POPULARITY_PREFIX = "popularity\":";

    public void getMovies() throws IOException {

        ArrayList<Movie> movies = new ArrayList<>();
        int movieID;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nExample for notable movies IDs:");
        System.out.println("1)  ID =   120:   Lord Of The Rings: The Fellowship of the Ring, Peter Jackson, J.R.R. Tolkien");
        System.out.println("2)  ID =   121:   Lord Of The Rings: The Two Towers, Peter Jackson, J.R.R. Tolkien");
        System.out.println("3)  ID =   122:   Lord Of The Rings:The Return of the King, Peter Jackson, J.R.R. Tolkien");
        System.out.println("4)  ID = 68718:   Django Unchained, Quentin Tarantino");
        System.out.print("\nEnter desired movie's ID or enter \'0\' to stop: ");

        // Get ID from user
        // Create MovieDB.Movie object
        // Download & rename movie poster
        while ((movieID = sc.nextInt())!= 0) {
            Movie currentMovie = createMovie(movieID);
            System.out.println("Movie title: " + currentMovie.getTitle());
            downloadPoster(currentMovie.getPosterPath(), currentMovie.getTitle());
            movies.add(currentMovie);

            System.out.print("\nEnter desired movie's ID or enter \'0\' to stop: ");
        }

        System.out.print("\n\n\n\n\n\nYOUR HIGHEST RATED MOVIE IS: "+ Collections.max(movies).getTitle() + "\n");
    }

    // Connect to TMDB using API key
    // Request movie text by ID
    // Parse movie text to variables
    // Return MovieDB.Movie object
    private static Movie createMovie(int movieID) throws IOException {
        String fullURL = BASE_URL + movieID + "?api_key=" + API_KEY;
        URL url = new URL(fullURL);
        URLConnection urlConnection = url.openConnection();

        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine;
        Movie currentMovie = new Movie();
        while ((inputLine = bufferedReader.readLine()) != null) {

            // Parse poster path
            String posterStartingString = inputLine.substring(inputLine.indexOf(
                    POSTER_PREFIX) + POSTER_PREFIX.length());
            String posterURL = posterStartingString.substring(0, posterStartingString.indexOf("\",\""));


            // Parse title path
            String titleStartingString = inputLine.substring(inputLine.indexOf(
                    TITLE_PREFIX) + TITLE_PREFIX.length());
            String titleURL = titleStartingString.substring(0, titleStartingString.indexOf("\",\""));

            // Parse popularity path
            String popularityStartingString = inputLine.substring(inputLine.indexOf(
                    POPULARITY_PREFIX) + POPULARITY_PREFIX.length());
            String popularityURL = popularityStartingString.substring(0, popularityStartingString.indexOf(",\""));

            // Set movie all features from parsed text
            currentMovie.setTitle(titleURL);
            currentMovie.setPopularity(Double.parseDouble(popularityURL));
            currentMovie.setPosterPath(posterURL);

        }

        bufferedReader.close();
        return currentMovie;
    }

    // Connect to poster's URL on TMDB using poster's key
    // Create directory C:\temp if not exists
    // Download the poster to C:\temp if not exists & rename to movie title
    private static void downloadPoster(String moviePosterKey, String movieTitle) throws MalformedURLException {
        URL posterURL = new URL("https://image.tmdb.org/t/p/w185/" + moviePosterKey);
        File directory = new File("C:/temp");

        try (InputStream in = posterURL.openStream()) {
            // Create directory if not exists
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Created directory C:/temp");
            }

            // Download poster if not exists
            Path downloadPath = Path.of("C:/temp/" + moviePosterKey);
            if (!(new File("C:/temp/" + movieTitle + ".jpg").exists())) {
                Files.copy(in, downloadPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Poster downloaded to: " + downloadPath);
            }
            else System.out.println("Poster already exists at: " + downloadPath);

            // Rename poster to movie title
            new File("C:/temp/" + moviePosterKey).renameTo(new File(
                    "C:/temp/" + movieTitle + ".jpg"));


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not download poster :(\nPlease try again");
        }
    }
}