package com.mathyan.controller;

import java.io.File;
import java.util.EventObject;

public class FileEvent extends EventObject{
    private String fileName;
    private File file;
    private String json;

    /**
     * Constructs a FileEvent object with the given source and file name.
     *
     * @param source   the source of the event
     * @param fileName the file name
     */
    public FileEvent(Object source, String fileName) {
        super(source);
        this.fileName = fileName;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file.
     *
     * @param file the file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Gets the json.
     *
     * @return the json
     */
    public String getJson() {
        return json;
    }

    /**
     * Sets the json.
     *
     * @param json the json
     */
    public void setJson(String json) {
        this.json = json;
    }
}
