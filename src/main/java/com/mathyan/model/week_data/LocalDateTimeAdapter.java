package com.mathyan.model.week_data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is helper class to serialize and deserialize LocalDateTime objects.
 * <p>
 * This class implements the TypeAdapter interface so that it can be used by
 * Gson to serialize and deserialize LocalDateTime objects.
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Overrides the write method of the TypeAdapter interface.
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(formatter.format(value));
    }
    /**
     * Overrides the read method of the TypeAdapter interface.
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return LocalDateTime.parse(in.nextString(), formatter);
    }
}