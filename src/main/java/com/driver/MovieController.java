package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    //addMovie()
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        try {
            Boolean added = movieService.addMovie(movie);
            return new ResponseEntity("Movie added successfully", HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException ex) {
            return new ResponseEntity("Unable to add movie as it already exists", HttpStatus.valueOf(400));
        }
    }

    //addDirector()
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        try {
            Boolean added = movieService.addDirector(director);
            return new ResponseEntity("Director added successfully", HttpStatus.CREATED);
        } catch (DirectorAlreadyExistsException ex) {
            return new ResponseEntity("Unable to add director as it already exists", HttpStatus.valueOf(400));
        }
    }

    //addMovieDirectorPair()
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName) {
        try {
            String added = movieService.addMovieDirectorPair(movieName, directorName);
            return new ResponseEntity<>(added, HttpStatus.CREATED);
        } catch (PairAlreadyExistsException ex) {
            return new ResponseEntity("Unable to add pair as it already exists", HttpStatus.valueOf(400));
        }
    }

    //getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name) {
        try {
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (MovieNotFoundException ex) {
            return new ResponseEntity("Movie not found", HttpStatus.valueOf(500));
        }
    }

    //getDirectorByName
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name) {
        try {
            Director director = movieService.getDirectorByName(name);
            return new ResponseEntity(director, HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return new ResponseEntity("Director not found", HttpStatus.valueOf(500));
        }
    }

    //getMoviesByDirectorName
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName) {
        try {
            List<String> ans = movieService.getMoviesByItsDirectorName(directorName);
            return new ResponseEntity<>(ans, HttpStatus.OK);
        } catch (PairNotFoundException ex) {
            return new ResponseEntity("Pair not found", HttpStatus.valueOf(500));
        }
    }

    //findAllMovies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> ans = movieService.findAllMovies();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    //deleteDirectorByName
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String directorName) {
        try {
            movieService.deleteDirectorByName(directorName);
            return new ResponseEntity("director deleted successfully", HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //deleteAllDirectors
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        try {
            movieService.deleteAllDirectors();
            return new ResponseEntity("All director deleted successfully", HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}