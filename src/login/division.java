package login;

import org.jetbrains.annotations.Contract;

public enum division {
    Administrator, Student, Teacher;

    private division() {}

    @Contract(pure = true)
    public String value() {
        return name();
    }

//    public static division from_value(String str) {
//        return valueOf(str);
//    }
}
