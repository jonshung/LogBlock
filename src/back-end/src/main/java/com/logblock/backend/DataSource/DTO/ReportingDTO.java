package com.logblock.backend.DataSource.DTO;

import java.util.Date;

import com.logblock.backend.DataSource.Model.Reporting;

public class ReportingDTO {
    public int reportID;
    public int reporterID;
    public int reportedPostID;
    public Date reportedDate;

    public ReportingDTO(int reportID, Date reportedDate, int reportedPostID, int reporterID) {
        this.reportID = reportID;
        this.reportedDate = reportedDate;
        this.reportedPostID = reportedPostID;
        this.reporterID = reporterID;
    }

    public static Reporting toReporting(ReportingDTO dto) {
        Reporting p = new Reporting(dto.reporterID, dto.reportedPostID, dto.reportedDate);
        if(dto.reportID > 0) p.setReportID(dto.reportID);
        return p;
    }

    public static ReportingDTO toDTO(Reporting r) {
        return new ReportingDTO(r.getReportID(), r.getReportedDate(), r.getReportedPostID(), r.getReporterID());
    }
}
