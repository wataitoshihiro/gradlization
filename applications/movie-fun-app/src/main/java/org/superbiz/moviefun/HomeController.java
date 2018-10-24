package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albums.Album;
import org.superbiz.moviefun.albumsapi.AlbumFixtures;
import org.superbiz.moviefun.albums.AlbumsRepository;
//import org.superbiz.moviefun.movies.Movie;
//import org.superbiz.moviefun.movies.MoviesRepository;
import org.superbiz.moviefun.albumsapi.AlbumInfo;
import org.superbiz.moviefun.albumsapi.AlbumsClient;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;

import java.util.Map;

@Controller
public class HomeController {

//    private final MoviesRepository moviesRepository;
    private final MoviesClient moviesClient;
    private final AlbumsClient albumsClient;
    private final AlbumsRepository albumsRepository;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

//    public HomeController(MoviesRepository moviesRepository, AlbumsBean albumsBean, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
    public HomeController(AlbumsClient albumsClient, MoviesClient moviesClient, AlbumsRepository albumsRepository, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.albumsClient = albumsClient;
        this.moviesClient = moviesClient;
        this.albumsRepository = albumsRepository;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movieInfo : movieFixtures.load()) {
            moviesClient.addMovie(movieInfo);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumsClient.addAlbum(album);
        }

        model.put("org/superbiz/moviefun/movies", moviesClient.getMovies());
        model.put("albums", albumsRepository.getAlbums());

        return "setup";
    }
}
