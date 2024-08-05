package edu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Schema(description = "Request DTO")
public class RequestDTO {
    @JsonProperty("sourceLanguageCode")
    @Schema(example = "en")
    private String from;

    @JsonProperty("target_language_code")
    @Schema(example = "ru")
    private String to;

    @JsonProperty("texts")
    @Schema(example = "Hello world")
    private String text;
}
