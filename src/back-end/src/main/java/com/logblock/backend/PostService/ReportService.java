package com.logblock.backend.PostService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Reporting;
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
    public Reporting retrieveReportInfo(int reportID) {
        Optional<Reporting> result = reportRepository.findById(reportID);
        if(!result.isPresent()) {
            return null;
        }
        return result.get();
    }

    /**
     * Create a new report.
     *
     * @param newReport The report information to create
     * @return ID of the created report
     */
    public int createReport(Reporting newReport) {
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
