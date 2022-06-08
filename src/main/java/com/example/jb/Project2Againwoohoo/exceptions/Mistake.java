package com.example.jb.Project2Againwoohoo.exceptions;

public class Mistake extends Exception{
    public Mistake(ErrMsg message) {
        super(message.getMessage());
    }
}
