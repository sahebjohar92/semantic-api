package com.semantic.Constants;

public class ApiConstants {

    public static final String IP = "34.216.149.88:3030";
    public static final String movieServiceEndPoint = "http://"+IP+"/Movie/query";
    public static final String userServiceEndPoint = "http://"+IP+"/users/query";
    public static final String musicServiceEndPoint = "http://"+IP+"/Music/query";
    public static final String bookServiceEndPoint = "http://"+IP+"/Book/query";

    public static final String PROPERTY_NAME_REDIS_HOST = "redis.host";
    public static final String PROPERTY_NAME_REDIS_PORT = "redis.port";

    public static final String userIdSkeleton = "<http://127.0.0.1:3333/{userId}>";
    public static final String userIdString   = "{userId}";

    public static final String FRIEND = "friend";
    public static final String NAME = "name";
    public static final String MOVIE = "movie";
    public static final String BOOKS = "book";

    public static final int MOVIE_COUNT = 12;
    public static final int MUSIC_COUNT = 12;
    public static final int BOOK_COUNT = 12;
}