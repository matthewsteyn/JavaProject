package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Steyn on 2016/08/15.
 */
public class DB {
    // Fields needed to access database
    // Actual connection to db
    private Connection connection = null;
    // Object used to issue SQL commands
    private Statement statement = null;

    public DB() {
        //this.connection = connection;
        //this.statement = statement;

        // Establish connection to the database
        connectToDB();
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Establish a connection to the database. Cannot do <b>anything</b> until a connection
     * is established.
     */
    private void connectToDB() {
        System.out.println("Establishing connection to database...");
        System.out.println("    Loading JDBC driver for Microsoft SQL Server database...");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("    Unable to load JDBC driver... " + e.getMessage());
            return;
        }

        System.out.println("    Using driver to connect to Microsoft SQL Server (openbox.nmmu.ac.za\\wrr)");
        if (true) {
            try {
                System.out.println("        Locating database to be opened...");
                String connectionString = "jdbc:sqlserver://openbox.nmmu.ac.za\\wrr;databaseName=MC09";
                System.out.println("            Connection string: " + connectionString);

                // Possibly ask user to enter database username and password?
                connection = DriverManager.getConnection(connectionString, "MC09User", "30kwOSx8");
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            } catch (Exception e) {
                System.out.println("An error occurred while attempting to connect to the MC09 database. " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("        Connected to database!");
    }

    /**
     * Terminates the connection to the database.
     */
    public void disconnectDB() {
        System.out.println("Disconnecting from database...");
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("    Unable to disconnect from database... " + e.getMessage());
            return;
        }
        System.out.println("Successfully disconnected from database...");
    }

    public Connection getConnection() {
        return connection;
    }
}
