package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reporting")
public class Reporting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reportid")
    private int reportID;

    @Column(name="reporter")
    private int reporterID;
    @Column(name="reportpostid")
    private int reportedPostID;
    @Column(name="reportdate")
    private Date reportedDate;

    // Getters
    public int getReportID() {
        return reportID;
    }

    public int getReporterID() {
        return reporterID;
    }

    public int getReportedPostID() {
        return reportedPostID;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    // Setters
    public void setReportedDate(Date newReportedDate) {
        this.reportedDate = newReportedDate;
    }

    public void setReporterID(int newReporterID) {
        this.reporterID = newReporterID;
    }

    public void setReportedPostID(int newReportedPostID) {
        this.reportedPostID = newReportedPostID;
    }
}
