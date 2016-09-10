package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    // Controls initialised by using reflection.  Name of the fields MUST be the same as the fx:id
    public Label lblMainLabel;
    public Button btnLogin;
    public Button btnCloseApp;
    public TextField edtUsername;
    public Label lblMessage;
    public PasswordField edtPassword;

    // Database variables
    private DB database = new DB();
    private String username;

    // Constructor
    public LoginController() {

    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    // Login methods
    public void btnLoginClicked(ActionEvent actionEvent) {
        try {
            if ((edtUsername.getText().equals("")) || (edtPassword.getText().equals(""))) {
                lblMessage.setText("Please enter your login credentials.");
                lblMessage.setVisible(true);
                edtUsername.clear();
                edtPassword.clear();
                edtUsername.requestFocus();
            } else {
                try {
                    String Username = edtUsername.getText();
                    String Password = edtPassword.getText();
                    String password = "";

                    String sql = "SELECT UserName FROM Users;";
                    ResultSet resultSet = database.getStatement().executeQuery(sql);

                    // Check each tuple for matching username
                    while (resultSet.next()) {
                        // Get username from current tuple
                        String currentUsername = resultSet.getString("UserName");
                        // Check for a matching username
                        if (Username.equals(currentUsername)) {
                            sql = "SELECT Password FROM Users WHERE UserName = '" + currentUsername + "'";
                            ResultSet passwordResult = database.getStatement().executeQuery(sql);
                            // Checks if Jarred is knob
                            if (passwordResult.next())
                                password = passwordResult.getString("Password");
                            if (password.equals(Password)) {
                                // Log in successful, open new window to actual application
                                lblMessage.setText("");
                                lblMessage.setVisible(false);
                                edtUsername.clear();
                                edtPassword.clear();

                                // Display home page
                                setUsername(Username);
                                newWindow(actionEvent);
                            } else {
                                lblMessage.setText("Password is incorrect.");
                                lblMessage.setVisible(true);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while trying to log in... " + e.getMessage());
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Application experienced an error: " + e.getMessage());
        }
        lblMessage.setText("Username does not exist.");
        lblMessage.setVisible(true);
        edtUsername.clear();
        edtPassword.clear();
        edtUsername.requestFocus();

    }

    public void newWindow(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage app = new Stage();
            app.setTitle("Maze-Solving Robot | Home");
            app.setScene(new Scene(root, 1280, 800));
            // TODO: Check if this changes the name of cmbUser
            //HomeController hc = new HomeController(username);
            Main.stage.close();
            app.show();
        } catch (Exception e) {
            System.out.println("An error occurred while logging in... " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void btnCloseClicked(ActionEvent actionEvent) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you want to exit?", "Exit", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            database.disconnectDB();
            Main.stage.close();
        } else {
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
