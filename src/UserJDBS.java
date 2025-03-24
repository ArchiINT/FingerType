
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJDBS {
    public static int default_time;
    public static float default_spm;

    public UserJDBS() {
    }

    public static void record() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:score.db");
                Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery("select * from user");
            default_time = rs.getInt("bTime");
            default_spm = (float)rs.getInt("bspm");
            if (ScoreGUI.spm > default_spm) {
                default_spm = ScoreGUI.spm;
                String query = "UPDATE user SET bspm = ? WHERE id = 1";

                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setFloat(1, default_spm);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void reset() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:score.db");
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("update user set bspm = 1 where id = 1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
