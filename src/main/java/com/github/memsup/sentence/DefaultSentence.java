package com.github.memsup.sentence;

import com.github.memsup.sentence.datasource.Database;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DefaultSentence implements Sentence {

    private final static DataSource dataSource = Database.INSTANCE.getDataSource();
    private static int counter = 0;

    /**
     * create table sentence (
     * sentence_id serial primary key,
     * sentence varchar(100)
     * <p>
     * create table sentence (
     * sentence_id serial primary key,
     * sentence text
     * )
     *
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
                         connection.prepareStatement(query, scrollInSensitive, readOnly, closeCursorsAtCommit);
                 PrintStream printStream = new PrintStream(new FileOutputStream("a.sql", true))) {

                for (String sentence : sentences) {
                    boolean isEmpty = sentence.isEmpty();
                    boolean len = sentence.length() < 100 && sentence.length() > 30;
                    boolean isSentenceHasDot = sentence.contains(".");

                    if (!isEmpty && len && isSentenceHasDot) {
//                        System.out.println("sentence = " + sentence);
                        //       preparedStatement.setString(1, sentence);
                        ///     preparedStatement.addBatch(); // bulk insert
                        sentence = sentence.replaceAll("\\s{2,}+", "");
                        sentence = sentence.replaceAll("\n", "");
                        printStream.println(String.format("insert into sentence(sentence) values ('%s');", sentence.trim()));
                        System.out.println("counter = " + counter);
                        counter++;
                    }

                }

                int[] results = preparedStatement.executeBatch();
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
