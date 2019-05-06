package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Approval;

public class ApprovalValidator {
  //Validationを実行する
    public static List<String> validate(Approval a) {
    List<String> errors = new ArrayList<String>();

    String approval_result_error = _validateApproval_result(a.getApproval_result());
    if(!approval_result_error.equals("")) {
        errors.add(approval_result_error);
    }

    //承認結果が否認のときだけ,comment validationを実行する
    if(a.getApproval_result() == 1) {
    String approval_comment_error = _validateApproval_comment(a.getApproval_comment());
    if(!approval_comment_error.equals("")) {
        errors.add(approval_comment_error);
    }
    }

    return errors;

    }

    public static String _validateApproval_result(Integer approval_result) {
        if(approval_result == null || approval_result.equals("")) {
            return "承認結果を選択してください。";
        }
        return "";
    }

    public static String _validateApproval_comment(String approval_comment) {
        if(approval_comment == null || approval_comment.equals("")) {
            return "否認の場合、コメント入力は必須です。";
        }
        return "";
    }

}
