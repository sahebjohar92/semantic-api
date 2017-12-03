package com.semantic.QueryHelper;

import com.semantic.Model.Results;
import org.apache.jena.query.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoadOntology {

    public Results loadSparQlQueries(String serviceURI,String query) {

        return loadSparQlQueries(serviceURI, query, null);
    }

    public Results loadSparQlQueries(String serviceURI,String query, String filter) {

        QueryExecution q= QueryExecutionFactory.sparqlService(serviceURI, query);
        ResultSet results=q.execSelect();

        List<Map<String, List<String>>> finalList = new ArrayList<>();

        while (results.hasNext()) {

            Map<String, List<String>> map = new HashMap<>();

            QuerySolution qs = results.next() ;

            for (String head : results.getResultVars()) {

                String value = qs.getLiteral(head).getValue().toString();

                String[] arr = value.split(",");

                if (null == filter) {

                    map.put(head, Arrays.asList(arr));
                } else {

                    if (head.equals(filter)) {
                        map.put(head, Arrays.asList(arr));
                    }
                }
            }

            finalList.add(map);
        }

        Results resultList = new Results();
        resultList.setResultList(finalList);

        return resultList;
    }
}