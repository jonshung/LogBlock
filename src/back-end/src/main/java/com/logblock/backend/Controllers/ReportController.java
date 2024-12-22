package com.logblock.backend.Controllers;

import com.logblock.backend.PostService.ReportService;
import com.logblock.backend.DataSource.Model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Retrieve a report's information.
     *
     * @param reportID ID of the report
     * @return Response with the report's information
     */
    @GetMapping("/{reportID}")
    public ResponseEntity<Report> retrieveReportInfo(@PathVariable int reportID) {
        Report report = reportService.retrieveReportInfo(reportID);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    /**
     * Create a new report.
     *
     * @param newReport The report information to create
     * @return Response with the newly created report ID
     */
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody Report newReport) {
        int result = reportService.createReport(newReport);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete a report.
     *
     * @param reportID ID of the report to delete
     * @return Response with the status of the deletion
     */
    @DeleteMapping("/{reportID}")
    public ResponseEntity<?> deleteReport(@PathVariable int reportID) {
        int result = reportService.deleteReport(reportID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
