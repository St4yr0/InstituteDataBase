package administrators;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import dbUtil.dbConnection;
import students.StudentsData;
import teachers.TeachersData;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdministratorsController implements Initializable {
    // Students
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dateofbirth;
    @FXML
    private TextField term;
    @FXML
    private TextField department;
    @FXML
    private TextField payingcompany;
    @FXML
    private TextField acperformance;
    @FXML
    private TextField firstNameToDelete;
    @FXML
    private TextField lastNameToDelete;


    @FXML
    private TableView<StudentsData> studenttable;
    @FXML
    private TableColumn<StudentsData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentsData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentsData, String> emailcolumn;
    @FXML
    private TableColumn<StudentsData, String> dateofbirthcolumn;
    @FXML
    private TableColumn<StudentsData, String> termcolumn;
    @FXML
    private TableColumn<StudentsData, String> departmentcolumn;
    @FXML
    private TableColumn<StudentsData, String> payingcompanycolumn;
    @FXML
    private TableColumn<StudentsData, String> acperformancecolumn;

    // Teachers

    @FXML
    private TextField teacherFirstName;
    @FXML
    private TextField teacherLastName;
    @FXML
    private TextField teacherEmail;
    @FXML
    private TextField teacherSalary;
    @FXML
    private TextField teacherDegree;
    @FXML
    private TextField teacherSubject;
    @FXML
    private TextField getTeacherFirstNameToDelete;
    @FXML
    private TextField getTeacherLastNameToDelete;

    @FXML
    private TableView<TeachersData> teacherTable;
    @FXML
    private TableColumn<TeachersData, String> teacherFNameColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherLNameColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherEmailColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherSalaryColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherDegreeColumn;
    @FXML
    private TableColumn<TeachersData, String> teacherSubjectColumn;

    // Administrative staff

    @FXML
    private TextField adstaffFName;
    @FXML
    private TextField adstaffLName;
    @FXML
    private TextField adstaffEmail;
    @FXML
    private TextField adstaffSalary;
    @FXML
    private TextField adstaffDepartment;
    @FXML
    private TextField adStaffFnameToDelete;
    @FXML
    private TextField adStaffLnameToDelete;

    @FXML
    private TableView<AdministrativeStaffData> adstaffTable;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adstaffFNameColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adstaffLNameColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adstaffEmailColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adstaffSalaryColumn;
    @FXML
    private TableColumn<AdministrativeStaffData, String> adstaffDepartmentColumn;

    // Partner Companies

    @FXML
    private TextField partnerApellative;
    @FXML
    private TextField partnerRequisite;
    @FXML
    private TextField partnerContPerson;
    @FXML
    private TextField partnerContNumber;
    @FXML
    private TextField partnerAppellativeToDelete;

    @FXML
    private TableView<PartnerCompaniesData> partnerTable;
    @FXML
    private TableColumn<PartnerCompaniesData, String> partnerApellativeColumn;
    @FXML
    private TableColumn<PartnerCompaniesData, String> partnerRequisiteColumn;
    @FXML
    private TableColumn<PartnerCompaniesData, String> partnerContPersonColumn;
    @FXML
    private TableColumn<PartnerCompaniesData, String> partnerContNumberColumn;


    private dbConnection db_connection;

    private ObservableList<StudentsData>            studentsData;
    private ObservableList<TeachersData>            teachersData;
    private ObservableList<PartnerCompaniesData>    partnerCompaniesData;
    private ObservableList<AdministrativeStaffData> administrativeStaffData;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db_connection = new dbConnection();

    }

    @FXML
    private void loadStudentData(ActionEvent event) {
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
                        resultSet.getString(7), resultSet.getString(8)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.firstnamecolumn.setCellValueFactory(    new PropertyValueFactory<>("FIRST_NAME"));
        this.lastnamecolumn.setCellValueFactory(     new PropertyValueFactory<>("LAST_NAME"));
        this.emailcolumn.setCellValueFactory(        new PropertyValueFactory<>("EMAIL"));
        this.dateofbirthcolumn.setCellValueFactory(  new PropertyValueFactory<>("DATE_OF_BIRTH"));
        this.departmentcolumn.setCellValueFactory(   new PropertyValueFactory<>("DEPARTMENT"));
        this.termcolumn.setCellValueFactory(         new PropertyValueFactory<>("TERM"));
        this.payingcompanycolumn.setCellValueFactory(new PropertyValueFactory<>("PAYING_COMPANY"));
        this.acperformancecolumn.setCellValueFactory(new PropertyValueFactory<>("AC_PERFORMANCE"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.studentsData);
    }

    @FXML
    private void addStudent(ActionEvent event) {
        try {
            String sql_stud = "INSERT INTO Students(First_Name, Last_Name, Email, Date_Of_Birth, " +
                    "Department, term, Paying_Company, Ac_performance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String sql_log = "INSERT INTO login(username, password, division) VALUES (?, ?, ?)";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForStud = con.prepareStatement(sql_stud);
            PreparedStatement preparedStatementForLog = con.prepareStatement(sql_log);

            preparedStatementForStud.setString(1, this.firstname.getText());
            preparedStatementForStud.setString(2, this.lastname.getText());
            preparedStatementForStud.setString(3, this.email.getText());
            preparedStatementForStud.setString(4, this.dateofbirth.getEditor().getText());
            preparedStatementForStud.setString(5, this.department.getText());
            preparedStatementForStud.setString(6, this.term.getText());
            preparedStatementForStud.setString(7, this.payingcompany.getText());
            preparedStatementForStud.setString(8, this.acperformance.getText());

            preparedStatementForLog.setString(1, this.firstname.getText() + '_' +
                    this.lastname.getText());
            preparedStatementForLog.setString(2, this.lastname.getText());
            preparedStatementForLog.setString(3, "Student");

            preparedStatementForStud.execute();
            preparedStatementForLog.execute();
            loadStudentData(event);
            clearFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        try {
            String sql = "DELETE FROM Students WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            String sql_log = "DELETE FROM login WHERE username = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForStud = con.prepareStatement(sql);
            PreparedStatement preparedStatementForLog = con.prepareStatement(sql_log);


            preparedStatementForStud.setString(1, this.firstNameToDelete.getText());
            preparedStatementForStud.setString(2, this.lastNameToDelete.getText());

            preparedStatementForLog.setString(1, this.firstNameToDelete.getText() + '_' +
                    this.lastNameToDelete.getText());

            preparedStatementForStud.execute();
            preparedStatementForLog.execute();
            loadStudentData(event);
            clearFields(event);
            con.close();

        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
            this.firstname.setText("");
            this.lastname.setText("");
            this.email.setText("");
            this.department.setText("");
            this.term.setText("");
            this.payingcompany.setText("");
            this.acperformance.setText("");
            this.dateofbirth.setValue(null);
    }

    @FXML
    private void loadTeacherData(ActionEvent event) {
        try {
            Connection con = dbConnection.getConnection();
            this.teachersData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Teachers";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.teachersData.add(new TeachersData(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.teacherFNameColumn.setCellValueFactory(  new PropertyValueFactory<>("FIRST_NAME"));
        this.teacherLNameColumn.setCellValueFactory(  new PropertyValueFactory<>("LAST_NAME"));
        this.teacherEmailColumn.setCellValueFactory(  new PropertyValueFactory<>("EMAIL"));
        this.teacherSalaryColumn.setCellValueFactory( new PropertyValueFactory<>("SALARY"));
        this.teacherDegreeColumn.setCellValueFactory( new PropertyValueFactory<>("DEGREE"));
        this.teacherSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("SUBJECT"));


        this.teacherTable.setItems(null);
        this.teacherTable.setItems(this.teachersData);
    }

    @FXML
    private void addTeacher(ActionEvent event) {
        try {
            String sql = "INSERT INTO Teachers(First_Name, Last_Name, Email, Salary, " +
                    "Degree, Subject) VALUES (?, ?, ?, ?, ?, ?)";
            String sql_log = "INSERT INTO login(username, password, division) VALUES (?, ?, ?)";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            PreparedStatement preparedStatementForLog = con.prepareStatement(sql_log);

            preparedStatement.setString(1, this.teacherFirstName.getText());
            preparedStatement.setString(2, this.teacherLastName.getText());
            preparedStatement.setString(3, this.teacherEmail.getText());
            preparedStatement.setString(4, this.teacherSalary.getText());
            preparedStatement.setString(5, this.teacherDegree.getText());
            preparedStatement.setString(6, this.teacherSubject.getText());

            preparedStatementForLog.setString(1, this.teacherFirstName.getText() + '_' +
                    this.teacherLastName.getText());
            preparedStatementForLog.setString(2, this.teacherLastName.getText());
            preparedStatementForLog.setString(3, "Teacher");

            preparedStatement.execute();
            preparedStatementForLog.execute();
            loadTeacherData(event);
            clearTeacherFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void deleteTeacher(ActionEvent event) {
        try {
            String sql = "DELETE FROM Teachers WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            String sql_log = "DELETE FROM login WHERE username = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatementForTeach = con.prepareStatement(sql);
            PreparedStatement preparedStatementForLog = con.prepareStatement(sql_log);


            preparedStatementForTeach.setString(1, this.getTeacherFirstNameToDelete.getText());
            preparedStatementForTeach.setString(2, this.getTeacherLastNameToDelete.getText());

            preparedStatementForLog.setString(1, this.getTeacherFirstNameToDelete.getText() + '_' +
                    this.getTeacherLastNameToDelete.getText());

            preparedStatementForTeach.execute();
            preparedStatementForLog.execute();
            loadTeacherData(event);
            clearTeacherFields(event);
            con.close();

        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void clearTeacherFields(ActionEvent event) {
        this.teacherFirstName.setText("");
        this.teacherLastName.setText("");
        this.teacherEmail.setText("");
        this.teacherSalary.setText("");
        this.teacherDegree.setText("");
        this.teacherSubject.setText("");
    }

    @FXML
    private void loadAdStaffData(ActionEvent event) {
        try {
            Connection con = dbConnection.getConnection();
            this.administrativeStaffData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Administrative_staff";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.administrativeStaffData.add(new AdministrativeStaffData(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.adstaffFNameColumn.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
        this.adstaffLNameColumn.setCellValueFactory(      new PropertyValueFactory<>("LAST_NAME"));
        this.adstaffEmailColumn.setCellValueFactory(      new PropertyValueFactory<>("EMAIL"));
        this.adstaffSalaryColumn.setCellValueFactory(     new PropertyValueFactory<>("SALARY"));
        this.adstaffDepartmentColumn.setCellValueFactory( new PropertyValueFactory<>("DEPARTMENT"));

        this.adstaffTable.setItems(null);
        this.adstaffTable.setItems(this.administrativeStaffData);
    }

    @FXML
    private void addAdStaff(ActionEvent event) {
        try {
            String sql = "INSERT INTO Administrative_staff(First_Name, Last_Name, Email, Salary, " +
                    "Department) VALUES (?, ?, ?, ?, ?)";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, this.adstaffFName.getText());
            preparedStatement.setString(2, this.adstaffLName.getText());
            preparedStatement.setString(3, this.adstaffEmail.getText());
            preparedStatement.setString(4, this.adstaffSalary.getText());
            preparedStatement.setString(5, this.adstaffDepartment.getText());

            preparedStatement.execute();
            loadAdStaffData(event);
            clearAdStaffFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void deleteManager(ActionEvent event) {
        try {
            String sql = "DELETE FROM Administrative_staff WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, this.adStaffFnameToDelete.getText());
            preparedStatement.setString(2, this.adStaffLnameToDelete.getText());

            preparedStatement.execute();
            loadAdStaffData(event);
            clearAdStaffFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void clearAdStaffFields(ActionEvent event) {
        this.adstaffFName.setText("");
        this.adstaffLName.setText("");
        this.adstaffEmail.setText("");
        this.adstaffSalary.setText("");
        this.adstaffDepartment.setText("");
    }

    @FXML
    private void loadPartnerData(ActionEvent event) {
        try {
            Connection con = dbConnection.getConnection();
            this.partnerCompaniesData = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Partner_Companies";
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while(resultSet.next()) {
                this.partnerCompaniesData.add(new PartnerCompaniesData(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.partnerApellativeColumn.setCellValueFactory(  new PropertyValueFactory<>("APPELLATIVE"));
        this.partnerRequisiteColumn.setCellValueFactory(   new PropertyValueFactory<>("REQUISITE"));
        this.partnerContPersonColumn.setCellValueFactory(  new PropertyValueFactory<>("CONTACT_PERSON"));
        this.partnerContNumberColumn.setCellValueFactory(  new PropertyValueFactory<>("CONTACT_NUMBER"));

        this.partnerTable.setItems(null);
        this.partnerTable.setItems(this.partnerCompaniesData);
    }

    @FXML
    private void addPartner(ActionEvent event) {
        try {
            String sql = "INSERT INTO Partner_Companies(Appellative, Requisite, Contact_Person, Contact_Number) " +
                    "VALUES (?, ?, ?, ?)";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, this.partnerApellative.getText());
            preparedStatement.setString(2, this.partnerRequisite.getText());
            preparedStatement.setString(3, this.partnerContPerson.getText());
            preparedStatement.setString(4, this.partnerContNumber.getText());

            preparedStatement.execute();
            loadPartnerData(event);
            clearPartnerFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void deletePartner(ActionEvent event) {
        try {
            String sql = "DELETE FROM Partner_companies WHERE Appellative = ?";
            Connection con = dbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, this.partnerAppellativeToDelete.getText());

            preparedStatement.execute();
            loadPartnerData(event);
            clearPartnerFields(event);
            con.close();
        }
        catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void clearPartnerFields(ActionEvent event) {
        this.partnerApellative.setText("");
        this.partnerContNumber.setText("");
        this.partnerContPerson.setText("");
        this.partnerRequisite.setText("");
    }

}
