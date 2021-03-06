package com.ontrack.ontrackriders.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
 
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,40})";
 
    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
 
    public boolean validate(final String password) {
 
        matcher = pattern.matcher(password);
        return matcher.matches();
 
    }
}
