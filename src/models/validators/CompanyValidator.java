package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Company;

public class CompanyValidator {
    public static List<String> validate(Company c) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        return errors;
    }

    public static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "新規取引先名が未入力です。";
        }
        return "";
    }
}