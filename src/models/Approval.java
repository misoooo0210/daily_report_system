package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "approvals")
@NamedQueries ({
    @NamedQuery(
            name = "getAllNotApprovedReports",
            query = "SELECT a FROM Approval AS a WHERE a.approval_result = 0 ORDER BY a.approval_id ASC"
            ),
    @NamedQuery(
            name = "getAllApprovedReports",
            query = "SELECT a FROM Approval AS a WHERE a.approval_result = 1 ORDER BY a.approval_id ASC"
            )
})

@Entity
public class Approval{
    @OneToOne(mappedBy = "approval")
    private Report report;

    @Id
    @Column(name = "approval_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer approval_id;

    @Column(name = "approver", nullable = false)
    private String approver;

    @Column(name = "approved_date")
    private Date approved_date;

    @Column(name = "approval_result", nullable = false)
    private Integer approval_result;

    @Lob
    @Column(name = "approval_comment")
    private String approval_comment;


    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Integer getApproval_id() {
        return approval_id;
    }

    public void setApproval_id(Integer approval_id) {
        this.approval_id = approval_id;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(Date approved_date) {
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