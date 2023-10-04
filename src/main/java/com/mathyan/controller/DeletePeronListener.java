package com.mathyan.controller;

import java.util.EventListener;

public interface DeletePeronListener extends EventListener{
    public void deletePerson(DeletePersonEvent event);
}
