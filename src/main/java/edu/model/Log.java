package edu.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {
    private String ip;
    private String fromLang;
    private String toLang;
    private String input;
    private String output;

    @Override
    public String toString() {
        return "{" +
                "ip='" + ip + '\'' +
                ", fromLang='" + fromLang + '\'' +
                ", toLang='" + toLang + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
