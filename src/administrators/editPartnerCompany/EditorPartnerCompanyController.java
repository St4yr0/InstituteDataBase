package administrators.editPartnerCompany;

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

public class EditorPartnerCompanyController implements Initializable {

    @FXML
    private Label appellativeLabel;

    @FXML
    private Label contPersonLabel;

    @FXML
    private TextField newAppellativeField;

    @FXML
    private TextField newRequisiteField;

    @FXML
    private TextField newContPersonField;

    @FXML
    private TextField newContNumberField;

    @FXML
    private Label requisiteLabel;

    @FXML
    private Label contNumberLabel;

    private dbConnection db_connection;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();
    }

    @FXML
    public void setPartnerData(String appellative, String requisite, String contPerson, String contNumber) {
        this.appellativeLabel.setText(appellative);
        this.requisiteLabel.setText(requisite);
        this.contPersonLabel.setText(contPerson);
        this.contNumberLabel.setText(contNumber);
    }

    @FXML
    void changeAppellative(ActionEvent event) {
        try {
            if (!this.newAppellativeField.equals("")) {
                String sql = "UPDATE Partner_Companies SET Appellative = ? WHERE Appellative = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newAppellativeField.getText());
                preparedStatement.setString(2, this.appellativeLabel.getText());

                preparedStatement.execute();
                this.appellativeLabel.setText(this.newAppellativeField.getText());
                this.newAppellativeField.setText("");
            } else {
                return;
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeContNumber(ActionEvent event) {
        try {
            if (!this.newContNumberField.equals("")) {
                String sql = "UPDATE Partner_Companies SET Contact_Number = ? WHERE Appellative = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newContNumberField.getText());
                preparedStatement.setString(2, this.appellativeLabel.getText());

                preparedStatement.execute();
                this.contNumberLabel.setText(this.newContNumberField.getText());
                this.newContNumberField.setText("");
            } else {
                return;
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeContPerson(ActionEvent event) {
        try {
            if (!this.newContPersonField.equals("")) {
                String sql = "UPDATE Partner_Companies SET Contact_Person = ? WHERE Appellative = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newContPersonField.getText());
                preparedStatement.setString(2, this.appellativeLabel.getText());

                preparedStatement.execute();
                this.contPersonLabel.setText(this.newContPersonField.getText());
                this.newContPersonField.setText("");
            } else {
                return;
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void changeRequisite(ActionEvent event) {
        try {
            if (!this.newRequisiteField.equals("")) {
                String sql = "UPDATE Partner_Companies SET Requisite = ? WHERE Appellative = ?";
                Connection con = dbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                preparedStatement.setString(1, this.newRequisiteField.getText());
                preparedStatement.setString(2, this.appellativeLabel.getText());

                preparedStatement.execute();
                this.requisiteLabel.setText(this.newRequisiteField.getText());
                this.newRequisiteField.setText("");
            } else {
                return;
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void deletePartner(ActionEvent event) {
        try {
            String sql = "DELETE FROM Partner_Companies WHERE Appellative = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForStud = con.prepareStatement(sql);

            preparedStatementForStud.setString(1, this.appellativeLabel.getText());

            preparedStatementForStud.execute();
            con.close();

            Stage stage = (Stage)this.newAppellativeField.getScene().getWindow();
            stage.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    void done(ActionEvent event) {
        Stage stage = (Stage)newRequisiteField.getScene().getWindow();
        stage.close();

    }

}
