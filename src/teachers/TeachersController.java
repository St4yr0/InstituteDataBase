package teachers;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;
import students.StudentsData;

import administrators.AdministrativeStaffData;
import dbUtil.dbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeachersController implements Initializable {

    @FXML
    private Label teacherFirstNameLabel;
    @FXML
    private Label teacherLastNameLabel;
    @FXML
    private Label teacherEmailLabel;
    @FXML
    private Label teacherSalaryLabel;
    @FXML
    private Label teacherDegreeLabel;
    @FXML
    private Label teacherSubjectLabel;
    @FXML
    private TextField teacherChangePasswordField;
    @FXML
    private Button logoutButton;

    @FXML
    private TableView<TeachersData> teacherTable;
    @FXML
    private TableColumn<TeachersData, String> teacherFirstNameColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherLastNameColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherEmailColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherDegreeColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherSubjectColumn;

    @FXML
    private TableView<StudentsData> studentTable;
    @FXML
    private TableColumn<StudentsData, String> studentFNameColumn;
    @FXML
    private TableColumn<StudentsData, String> studentLNameColumn;
    @FXML
    private TableColumn<StudentsData, String> studentEmailColumn;
    @FXML
    private TableColumn<StudentsData, String> studentDOBColumn;
    @FXML
    private TableColumn<StudentsData, String> studentTermColumn;
    @FXML
    private TableColumn<StudentsData, String> studentDepartmentColumn;
    @FXML
    private TableColumn<StudentsData, String> studentAcPerformColumn;

    @FXML
    private TableView<AdministrativeStaffData> adStaffTable;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adStaffFirstNameColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adStaffLastNameColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adStaffEmailColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adStaffDepartmentColumn;

    private dbConnection db_connection;
    private ObservableList<StudentsData>            studentsData;
    private ObservableList<TeachersData>            teachersData;
    private ObservableList<AdministrativeStaffData> administrativeStaffData;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }

    @FXML
    private void loadOtherTeachers(ActionEvent event) {
        try {
            Connection con = dbConnection.getConnection();
            this.teachersData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Teachers";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.teachersData.add(new TeachersData(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), "",
                        resultSet.getString(5), resultSet.getString(6)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.teacherFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
        this.teacherLastNameColumn.setCellValueFactory( new PropertyValueFactory<>("LAST_NAME"));
        this.teacherEmailColumn.setCellValueFactory(    new PropertyValueFactory<>("EMAIL"));
        this.teacherDegreeColumn.setCellValueFactory(   new PropertyValueFactory<>("DEGREE"));
        this.teacherSubjectColumn.setCellValueFactory(  new PropertyValueFactory<>("SUBJECT"));


        this.teacherTable.setItems(null);
        this.teacherTable.setItems(this.teachersData);
    }

    @FXML
    private void changePassword(ActionEvent event) {
        try {
            if (!this.teacherChangePasswordField.getText().equals("")) {
                String sql = "UPDATE login SET password = ? WHERE username = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.teacherChangePasswordField.getText());
                preparedStatement.setString(2, this.teacherFirstNameLabel.getText() + '_' + this.teacherLastNameLabel.getText());

                preparedStatement.execute();
                this.teacherChangePasswordField.setText("");
            } else {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(70),
                        this.teacherChangePasswordField);
                translateTransition.setFromX(0f);
                translateTransition.setFromY(0f);
                translateTransition.setByX(10f);
                translateTransition.setByY(11f);
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
    public void setTeachersData(String login) {
        try {
            String[] parseLogin = login.split("_");
            String firstName = parseLogin[0];
            String lastName = parseLogin[1];

            String sql = "SELECT * FROM Teachers WHERE First_Name = ? AND Last_name = ?";
            Connection con = dbConnection.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            resultSet = preparedStatement.executeQuery();

            this.teacherFirstNameLabel.setText(resultSet.getString(1));
            this.teacherLastNameLabel.setText(resultSet.getString(2));
            this.teacherEmailLabel.setText(resultSet.getString(3));
            this.teacherSalaryLabel.setText(resultSet.getString(4));
            this.teacherDegreeLabel.setText(resultSet.getString(5));
            this.teacherSubjectLabel.setText(resultSet.getString(6));
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void loadStudents() {
        try {
            Connection con = dbConnection.getConnection();
            this.studentsData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Students";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.studentsData.add(new StudentsData(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        "", resultSet.getString(8)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.studentFNameColumn.setCellValueFactory(     new PropertyValueFactory<>("FIRST_NAME"));
        this.studentLNameColumn.setCellValueFactory(     new PropertyValueFactory<>("LAST_NAME"));
        this.studentEmailColumn.setCellValueFactory(     new PropertyValueFactory<>("EMAIL"));
        this.studentDOBColumn.setCellValueFactory(       new PropertyValueFactory<>("DATE_OF_BIRTH"));
        this.studentDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("DEPARTMENT"));
        this.studentTermColumn.setCellValueFactory(      new PropertyValueFactory<>("TERM"));
        this.studentAcPerformColumn.setCellValueFactory( new PropertyValueFactory<>("AC_PERFORMANCE"));

        this.studentTable.setItems(null);
        this.studentTable.setItems(this.studentsData);
    }

    @FXML
    private void loadAdministrativeStaff() {
        try {
            Connection con = dbConnection.getConnection();
            this.administrativeStaffData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Administrative_staff";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.administrativeStaffData.add(new AdministrativeStaffData(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), "",
                        resultSet.getString(5)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.adStaffFirstNameColumn.setCellValueFactory( new PropertyValueFactory<>("FIRST_NAME"));
        this.adStaffLastNameColumn.setCellValueFactory(  new PropertyValueFactory<>("LAST_NAME"));
        this.adStaffEmailColumn.setCellValueFactory(     new PropertyValueFactory<>("EMAIL"));
        this.adStaffDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("DEPARTMENT"));

        this.adStaffTable.setItems(null);
        this.adStaffTable.setItems(this.administrativeStaffData);
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
