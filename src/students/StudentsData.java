package students;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentsData {
    private final StringProperty FIRST_NAME;
    private final StringProperty LAST_NAME;
    private final StringProperty EMAIL;
    private final StringProperty DATE_OF_BIRTH;
    private final StringProperty TERM;
    private final StringProperty DEPARTMENT;
    private final StringProperty PAYING_COMPANY;
    private final StringProperty AC_PERFORMANCE;

    public StudentsData(String first_name, String last_name, String email, String date_of_birth, String term,
                        String department, String paying_company, String ac_performance) {

        this.FIRST_NAME     = new SimpleStringProperty(first_name);
        this.LAST_NAME      = new SimpleStringProperty(last_name);
        this.EMAIL          = new SimpleStringProperty(email);
        this.DATE_OF_BIRTH  = new SimpleStringProperty(date_of_birth);
        this.TERM           = new SimpleStringProperty(term);
        this.DEPARTMENT     = new SimpleStringProperty(department);
        this.PAYING_COMPANY = new SimpleStringProperty(paying_company);
        this.AC_PERFORMANCE = new SimpleStringProperty(ac_performance);
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

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH.get();
    }

    public StringProperty DATE_OF_BIRTHProperty() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH.set(DATE_OF_BIRTH);
    }

    public String getTERM() {
        return TERM.get();
    }

    public StringProperty TERMProperty() {
        return TERM;
    }

    public void setTERM(String TERM) {
        this.TERM.set(TERM);
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

    public String getPAYING_COMPANY() {
        return PAYING_COMPANY.get();
    }

    public StringProperty PAYING_COMPANYProperty() {
        return PAYING_COMPANY;
    }

    public void setPAYING_COMPANY(String PAYING_COMPANY) {
        this.PAYING_COMPANY.set(PAYING_COMPANY);
    }

    public String getAC_PERFORMANCE() {
        return AC_PERFORMANCE.get();
    }

    public StringProperty AC_PERFORMANCEProperty() {
        return AC_PERFORMANCE;
    }

    public void setAC_PERFORMANCE(String AC_PERFORMANCE) {
        this.AC_PERFORMANCE.set(AC_PERFORMANCE);
    }
}
