package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Company;
import utils.DBUtil;

public class CompanyValidator {
    public static List<String> validate(Company c, Boolean name_duplicate_check_flag) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName(), name_duplicate_check_flag);
        if(!name_error.equals("")) {
            errors.add(name_error);
        }
        return errors;
    }

        //取引先名
        public static String _validateName(String name, Boolean name_duplicate_check_flag) {
        //必須入力チェック
        if(name == null || name.equals("")) {
            return "新規取引先名が未入力です。";
        }

        // 既に登録されている取引先名との重複チェック
        if(name_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long companies_count = (long)em.createNamedQuery("checkRegisteredName", Long.class)
                                             .setParameter("name", name)
                                             .getSingleResult();
            em.close();
            if(companies_count > 0) {
                return "入力された取引先名は既に存在しています。";
            }
        }
        return "";
    }

}