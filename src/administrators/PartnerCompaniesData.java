package administrators;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartnerCompaniesData {
    private final StringProperty APPELLATIVE;
    private final StringProperty REQUISITE;
    private final StringProperty CONTACT_PERSON;
    private final StringProperty CONTACT_NUMBER;

    public PartnerCompaniesData(String appelative, String requisite, String contactPerson, String contactNumber) {
        this.APPELLATIVE = new SimpleStringProperty(appelative);
        this.REQUISITE = new SimpleStringProperty(requisite);
        this.CONTACT_NUMBER = new SimpleStringProperty(contactNumber);
        this.CONTACT_PERSON = new SimpleStringProperty(contactPerson);
    }

    public String getAPPELLATIVE() {
        return APPELLATIVE.get();
    }

    public StringProperty APPELLATIVEProperty() {
        return APPELLATIVE;
    }

    public void setAPPELLATIVE(String APPELLATIVE) {
        this.APPELLATIVE.set(APPELLATIVE);
    }

    public String getREQUISITE() {
        return REQUISITE.get();
    }

    public StringProperty REQUISITEProperty() {
        return REQUISITE;
    }

    public void setREQUISITE(String REQUISITE) {
        this.REQUISITE.set(REQUISITE);
    }

    public String getCONTACT_PERSON() {
        return CONTACT_PERSON.get();
    }

    public StringProperty CONTACT_PERSONProperty() {
        return CONTACT_PERSON;
    }

    public void setCONTACT_PERSON(String CONTACT_PERSON) {
        this.CONTACT_PERSON.set(CONTACT_PERSON);
    }

    public String getCONTACT_NUMBER() {
        return CONTACT_NUMBER.get();
    }

    public StringProperty CONTACT_NUMBERProperty() {
        return CONTACT_NUMBER;
    }

    public void setCONTACT_NUMBER(String CONTACT_NUMBER) {
        this.CONTACT_NUMBER.set(CONTACT_NUMBER);
    }
}
