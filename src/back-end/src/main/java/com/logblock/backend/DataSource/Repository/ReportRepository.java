package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    /**
     * Retrieve all reports.
     *
     * @return List of all reports
     */
    @Query("SELECT r FROM Report r")
    List<Report> retrieveAllReports();

    /**
     * Retrieve a report by its ID.
     *
     * @param reportID ID of the report
     * @return Report object if found, otherwise null
     */

    default Report retrieveReport(int reportID) {
        Optional<Report> reportOpt = findById(reportID);
        return reportOpt.orElse(null); // Return the report if found, otherwise return null
    }

    /**
     * Retrieve a report by its ID.
     *
     * @param reportID ID of the report
     * @return Report object if found, otherwise null
     */
    @Override
    Optional<Report> findById(Integer reportID);

    /**
     * Add a new report.
     *
     * @param newReport The report to be added
     * @return ID of the newly added report
     */
    @Transactional
    default int addReport(Report newReport) {
        // JpaRepository's save method handles both adding and updating
        Report savedReport = save(newReport);
        return savedReport.getReportID();
    }

    /**
     * Update an existing report.
     *
     * @param reportID         ID of the report to update
     * @param newReportContent Updated report content
     * @return ID of the updated report
     */
    @Transactional
    default int updateReport(int reportID, Report newReportContent) {
        Optional<Report> existingReportOpt = findById(reportID);
        if (existingReportOpt.isPresent()) {
            Report existingReport = existingReportOpt.get();
            existingReport.setReporterID(newReportContent.getReporterID());
            existingReport.setReportedPostID(newReportContent.getReportedPostID());
            existingReport.setReportedDate(newReportContent.getReportedDate());
            save(existingReport); // Save the updated report
            return existingReport.getReportID();
        }
        return -1; // Return -1 if report does not exist
    }

    /**
     * Remove a report by its ID.
     *
     * @param reportID ID of the report to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removeReport(int reportID) {
        Optional<Report> reportOpt = findById(reportID);
        if (reportOpt.isPresent()) {
            delete(reportOpt.get()); // Delete the report from the database
            return 1;
        }
        return 0; // Return 0 if report does not exist
    }
}
