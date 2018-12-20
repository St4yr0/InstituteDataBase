package students;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import dbUtil.dbConnection;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private Label studentFirstNameLabel;
    @FXML
    private Label studentLastNameLabel;
    @FXML
    private Label studentEmailLabel;
    @FXML
    private Label studentDepartmentLabel;
    @FXML
    private Label studentTermLabel;
    @FXML
    private Label studentDateOfBirthLabel;
    @FXML
    private Label studentPayingCompanyLabel;
    @FXML
    private Label studentAcPerformanceLabel;
    @FXML
    private TextField studentChangePasswrodFireld;
    @FXML
    private Button logoutButton;

    @FXML
    private TableView<StudentsData> studenttable;
    @FXML
    private TableColumn<StudentsData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentsData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentsData, String> dateofbirthcolumn;
    @FXML
    private TableColumn<StudentsData, String> termcolumn;
    @FXML
    private TableColumn<StudentsData, String> departmentcolumn;
    @FXML
    private TableColumn<StudentsData, String> acperformancecolumn;



    private dbConnection db_connection;
    private ObservableList<StudentsData> studentsData;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }



    @FXML
    private void loadOtherStudentsData(javafx.event.ActionEvent event) {
        try {
            Connection con = dbConnection.getConnection();
            this.studentsData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Students";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.studentsData.add(new StudentsData(resultSet.getString(1),
                        resultSet.getString(2), "", resultSet.getString(6),
                        resultSet.getString(5), resultSet.getString(4),
                        "", resultSet.getString(8)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.firstnamecolumn.setCellValueFactory(    new PropertyValueFactory<>("FIRST_NAME"));
        this.lastnamecolumn.setCellValueFactory(     new PropertyValueFactory<>("LAST_NAME"));
        this.dateofbirthcolumn.setCellValueFactory(  new PropertyValueFactory<>("DATE_OF_BIRTH"));
        this.departmentcolumn.setCellValueFactory(   new PropertyValueFactory<>("DEPARTMENT"));
        this.termcolumn.setCellValueFactory(         new PropertyValueFactory<>("TERM"));
        this.acperformancecolumn.setCellValueFactory(new PropertyValueFactory<>("AC_PERFORMANCE"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.studentsData);
    }

    @FXML
    private void changePassword(ActionEvent event) {
        try {
            if (!this.studentChangePasswrodFireld.getText().equals("")) {
                String sql = "UPDATE login SET password = ? WHERE username = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.studentChangePasswrodFireld.getText());
                preparedStatement.setString(2, this.studentFirstNameLabel.getText() + '_' +
                        this.studentLastNameLabel.getText());

                preparedStatement.execute();
                this.studentChangePasswrodFireld.setText("");
            } else {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(70),
                        this.studentChangePasswrodFireld);
                translateTransition.setFromX(0f);
                translateTransition.setFromY(0f);
                translateTransition.setByX(10f);
                translateTransition.setByY(10f);
                translateTransition.setCycleCount(3);
                translateTransition.setAutoReverse(true);
                translateTransition.play();
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void setStudentsData(String login) {
        try {
            String[] parseLogin = login.split("_");
            String firstName = parseLogin[0];
            String lastName = parseLogin[1];

            String sql = "SELECT * FROM Students WHERE First_Name = ? AND Last_name = ?";
            Connection con = dbConnection.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            resultSet = preparedStatement.executeQuery();

             this.studentFirstNameLabel.setText(resultSet.getString(1));
             this.studentLastNameLabel.setText(resultSet.getString(2));
             this.studentEmailLabel.setText(resultSet.getString(3));
             this.studentDepartmentLabel.setText(resultSet.getString(4));
             this.studentTermLabel.setText(resultSet.getString(5));
             this.studentDateOfBirthLabel.setText(resultSet.getString(6));
             this.studentPayingCompanyLabel.setText(resultSet.getString(7));
             this.studentAcPerformanceLabel.setText(resultSet.getString(8));
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            Stage stage = (Stage)this.logoutButton.getScene().getWindow();
            stage.close();

            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane loginRoot = loader.load(getClass().getResource(
                    "/login/login.fxml").openStream());
            LoginController loginController = (LoginController)loader.getController();
            Scene scene = new Scene(loginRoot);
            loginStage.setScene(scene);
            loginStage.setTitle("Institute Managing System");
            loginStage.setResizable(false);
            loginStage.show();

        }
        catch(IOException ex) {
            System.err.println("Error " + ex);
        }
    }

}
