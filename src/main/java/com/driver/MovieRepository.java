package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository

public class MovieRepository {
    Map<String, Movie> movieData = new HashMap<>();
    Map<String, Director> directorData = new HashMap<>();
    Map<String, String> pairData = new HashMap<>();

    public Boolean addMovie(Movie movie){
        String key = movie.getName();
        movieData.put(key, movie);
        return true;
    }
    public Optional<Movie> getMovieByName(String name){
        if(movieData.containsKey(name)) {
            return Optional.of(movieData.get(name));
        }
        return Optional.empty();
    }


    public Boolean addDirector(Director director) {
        String key = director.getName();
        directorData.put(key, director);
        return true;
    }

    public Optional<Director> getDirectorByName(String name) {
        if (directorData.containsKey(name)){
            return Optional.of(directorData.get(name));
        }
        return Optional.empty();
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        pairData.put(movieName, directorName);
        return "Paired successfully";
    }

    public List<String> getMoviesByItsDirectorName(String directorName) {
        return pairData.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(directorName))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> getAllMovies() {
        return movieData.keySet().stream().collect(Collectors.toList());
    }

    public void deleteDirectorByName(String directorName){
        directorData.remove(directorName);
        pairData.entrySet().removeIf(e -> e.getValue().equals(directorName));
    }

    public void deleteAllDirectors(){
        directorData.clear();
        pairData.clear();
    }

}