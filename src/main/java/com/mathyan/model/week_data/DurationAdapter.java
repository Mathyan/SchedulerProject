package com.mathyan.model.week_data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Duration;

/**
 * This is helper class to serialize and deserialize Duration objects.
 * <p>
 * This class implements the TypeAdapter interface so that it can be used by
 * Gson to serialize and deserialize Duration objects.
 */
public class DurationAdapter extends TypeAdapter<Duration> {
    @Override
    public void write(JsonWriter out, Duration value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public Duration read(JsonReader in) throws IOException {
        return Duration.parse(in.nextString());
    }
}