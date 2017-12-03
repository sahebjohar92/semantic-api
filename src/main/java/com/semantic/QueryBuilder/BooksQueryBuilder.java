package com.semantic.QueryBuilder;

import org.springframework.stereotype.Component;

@Component
public class BooksQueryBuilder {

    public String getBookQueryByAuthor(String bookList, String author) {

        return "SELECT distinct ?author ?name\n" +
                "WHERE {\n" +
                " ?book InSync:hasName ?name ;\n" +
                "   InSync:hasAuthor ?author .\n" +
                "  FILTER(?author = '"+author+"' && ?name in ("+bookList+"))\n" +
                "}\n" +
                "order by (?name)";
    }

    public String getBookQueryByGenreAndMovieFilter(String bookList, String author, int limit) {

        return "SELECT distinct ?author ?name\n" +
                "WHERE {\n" +
                " ?book InSync:hasName ?name ;\n" +
                "   InSync:hasAuthor ?author .\n" +
                "  FILTER(?author = '"+author+"' && ?name in ("+bookList+"))\n" +
                "}\n" +
                "order by (?name)\n" +
                "limit "+limit;
    }
}