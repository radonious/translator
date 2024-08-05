package edu.controller;

import edu.model.Log;
import edu.model.RequestDTO;
import edu.service.LogService;
import edu.service.TranslateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class Controller {

    private final TranslateService translateService;
    private final LogService logService;

    @PostMapping("/translate")
    public String translate(
            @Valid @RequestBody RequestDTO requestDTO,
            HttpServletRequest request) {
        String result = translateService.translate(requestDTO).trim();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()) ipAddress = request.getRemoteAddr();
        logService.saveLog(new Log(ipAddress,requestDTO.getFrom(), requestDTO.getTo(), requestDTO.getText(), result));
        return result;
    }

    @GetMapping("/data")
    public String data() {
        StringBuilder sb = new StringBuilder("{\n");
        for (Log line : logService.fetchList()) {
            sb.append('\t').append(line).append('\n');
        }
        sb.append('}');
        return sb.toString();
    }
}
