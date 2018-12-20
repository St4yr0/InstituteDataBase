package administrators.editTeacher;

import dbUtil.dbConnection;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditorTeacherController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField newEmailFIeld;

    @FXML
    private TextField newSalaryField;

    @FXML
    private TextField newDegreeField;

    @FXML
    private TextField newSubjectField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label degreeLabel;

    @FXML
    private Label subjectLabel;

    private dbConnection db_connection;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }

    @FXML
    public void setTeacherData(String firstName, String lastName, String email, String salary, String degree,
                               String subject) {
        this.firstNameLabel.setText(firstName);
        this.lastNameLabel.setText(lastName);
        this.emailLabel.setText(email);
        this.salaryLabel.setText(salary);
        this.degreeLabel.setText(degree);
        this.subjectLabel.setText(subject);
    }

    @FXML
    void changeDegree(ActionEvent event) {
        try {
            if (!this.newDegreeField.getText().equals("")) {
                String sql = "UPDATE Teachers SET Degree = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newDegreeField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.degreeLabel.setText(this.newDegreeField.getText());
                this.newDegreeField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeEmail(ActionEvent event) {
        try {
            if (!this.newEmailFIeld.getText().equals("")) {
                String sql = "UPDATE Teachers SET Email = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newEmailFIeld.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.emailLabel.setText(this.newEmailFIeld.getText());
                this.newEmailFIeld.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeSalary(ActionEvent event) {
        try {
            if (!this.newSalaryField.getText().equals("")) {
                String sql = "UPDATE Teachers SET Salary = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newSalaryField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.salaryLabel.setText(this.newSalaryField.getText());
                this.newSalaryField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeSubject(ActionEvent event) {
        try {
            if (!this.newSubjectField.getText().equals("")) {
                String sql = "UPDATE Teachers SET Subject = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newSubjectField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.subjectLabel.setText(this.newSubjectField.getText());
                this.newSubjectField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void deleteTeacher(ActionEvent event) {
        try {
            String sql = "DELETE FROM Teachers WHERE First_Name = ? AND Last_Name = ?";
            String sql_log = "DELETE FROM login WHERE username = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForStud = con.prepareStatement(sql);
            PreparedStatement preparedStatementForLog = con.prepareStatement(sql_log);


            preparedStatementForStud.setString(1, this.firstNameLabel.getText());
            preparedStatementForStud.setString(2, this.lastNameLabel.getText());

            preparedStatementForLog.setString(1, this.firstNameLabel.getText() + '_' +
                    this.lastNameLabel.getText());

            preparedStatementForStud.execute();
            preparedStatementForLog.execute();
            con.close();

            Stage stage = (Stage)this.newSubjectField.getScene().getWindow();
            stage.close();


        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void done(ActionEvent event) {
        Stage stage = (Stage)newSubjectField.getScene().getWindow();
        stage.close();
    }

}
