package com.example.boilerroom_labb1;

import com.example.boilerroom_labb1.entity.Author;

public class TestData {
    public static Author createAuthor(String name){
             return new Author(name);

    }
}
