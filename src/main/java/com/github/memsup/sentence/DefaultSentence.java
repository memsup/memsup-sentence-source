package com.github.memsup.sentence;

import com.github.memsup.sentence.datasource.Database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DefaultSentence implements Sentence {

    private final static DataSource dataSource = Database.INSTANCE.getDataSource();

    /**
     * create table sentence (
     * 	sentence_id serial primary key,
     * 	sentence varchar(100)
     * )
     * @param sentences
     */

    @Override
    public final void save(final Set<String> sentences) {

        try (Connection connection =
                     dataSource.getConnection()) {

            connection.setAutoCommit(false);

            final String query = "insert into sentence(sentence) values (?)";
            final int scrollInSensitive = ResultSet.TYPE_SCROLL_INSENSITIVE;
            final int readOnly = ResultSet.CONCUR_READ_ONLY;
            final int closeCursorsAtCommit = ResultSet.CLOSE_CURSORS_AT_COMMIT;

            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query, scrollInSensitive, readOnly, closeCursorsAtCommit)) {

                for (final String sentence : sentences) {
                    preparedStatement.setString(1, sentence);
                    preparedStatement.addBatch(); // bulk insert
                }

                int[] results = preparedStatement.executeBatch();
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
