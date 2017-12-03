package com.semantic.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BookResults implements Serializable {

    List<Map<String, List<String>>> friendBookList;
    List<Map<String, List<String>>> allBookList;

    public List<Map<String, List<String>>> getFriendBookList() {
        return friendBookList;
    }

    public void setFriendBookList(List<Map<String, List<String>>> friendBookList) {
        this.friendBookList = friendBookList;
    }

    public List<Map<String, List<String>>> getAllBookList() {
        return allBookList;
    }

    public void setAllBookList(List<Map<String, List<String>>> allBookList) {
        this.allBookList = allBookList;
    }
}