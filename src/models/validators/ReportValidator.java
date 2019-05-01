package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportValidator {
    //Validationを実行する
    public static List<String> validate(Report r) {
    List<String> errors = new ArrayList<String>();

    String title_error = _validateTitle(r.getTitle());
    if(!title_error.equals("")) {
        errors.add(title_error);
    }

    String content_error = _validateContent(r.getContent());
    if(!content_error.equals("")) {
        errors.add(content_error);
    }

    String company_error = _validateCompany(r.getCompany());
    if(!company_error.equals("")) {
        errors.add(company_error);
    }

    String meet_at_error = _validateMeet_at(r.getMeet_at());
    if(!meet_at_error.equals("")) {
        errors.add(meet_at_error);
    }

    return errors;
    }

    public static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
        }
        return "";
    }

    public static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
        }
        return "";
    }

    public static String _validateCompany(String company) {
        if(company == null || company.equals("")) {
            return "取引先名を入力してください。";
        }
        return "";
    }

    public static String _validateMeet_at(String meet_at) {
        if(meet_at == null || meet_at.equals("")) {
            return "商談場所を入力してください。";
        }
        return "";
    }
}
