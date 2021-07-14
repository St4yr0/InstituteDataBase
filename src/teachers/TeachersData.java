package teachers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeachersData {
    private final StringProperty FIRST_NAME;
    private final StringProperty LAST_NAME;
    private final StringProperty EMAIL;
    private final StringProperty SALARY;
    private final StringProperty DEGREE;
    private final StringProperty SUBJECT;

    public TeachersData(String firstName, String lastName, String email, String salary,
                        String degree, String subject) {

        this.FIRST_NAME     = new SimpleStringProperty(firstName);
        this.LAST_NAME      = new SimpleStringProperty(lastName);
        this.EMAIL          = new SimpleStringProperty(email);
        this.SALARY         = new SimpleStringProperty(salary);
        this.DEGREE         = new SimpleStringProperty(degree);
        this.SUBJECT        = new SimpleStringProperty(subject);
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

    public String getDEGREE() {
        return DEGREE.get();
    }

    public StringProperty DEGREEProperty() {
        return DEGREE;
    }

    public void setDEGREE(String DEGREE) {
        this.DEGREE.set(DEGREE);
    }

    public String getSUBJECT() {
        return SUBJECT.get();
    }

    public StringProperty SUBJECTProperty() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT.set(SUBJECT);
    }
}
