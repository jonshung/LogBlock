package com.logblock.backend.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Report;
import com.logblock.backend.DataSource.Repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    /**
     * Retrieve report information.
     *
     * @param reportID ID of the report
     * @return Report object if found, otherwise null
     */
    public Report retrieveReportInfo(int reportID) {
        return reportRepository.retrieveReport(reportID);
    }

    /**
     * Create a new report.
     *
     * @param newReport The report information to create
     * @return ID of the created report
     */
    public int createReport(Report newReport) {
        return reportRepository.addReport(newReport);
    }

    /**
     * Delete a report.
     *
     * @param reportID ID of the report to delete
     * @return 1 if successful, otherwise 0
     */
    public int deleteReport(int reportID) {
        return reportRepository.removeReport(reportID);
    }
}
