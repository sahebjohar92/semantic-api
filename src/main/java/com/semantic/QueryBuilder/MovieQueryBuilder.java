package com.semantic.QueryBuilder;

import org.springframework.stereotype.Component;

@Component
public class MovieQueryBuilder {


    public String getMovieQuery(int count) {

        return
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" +
                        "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\r\n" +
                        "\r\n" +
                        "SELECT ?name ?Genre\r\n" +
                        "WHERE {\r\n" +
                        "	?movie InSync:hasName ?name ;\r\n" +
                        "		 InSync:hasGenre ?Genre .\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "LIMIT " + count;
    }

    public String getMovieQueryByGenre(String movieList, String genre) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?movie ?popularity\n" +
                "WHERE {\n" +
                " ?m InSync:hasName ?movie ;\n" +
                "   InSync:hasGenre ?genre ;\n" +
                "   InSync:hasPopularity ?popularity .\n" +
                "  FILTER(?movie in ("+movieList+") && contains(str(?genre), '"+genre+"'))\n" +
                "}\n"+
                "ORDER BY DESC(?popularity)";
    }

    public String getMovieQueryByGenreAndMovieFilter(String movieList, String genre, int limit) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?movie ?popularity\n" +
                "WHERE {\n" +
                " ?m InSync:hasName ?movie ;\n" +
                "   InSync:hasGenre ?genre ;\n" +
                "   InSync:hasPopularity ?popularity .\n" +
                "  FILTER(?movie not in ("+movieList+") && contains(str(?genre), '"+genre+"'))\n" +
                "}\n"+
                "ORDER BY DESC(?popularity)\n"+
                "LIMIT "+limit;
    }
}