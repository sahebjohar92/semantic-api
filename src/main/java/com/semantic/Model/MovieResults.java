package com.semantic.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MovieResults implements Serializable {

    List<Map<String, List<String>>> friendMovieList;
    List<Map<String, List<String>>> allMovieList;

    public List<Map<String, List<String>>> getFriendMovieList() {
        return friendMovieList;
    }

    public void setFriendMovieList(List<Map<String, List<String>>> friendMovieList) {
        this.friendMovieList = friendMovieList;
    }

    public List<Map<String, List<String>>> getAllMovieList() {
        return allMovieList;
    }

    public void setAllMovieList(List<Map<String, List<String>>> allMovieList) {
        this.allMovieList = allMovieList;
    }
}