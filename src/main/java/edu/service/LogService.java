package edu.service;

import edu.model.Log;
import edu.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public Log saveLog(Log log) {
        try {
            return logRepository.save(log);
        }catch (SQLException e) {
            System.err.println("Error with DB save - " + e.getMessage());
            return null;
        }

    }

    public List<Log> fetchList() {
        try {
            return logRepository.findAll();
        } catch (SQLException e) {
            System.err.println("Error with DB parse - " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
