package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportID;

    private int reporterID;
    private int reportedPostID;
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
    public void setReporterID(int newReporterID) {
        this.reporterID = newReporterID;
    }

    public void setReportedPostID(int newReportedPostID) {
        this.reportedPostID = newReportedPostID;
    }

    public void setReportedDate(Date newReportedDate) {
        this.reportedDate = newReportedDate;
    }
}
