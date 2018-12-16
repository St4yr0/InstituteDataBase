package login;

import administrators.AdministratorsController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;
import teachers.TeachersController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<division> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.is_db_connected()) {
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("Not Connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(division.values()));
    }

    @FXML
    public void Login(ActionEvent event) {
        try {
            if (this.loginModel.is_login(this.login.getText(), this.password.getText(),
                    ((division)this.combobox.getValue()).toString())) {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch (((division)this.combobox.getValue()).toString()) {
                    case "Administrator":
                        administrator_login();
                        break;
                    case "Teacher":
                        teacher_login();
                        break;
                    case "Student":
                        student_login();
                }
            } else {
                this.loginStatus.setText("Wrong Login or Password");
            }
        }
        catch (Exception localException) {
            System.err.println("Error " + localException);
        }
    }

    private void student_login() {
        try {
            Stage studentStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane studentsRoot = loader.load(getClass().getResource(
                    "/students/studentsFXML.fxml").openStream());
            StudentsController studentsController = (StudentsController)loader.getController();

            Scene scene = new Scene(studentsRoot);
            studentStage.setScene(scene);
            studentStage.setTitle("Student Dashboard");
            studentStage.setResizable(false);
            studentStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void teacher_login() {
        try {
            Stage teacherStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane teachersRoot = loader.load(getClass().getResource(
                    "/teachers/teachersFXML.fxml").openStream());

            TeachersController teachersController = (TeachersController)loader.getController();
            Scene scene = new Scene(teachersRoot);

            teacherStage.setScene(scene);
            teacherStage.setTitle("Teacher Dashboard");
            teacherStage.setResizable(false);
            teacherStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void administrator_login() {
        try {
            Stage administratorStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane administratorRoot = loader.load(getClass().getResource(
                    "/administrators/administratorsFXML.fxml").openStream());

            AdministratorsController administratorsController = (AdministratorsController)loader.getController();
            Scene scene = new Scene(administratorRoot);

            administratorStage.setScene(scene);
            administratorStage.setTitle("Administrator Dashboard");
            administratorStage.setResizable(false);
            administratorStage.show();
        }
        catch (IOException ex) {
            System.err.println("Error here" + ex);
        }
    }
}
