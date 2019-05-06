package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "approvals")
@NamedQueries ({
    @NamedQuery(
    name = "getAllApprovals",
    query = "SELECT a FROM Approval AS a ORDER BY a.id DESC"
    ),
    @NamedQuery(
    name = "getApprovalsCount",
    query = "SELECT COUNT(a) FROM Approval AS a"
    )
    //未承認の日報だけを取り出すNamedqueryを作成する
})

@Entity
public class Approval {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "approver", nullable = false)
    private String approver;

    @Column(name = "approved_date", nullable = false)
    private Timestamp approved_date;

    @Column(name = "approval_result", nullable = false)
    private Integer approval_result;

    @Lob
    @Column(name = "approval_comment")
    private String approval_comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Timestamp getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(Timestamp approved_date) {
        this.approved_date = approved_date;
    }

    public Integer getApproval_result() {
        return approval_result;
    }

    public void setApproval_result(Integer approval_result) {
        this.approval_result = approval_result;
    }

    public String getApproval_comment() {
        return approval_comment;
    }

    public void setApproval_comment(String approval_comment) {
        this.approval_comment = approval_comment;
    }

}