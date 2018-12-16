package administrators;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdministrativeStaffData {
    private final StringProperty FIRST_NAME;
    private final StringProperty LAST_NAME;
    private final StringProperty EMAIL;
    private final StringProperty SALARY;
    private final StringProperty DEPARTMENT;

    public AdministrativeStaffData(String firstName, String lastName, String email, String salary, String department) {
        this.DEPARTMENT = new SimpleStringProperty(department);
        this.EMAIL      = new SimpleStringProperty(email);
        this.FIRST_NAME = new SimpleStringProperty(firstName);
        this.LAST_NAME  = new SimpleStringProperty(lastName);
        this.SALARY     = new SimpleStringProperty(salary);
    }

    public String getFIRST_NAME() {
        return FIRST_NAME.get();
    }

    public StringProperty FIRST_NAMEProperty() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME.set(FIRST_NAME);
    }

    public String getLAST_NAME() {
        return LAST_NAME.get();
    }

    public StringProperty LAST_NAMEProperty() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME.set(LAST_NAME);
    }

    public String getEMAIL() {
        return EMAIL.get();
    }

    public StringProperty EMAILProperty() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL.set(EMAIL);
    }

    public String getSALARY() {
        return SALARY.get();
    }

    public StringProperty SALARYProperty() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY.set(SALARY);
    }

    public String getDEPARTMENT() {
        return DEPARTMENT.get();
    }

    public StringProperty DEPARTMENTProperty() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT.set(DEPARTMENT);
    }
}
