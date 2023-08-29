import java.sql.*;

public class JDBC {//Design Pattern : Singleton Class

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:books.sqlite");
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void closeConnection(Connection con, Statement stmt) throws SQLException
    {
        stmt.close();
        con.close();
        System.out.println("The connection is closed");
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet)  {
        if(resultSet!=null){
            try {
                resultSet.close ();
            }catch (SQLException e){
                e.printStackTrace ();
            }
        }
        if(statement!=null){
            try {
                statement.close ();
            }catch (SQLException e){
                e.printStackTrace ();
            }
        }
        if(connection!=null){
            try {
                connection.close ();
            }catch (SQLException e){
                e.printStackTrace ();
            }
        }
    }
}

