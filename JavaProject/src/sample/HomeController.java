package sample;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by Steyn on 2016/08/15.
 */
public class HomeController implements Initializable {
    // Controls initialised by using reflection.  Name of the fields MUST be the same as the fx:id
    public BorderPane borderPane;
    public MenuButton cmbUser;
    public MenuItem miAddUser;
    public MenuItem miUpdateDetails;
    public MenuItem miChangePassword;
    public MenuItem miLogout;
    public Pane pnlContainer;
    public Pane pnlAddUser;
    public Pane pnlUpdateDetails;
    public Pane pnlChangePassword;
    public TextField edtUpdateUsername;
    public TextField edtUpdateName;
    public TextField edtUpdateSurname;
    public TextField edtUpdateEmail;
    public TextField edtUpdateStudentNo;
    public Button btnUpdate;
    public Button btnClearUpdate;
    public TextField edtAddFirstName;
    public TextField edtAddSurname;
    public TextField edtAddStudentNo;
    public TextField edtAddEmail;
    public Button btnAddUser;
    public Button btnClearUserAdd;
    public Label lblPasswordMessage;
    public Button btnUpdatePassword;
    public Button btnClearPassword;
    public PasswordField edtOldPassword;
    public PasswordField edtNewPassword;
    public PasswordField edtConfirmPassword;
    public Pane pnlMaze;
    public ComboBox cbxMazeChoice;
    public Canvas canvasMaze;
    public Button btnSaveCanvas;
    public Button btnClearCanvas;
    public Pane pnlSimulate;
    public ComboBox cbxSelectMaze;
    public ComboBox cbxSelectProgram;
    public ProgressBar SimulationProgress;
    public Label lblSimulateMessage;
    public Button btnRunSimulation;
    public Button btnClearSimulation;
    public Button btnViewStats;
    public Pane pnlStatistics;
    public ComboBox cbxStatisticsRun;
    public TableView tblStatistics;
    public Button btnMaze;
    public Button btnSimulate;
    public Button btnStatistics;
    public Label lblAddUserMessage;
    public CheckBox cbxAdmin;
    public TextField edtAddUsername;

    // Local variables
    private DB homeDB = null;

    public void populateComboBox() {
        // FIXME: 2016/08/16 Change username in menu button
        cmbUser.setText("");
    }

    // Changing the visibilities of panes
    public void addUser(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(false);
        pnlSimulate.setVisible(false);
        pnlStatistics.setVisible(false);
        pnlUpdateDetails.setVisible(false);
        pnlAddUser.setVisible(true);
        pnlChangePassword.setVisible(false);

        pnlMaze.setDisable(true);
        pnlSimulate.setDisable(true);
        pnlStatistics.setDisable(true);
        pnlAddUser.setDisable(false);
        pnlUpdateDetails.setDisable(true);
        pnlChangePassword.setDisable(true);
    }
    public void mazeWindow(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(true);
        pnlSimulate.setVisible(false);
        pnlStatistics.setVisible(false);
        pnlUpdateDetails.setVisible(false);
        pnlAddUser.setVisible(false);
        pnlChangePassword.setVisible(false);

        pnlMaze.setDisable(false);
        pnlSimulate.setDisable(true);
        pnlStatistics.setDisable(true);
        pnlAddUser.setDisable(true);
        pnlUpdateDetails.setDisable(true);
        pnlChangePassword.setDisable(true);
    }

    public void simulateWindow(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(false);
        pnlSimulate.setVisible(true);
        pnlStatistics.setVisible(false);
        pnlUpdateDetails.setVisible(false);
        pnlAddUser.setVisible(false);
        pnlChangePassword.setVisible(false);

        pnlMaze.setDisable(true);
        pnlSimulate.setDisable(false);
        pnlStatistics.setDisable(true);
        pnlAddUser.setDisable(true);
        pnlUpdateDetails.setDisable(true);
        pnlChangePassword.setDisable(true);
    }

    public void statisticsWindow(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(false);
        pnlSimulate.setVisible(false);
        pnlStatistics.setVisible(true);
        pnlUpdateDetails.setVisible(false);
        pnlAddUser.setVisible(false);
        pnlChangePassword.setVisible(false);

        pnlMaze.setDisable(true);
        pnlSimulate.setDisable(true);
        pnlStatistics.setDisable(false);
        pnlAddUser.setDisable(true);
        pnlUpdateDetails.setDisable(true);
        pnlChangePassword.setDisable(true);
    }

