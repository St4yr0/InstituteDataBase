package administrators.editAdministrativeStaff;

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

public class EditorAdStaffController implements Initializable {
    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField newEmailFIeld;

    @FXML
    private TextField newSalaryField;

    @FXML
    private TextField newDepartmentFIeld;

    @FXML
    private Label emailLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label departmentLabel;

    private dbConnection db_connection;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }

    @FXML
    public void setAdStaffData(String firstName, String lastName, String email, String salary, String department) {
        this.firstNameLabel.setText(firstName);
        this.lastNameLabel.setText(lastName);
        this.emailLabel.setText(email);
        this.salaryLabel.setText(salary);
        this.departmentLabel.setText(department);
    }



    @FXML
    void changeDepartment(ActionEvent event) {
        try {
            if (!this.newDepartmentFIeld.getText().equals("")) {
                String sql = "UPDATE Administrative_staff SET Department = ? WHERE First_Name = ? AND Last_Name = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newDepartmentFIeld.getText());
                preparedStatement.setString(2, this.firstNameLabel.getText());
                preparedStatement.setString(3, this.lastNameLabel.getText());

                preparedStatement.execute();
                this.departmentLabel.setText(this.newDepartmentFIeld.getText());
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
    void changeEmail(ActionEvent event) {
        try {
            if (!this.newEmailFIeld.getText().equals("")) {
                String sql = "UPDATE Administrative_staff SET Email = ? WHERE First_Name = ? AND Last_Name = ?";
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
            if(!this.newSalaryField.getText().equals("")) {
                String sql = "UPDATE Administrative_staff SET Salary = ? WHERE First_Name = ? AND Last_Name = ?";
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
    void deleteAdStaff(ActionEvent event) {
        try {
            String sql = "DELETE FROM Administrative_staff WHERE First_Name = ? AND Last_Name = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForStud = con.prepareStatement(sql);


            preparedStatementForStud.setString(1, this.firstNameLabel.getText());
            preparedStatementForStud.setString(2, this.lastNameLabel.getText());

            preparedStatementForStud.execute();
            con.close();

            Stage stage = (Stage)this.newSalaryField.getScene().getWindow();
            stage.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void done(ActionEvent event) {
        Stage stage = (Stage)newEmailFIeld.getScene().getWindow();
        stage.close();
    }


}
