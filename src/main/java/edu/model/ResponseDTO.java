package edu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Translator API response DTO")
public class ResponseDTO {
    public ArrayList<Translation> translations;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Translation translation : translations) sb.append(translation.text.replaceAll("[\\[\\]]",""));
        return sb.toString();
    }

    @NoArgsConstructor
    public static class Translation {
        public String text;

        @Override
        public String toString() {
            return text;
        }
    }
}
