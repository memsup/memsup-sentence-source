package com.github.memsup.sentence;

import com.github.memsup.sentence.datasource.Database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class DefaultSentence implements Sentence {

    private final static DataSource dataSource = Database.INSTANCE.getDataSource();

    @Override
    public void save(Set<String> sentences) {

        try (Connection connection =
                     dataSource.getConnection()) {

            connection.setAutoCommit(false);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
