package com.logblock.backend.PostServiceTests;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.DTO.ReportingDTO;
import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Model.Reporting;
import com.logblock.backend.DataSource.Repository.ReportRepository;
import com.logblock.backend.DataSource.Repository.ProfileRepository;

import com.logblock.backend.PostService.ReportService;

import jakarta.transaction.Transactional;

/**
 * Template for service and data access testing for ReportService.
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepo;

    @Autowired
    private ProfileRepository profileRepo;

    @Test
    void testCreateReport() {
        // Create a user (similarly as in the PostTest)
        Profile u = new Profile("johndoe@gmail.com", "John Doe", "This is John Doe.", "https://example.com", 0);
        profileRepo.addUser(u);

        // Create a ReportingDTO object with four parameters
        ReportingDTO reportDTO = new ReportingDTO(0, new Date(), 123, u.getUserID()); // reportID will be auto-generated

        // Create report using the service
        int reportID = reportService.createReport(reportDTO);

        // Assert that a report ID is returned (it should be greater than 0)
        assertNotNull(reportID);

        // Retrieve the created report from the repository
        Reporting report = reportRepo.findById(reportID).orElse(null);

        // Assert the report is not null
        assertNotNull(report);
        assertEquals(u.getUserID(), report.getReporterID());
        assertEquals(123, report.getReportedPostID());
    }

    @Test
    void testRetrieveReportInfo() {
        // Create a user (similarly as in the PostTest)
        Profile u = new Profile("janedoe@gmail.com", "Jane Doe", "This is Jane Doe.", "https://example.com", 0);
        profileRepo.addUser(u);

        // Create a new report
        ReportingDTO reportDTO = new ReportingDTO(0, new Date(), 456, u.getUserID()); // reportID will be auto-generated
        int reportID = reportService.createReport(reportDTO);

        // Retrieve the report by ID using the service
        Reporting retrievedReport = reportService.retrieveReportInfo(reportID);

        // Assert that the report is not null and matches the expected values
        assertNotNull(retrievedReport);
        assertEquals(reportID, retrievedReport.getReportID());
        assertEquals(u.getUserID(), retrievedReport.getReporterID());
        assertEquals(456, retrievedReport.getReportedPostID());
    }

    @Test
    void testDeleteReport() {
        // Create a user
        Profile u = new Profile("alexsmith@gmail.com", "Alex Smith", "This is Alex Smith.", "https://example.com", 0);
        profileRepo.addUser(u);

        // Create a new report
        ReportingDTO reportDTO = new ReportingDTO(0, new Date(), 789, u.getUserID()); // reportID will be auto-generated
        int reportID = reportService.createReport(reportDTO);

        // Delete the created report
        int deleteResult = reportService.deleteReport(reportID);

        // Assert that the deletion was successful
        assertEquals(1, deleteResult);

        // Try to retrieve the report again (should return null since it was deleted)
        Reporting deletedReport = reportRepo.findById(reportID).orElse(null);
        assertNull(deletedReport);
    }

    @Test
    void testDeleteNonExistingReport() {
        // Try to delete a report with an invalid ID
        int deleteResult = reportService.deleteReport(99999); // Non-existing report ID

        // Assert that the deletion failed (should return 0)
        assertEquals(0, deleteResult);
    }
}
