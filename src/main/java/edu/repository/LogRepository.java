package edu.repository;

import edu.model.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPass;

    public Log save(Log log) throws SQLException {
        Connection conn = DriverManager.getConnection (dbUrl, dbUser,dbPass);
        Statement st = conn.createStatement();
        st.executeUpdate(String.format(
                "INSERT INTO translations VALUES('%s', '%s', '%s', '%s');",
                log.getIp(),
                log.getFromLang(),
                log.getToLang(),
                log.getText()
        ));
        st.close();
        conn.close();
        return log;
    }

    public List<Log> findAll() throws SQLException {
        Connection conn = DriverManager.getConnection (dbUrl, dbUser,dbPass);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM translations;");
        ArrayList<Log> result = new ArrayList<>();
        while (rs.next()) {
            String ip = rs.getString("ip");
            String fromLang = rs.getString("fromLang");
            String toLang = rs.getString("toLang");
            String text = rs.getString("text");
            result.add(new Log(ip, fromLang, toLang, text));
        }
        return result;
    }
}
