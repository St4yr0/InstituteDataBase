package administrators.editStudent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditorStudentController implements Initializable {

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField newEmailFIeld;

    @FXML
    private TextField newTermField;

    @FXML
    private TextField newDepartmentFIeld;

    @FXML
    private TextField newPayingComField;

    @FXML
    private TextField newAcPerformanceField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label termLabel;

    @FXML
    private Label departmantLabel;

    @FXML
    private Label payingCompanyLabel;

    @FXML
    private Label acPerformanceLabel;

    private dbConnection db_connection;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }

    @FXML
    public void setStudentData(String firstName, String lastName, String email, String dateOfBirth, String term,
                               String department, String payingCompany, String acPerformance) {
        this.firstNameLabel.setText(firstName);
        this.lastNameLabel.setText(lastName);
        this.emailLabel.setText(email);
        this.dobLabel.setText(dateOfBirth);
        this.termLabel.setText(term);
        this.departmantLabel.setText(department);
        this.payingCompanyLabel.setText(payingCompany);
        this.acPerformanceLabel.setText(acPerformance);
    }


    @FXML
    public void changeEmail(ActionEvent event) {
        try {
            if (!this.newEmailFIeld.getText().equals("")) {
                String sql = "UPDATE Students SET Email = ? WHERE First_Name = ? AND Last_Name = ?";
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
    public void changeTerm(ActionEvent event) {
        try {
            if(!this.newTermField.getText().equals("")) {
                String sql = "UPDATE Students SET Term = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newTermField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.termLabel.setText(this.newTermField.getText());
                this.newTermField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void changeDepartment(ActionEvent event) {
        try {
            if (!this.newDepartmentFIeld.getText().equals("")) {
                String sql = "UPDATE Students SET Department = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newDepartmentFIeld.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.departmantLabel.setText(this.newDepartmentFIeld.getText());
                this.newDepartmentFIeld.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void changePayingCompany(ActionEvent event) {
        try {
            if (!this.newPayingComField.getText().equals("")) {
                String sql = "UPDATE Students SET Paying_Company = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newPayingComField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.payingCompanyLabel.setText(this.newPayingComField.getText());
                this.newPayingComField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void changeAcPerformance(ActionEvent event) {
        try {
            if (!this.newAcPerformanceField.getText().equals("")) {
                String sql = "UPDATE Students SET Ac_Performance = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newAcPerformanceField.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.acPerformanceLabel.setText(this.newAcPerformanceField.getText());
                this.newAcPerformanceField.setText("");
            } else {
                return;
            }
        }
        catch(SQLException ex) {
            System.err.println("Error " + ex);
        }
    }


    @FXML
    private void deleteStudent(ActionEvent event) {
        try {
            String sql = "DELETE FROM Students WHERE First_Name = ? AND Last_Name = ?";
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

            Stage stage = (Stage)this.newTermField.getScene().getWindow();
            stage.close();


        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    public void done(ActionEvent event) {
        Stage stage = (Stage)newEmailFIeld.getScene().getWindow();
        stage.close();

    }
}
