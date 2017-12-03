package com.semantic.Controllers;

import com.semantic.Constants.ApiConstants;
import com.semantic.Model.BookResults;
import com.semantic.Model.MovieResults;
import com.semantic.Model.Results;
import com.semantic.QueryBuilder.BooksQueryBuilder;
import com.semantic.QueryBuilder.MovieQueryBuilder;
import com.semantic.QueryBuilder.MusicQueryBuilder;
import com.semantic.QueryBuilder.UserQueryBuilder;
import com.semantic.QueryHelper.LoadOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class SparQlController {

    @Autowired
    LoadOntology loadOntology;

    @Autowired
    MovieQueryBuilder movieQueryBuilder;

    @Autowired
    UserQueryBuilder userQueryBuilder;

    @Autowired
    MusicQueryBuilder musicQueryBuilder;

    @Autowired
    BooksQueryBuilder booksQueryBuilder;

    @RequestMapping("/getMovies")
    public Results getMovies(@RequestParam(name = "count", required = false) Integer count) {

        if (null == count) {
            count = 10;
        }

        return loadOntology.loadSparQlQueries(ApiConstants.movieServiceEndPoint, movieQueryBuilder.getMovieQuery(count));
    }

    @RequestMapping("/getUserDetails")
    public Results getUserDetails(@RequestParam(name = "userId") Integer userId,
                                  @RequestParam(name = "page", required = false) String page) {

        String userIdStr = ApiConstants.userIdSkeleton.replace(ApiConstants.userIdString, userId.toString());

        return loadOntology.loadSparQlQueries(ApiConstants.userServiceEndPoint, userQueryBuilder.getUserQuery(userIdStr), page);
    }

    @RequestMapping("/getUserFriends")
    public List<String> getUserFriendsDetails(@RequestParam(name = "userId") Integer userId) {

        String friendIdList = makeFriendIdStr(userId);

        Results resultsName = loadOntology.
                loadSparQlQueries(ApiConstants.userServiceEndPoint, userQueryBuilder.getFriendNameQuery(friendIdList));

        List<String> friends = new ArrayList<>();

        for (Map<String, List<String>> mapList : resultsName.getResultList()) {

            friends.add(mapList.get(ApiConstants.NAME).get(0));
        }

        return friends;
    }


    @RequestMapping("/getMoviesForUser")
    public MovieResults getMoviesForUser(@RequestParam(name = "userId") Integer userId,
                                    @RequestParam(name ="genre") String genre) {


        MovieResults movieResults = new MovieResults();

        String friendList = makeFriendIdStr(userId);

        String movieList = makeMovieIdStrForFriends(friendList);

        Results results =  loadOntology.
                loadSparQlQueries(ApiConstants.movieServiceEndPoint, movieQueryBuilder.getMovieQueryByGenre(movieList, genre));


        String userMovie = makeStrFromResultByType(results, ApiConstants.MOVIE);

        String[] arr = userMovie.split(",");
        int size = arr.length;

        int remaining = ApiConstants.MOVIE_COUNT - size;

        if (remaining > 0) {

            Results resultRemaining =  loadOntology.
                    loadSparQlQueries(ApiConstants.movieServiceEndPoint, movieQueryBuilder
                                .getMovieQueryByGenreAndMovieFilter(userMovie, genre, remaining));

            movieResults.setAllMovieList(resultRemaining.getResultList());
        }

        movieResults.setFriendMovieList(results.getResultList());

        return movieResults;
    }

    @RequestMapping("/getSongsForUser")
    public Results getSongsForUser(@RequestParam(name = "artist") String artist,
                                   @RequestParam(name = "count", required = false) Integer count) {

        if (null == count) {
            count = ApiConstants.MUSIC_COUNT;
        }

        return loadOntology.
                loadSparQlQueries(ApiConstants.musicServiceEndPoint, musicQueryBuilder
                        .getMusicQuery(artist, count));
    }

    @RequestMapping("/getBooksForUser")
    public BookResults getBooksForUser(@RequestParam(name = "author") String author,
                                   @RequestParam(name = "userId") Integer userId) {

        BookResults bookResults = new BookResults();

        String friendList = makeFriendIdStr(userId);

        String bookList = makeBooksIdStrForFriends(friendList);

        Results results =  loadOntology.
                loadSparQlQueries(ApiConstants.bookServiceEndPoint, booksQueryBuilder.getBookQueryByAuthor(bookList, author));


        String userBooks = makeStrFromResultByType(results, ApiConstants.BOOKS);

        String[] arr = bookList.split(",");
        int size = arr.length;

        int remaining = ApiConstants.BOOK_COUNT - size;

        if (remaining > 0) {

            Results resultRemaining =  loadOntology.
                    loadSparQlQueries(ApiConstants.bookServiceEndPoint, booksQueryBuilder
                            .getBookQueryByGenreAndMovieFilter(userBooks, author, remaining));

            bookResults.setAllBookList(resultRemaining.getResultList());
        }

        bookResults.setFriendBookList(results.getResultList());

        return bookResults;

    }

    private String makeFriendIdStr(Integer userId) {

        String userIdStr = ApiConstants.userIdSkeleton.replace(ApiConstants.userIdString, userId.toString());

        Results results =  loadOntology.
                loadSparQlQueries(ApiConstants.userServiceEndPoint, userQueryBuilder.getFriendQuery(userIdStr));

        int i = 0;
        String friendIdList = "<";
        for (Map<String, List<String>> mapList : results.getResultList()) {

            String friendId = mapList.get(ApiConstants.FRIEND).get(0);

            if (i != 0) {
                friendIdList += ", <";
            }

            friendIdList += friendId + ">";
            i++;
        }

        return friendIdList;
    }

    private String makeMovieIdStrForFriends(String friendIdList) {

        Results results =  loadOntology.
                loadSparQlQueries(ApiConstants.userServiceEndPoint, userQueryBuilder.getMovieDataForFriends(friendIdList));


        return makeStrFromResultByType(results, ApiConstants.MOVIE);
    }

    private String makeBooksIdStrForFriends(String friendIdList) {

        Results results =  loadOntology.
                loadSparQlQueries(ApiConstants.userServiceEndPoint, userQueryBuilder.getBooksDataForFriends(friendIdList));


        return makeStrFromResultByType(results, ApiConstants.BOOKS);
    }


    private String makeStrFromResultByType(Results results, String value) {

        int i = 0;
        String movieList = "";
        Set<String> movieSet = new HashSet<>();
        for (Map<String, List<String>> mapList : results.getResultList()) {

            List<String> movies = mapList.get(value);

            for (String movie : movies) {

                if (!movieSet.contains(movie)) {

                    if (i != 0) {
                        movieList += ", ";
                    }

                    movieList += "'" + movie + "'";
                    i++;

                    movieSet.add(movie);
                }
            }
        }

        return movieList;
    }
}