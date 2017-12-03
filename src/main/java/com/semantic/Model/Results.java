package com.semantic.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Results implements Serializable{

    List<Map<String, List<String>>> resultList;

    public List<Map<String, List<String>>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, List<String>>> resultList) {
        this.resultList = resultList;
    }
}