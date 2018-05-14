package Utils;

import java.sql.*;
import java.util.Date;

import static java.lang.System.getProperty;

public class DbWrapper {
  public static final String DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/open_lmis";
  public static final String DEFAULT_DB_USERNAME = "postgres";
  public static final String DEFAULT_DB_PASSWORD = "p@ssw0rd";

  Connection connection;

  public DbWrapper() throws SQLException {
    String dbUrl = getProperty("dbUrl", DEFAULT_DB_URL);
    String dbUser = getProperty("dbUser", DEFAULT_DB_USERNAME);
    String dbPassword = getProperty("dbPassword", DEFAULT_DB_PASSWORD);
    loadDriver();

    connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
  }

  public void closeConnection() throws SQLException {
    if (connection != null) connection.close();
  }

  private void loadDriver() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e);
      System.exit(1);
    }
  }

  private void update(String sql) throws SQLException {
    Statement statement = connection.createStatement();
    statement.executeUpdate(sql);
  }

  private ResultSet query(String sql) throws SQLException {
    return connection.createStatement().executeQuery(sql);
  }

  public Date getDate() throws SQLException {
    Date currentDate = null;
    ResultSet rs = query("Select NOW()");
    if(rs.next()) {
      currentDate = rs.getDate(1);
    }
    return currentDate;
  }
}