    public void updateDetails(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(false);
        pnlSimulate.setVisible(false);
        pnlStatistics.setVisible(false);
        pnlUpdateDetails.setVisible(true);
        pnlAddUser.setVisible(false);
        pnlChangePassword.setVisible(false);

        pnlMaze.setDisable(true);
        pnlSimulate.setDisable(true);
        pnlStatistics.setDisable(true);
        pnlAddUser.setDisable(true);
        pnlUpdateDetails.setDisable(false);
        pnlChangePassword.setDisable(true);
    }

    public void changePassword(ActionEvent actionEvent) {
        pnlContainer.setVisible(true);
        pnlMaze.setVisible(false);
        pnlSimulate.setVisible(false);
        pnlStatistics.setVisible(false);
        pnlUpdateDetails.setVisible(false);
        pnlAddUser.setVisible(false);
        pnlChangePassword.setVisible(true);

        pnlMaze.setDisable(true);
        pnlSimulate.setDisable(true);
        pnlStatistics.setDisable(true);
        pnlAddUser.setDisable(true);
        pnlUpdateDetails.setDisable(true);
        pnlChangePassword.setDisable(false);
    }

    public void logOut(ActionEvent actionEvent) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you want to logout?", "Logout", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            Main.stage.show();
            // Hide application window
            Stage homeStage = (Stage)borderPane.getScene().getWindow();
            homeStage.hide();
        } else {
            return;
        }
    }

    // Update details methods
    /**
     * Updates the details of the details of the user to those entered by the user.
     * @param actionEvent
     */
    public void btnUpdateDetails(ActionEvent actionEvent) {
    }

    public void clearUpdate(ActionEvent actionEvent) {
        edtUpdateName.clear();
        edtUpdateSurname.clear();
        edtUpdateUsername.clear();
        edtUpdateStudentNo.clear();
        edtUpdateEmail.clear();
        edtUpdateUsername.requestFocus();
    }

    // Add User methods
    // TODO: Error checks and add user to database
    public void btnAddUser(ActionEvent actionEvent) {
        try {
            if ((edtAddUsername.getText().equals("")) || (edtAddFirstName.getText().equals("")) || (edtAddSurname.getText().equals("")) || (edtAddStudentNo.getText().equals("")) || (edtAddEmail.getText().equals(""))) {
                // Clear text fields and display error message
                /*edtAddFirstName.clear();
                edtAddSurname.clear();
                edtAddStudentNo.clear();
                edtAddEmail.clear();
                edtAddFirstName.requestFocus();*/
                lblAddUserMessage.setText("Ensure that all the required fields have been filled.");
                lblAddUserMessage.setVisible(true);

            } else {
                System.out.println("Attempting to insert the new user into the database ...");
                try {
                    // Get new user details
                    String Username = edtAddUsername.getText();
                    String firstName = edtAddFirstName.getText();
                    String Surname = edtAddSurname.getText();
                    String studentNo = edtAddStudentNo.getText();
                    String email = edtAddEmail.getText();
                    boolean admin = cbxAdmin.isSelected();

                    // Randomly generate password
                    // TODO: Randomly generate password for the new user
                    String Password = "randomPassword";

                    // Add new user details to the database
                    // FIXME: Adding user to the database
                    String sql = String.format("INSERT INTO Users VALUES ('" + Username + "', '" + firstName + "', '" + Surname + "', '" + email + "', '" + studentNo + "', '" + Password + "', '" + admin + "')", Username, firstName, Surname, email, studentNo, Password, admin);
                    homeDB.getStatement().execute(sql);
                    System.out.println("New user inserted successfully into database.");
                    lblAddUserMessage.setText("New user inserted successfully into database.");
                    lblAddUserMessage.setVisible(true);

                } catch (Exception ee) {
                    System.out.println("An error occurred what inserting the new user into the database ... " + ee.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred when adding a user to the database ... " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clearAddUser(ActionEvent actionEvent) {
        edtAddUsername.clear();
        edtAddFirstName.clear();
        edtAddSurname.clear();
        edtAddStudentNo.clear();
        edtAddEmail.clear();
        cbxAdmin.setSelected(false);
        lblAddUserMessage.setText("");
        lblAddUserMessage.setVisible(false);
        edtAddUsername.requestFocus();
    }

    //  Change Password methods
    public void btnUpdatePassword(ActionEvent actionEvent) {

    }

    public void btnClearPassword(ActionEvent actionEvent) {
        edtOldPassword.clear();
        edtNewPassword.clear();
        edtConfirmPassword.clear();
        edtOldPassword.requestFocus();
    }

    public MenuButton getCmbUser() {
        return cmbUser;
    }

    public void setCmbUser(MenuButton cmbUser) {
        this.cmbUser = cmbUser;
    }

    /*public HomeController(String username) {
        cmbUser.setText(username);
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeDB = new DB();
        populateComboBox();
    }
}
