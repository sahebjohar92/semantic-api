package com.semantic.QueryBuilder;

import org.springframework.stereotype.Component;

@Component
public class UserQueryBuilder {

    public String getUserQuery(String userId) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?name ?livingPlace ?movies ?music ?series ?visitedPlaces\n" +
                "WHERE {\n" +
                " ?person InSync:watchesMovie ?movies .\n" +
                "    ?person InSync:watchesChannels ?series .\n" +
                "    ?person InSync:listensTo ?music .\n" +
                "    ?person InSync:hasName ?name .\n" +
                "   ?person InSync:livesIn ?livingPlace .\n" +
                "    ?person InSync:reads ?books .\n" +
                "   ?person InSync:attends ?events .\n" +
                "   ?person InSync:isTaggedOn ?visitedPlaces .\n" +
                " FILTER (?person = "+ userId +")}";
    }

    public String getFriendQuery(String userId) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?friend\n" +
                "WHERE {\n" +
                " ?person InSync:hasFriends ?friend .\n" +
                " FILTER (?person = "+ userId +")}";
    }

    public String getFriendNameQuery(String friendIds) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?name\n" +
                "WHERE {\n" +
                " ?person InSync:hasName ?name .\n" +
                " FILTER (?person IN ("+ friendIds +"))}";
    }

    public String getMovieDataForFriends(String friendIds) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?movie\n" +
                "WHERE {\n" +
                " ?person InSync:watchesMovie ?movie .\n" +
                " FILTER (?person IN ("+ friendIds +"))}";
    }

    public String getBooksDataForFriends(String friendIds) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT ?book\n" +
                "WHERE {\n" +
                " ?person InSync:reads ?book .\n" +
                " FILTER (?person IN ("+ friendIds +"))}";
    }
}