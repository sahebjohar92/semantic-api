package com.semantic.QueryBuilder;

import org.springframework.stereotype.Component;

@Component
public class BooksQueryBuilder {

    public String getBookQueryByAuthor(String bookList, String author) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT distinct ?author ?name\n" +
                "WHERE {\n" +
                " ?book InSync:hasName ?name ;\n" +
                "   InSync:hasAuthor ?author .\n" +
                "  FILTER(?author = '"+author+"' && ?name in ("+bookList+"))\n" +
                "}\n" +
                "order by (?name)";
    }

    public String getBookQueryByGenreAndMovieFilter(String bookList, String author, int limit) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT distinct ?author ?name\n" +
                "WHERE {\n" +
                " ?book InSync:hasName ?name ;\n" +
                "   InSync:hasAuthor ?author .\n" +
                "  FILTER(?author = '"+author+"' && ?name not in ("+bookList+"))\n" +
                "}\n" +
                "order by (?name)\n" +
                "limit "+limit;
    }
}