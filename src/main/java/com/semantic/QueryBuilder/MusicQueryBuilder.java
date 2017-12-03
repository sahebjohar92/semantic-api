package com.semantic.QueryBuilder;

import org.springframework.stereotype.Component;

@Component
public class MusicQueryBuilder {

    public String getMusicQuery(String artist, int limit) {

        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX InSync: <http://www.semanticweb.org/abhishek/ontologies/2017/9/insync#>\n" +
                "SELECT distinct ?name ?views ?url\n" +
                "WHERE {\n" +
                " ?song InSync:hasTrack ?name ;\n" +
                " InSync:hasURL ?url ;\n" +
                " InSync:hasViews ?views ;\n" +
                "   InSync:hasArtist ?artist .\n" +
                "  FILTER(?artist = '"+artist+"')\n" +
                "}\n" +
                "order by (?name)\n"+
                "LIMIT "+limit;
    }
}