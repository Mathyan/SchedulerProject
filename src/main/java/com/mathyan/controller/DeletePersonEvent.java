package com.mathyan.controller;

import java.util.EventObject;

public class DeletePersonEvent extends EventObject{
    private String nameSurname;

    public DeletePersonEvent(Object source , String nameSurname){
        super(source);
        this.nameSurname = nameSurname;
    }

    public String getNameSurname(){
        return nameSurname;
    }

}

