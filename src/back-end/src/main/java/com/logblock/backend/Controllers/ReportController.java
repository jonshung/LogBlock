package com.logblock.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.ReportingDTO;
import com.logblock.backend.DataSource.Model.Reporting;
import com.logblock.backend.PostService.ReportService;

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
    public ResponseEntity<ReportingDTO> retrieveReportInfo(@PathVariable int reportID) {
        Reporting report = reportService.retrieveReportInfo(reportID);
        return report != null ? ResponseEntity.ok(ReportingDTO.toDTO(report)) : ResponseEntity.noContent().build();
    }

    /**
     * Create a new report.
     *
     * @param newReport The report information to create
     * @return Response with the newly created report ID
     */
    @PostMapping
    public ResponseEntity<Integer> createReport(@RequestBody ReportingDTO newReport) {
        int result = reportService.createReport(newReport);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.internalServerError().build();
    }

    /**
     * Delete a report.
     *
     * @param reportID ID of the report to delete
     * @return Response with the status of the deletion
     */
    @DeleteMapping("/{reportID}")
    public ResponseEntity<Integer> deleteReport(@PathVariable int reportID) {
        int result = reportService.deleteReport(reportID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }
}
