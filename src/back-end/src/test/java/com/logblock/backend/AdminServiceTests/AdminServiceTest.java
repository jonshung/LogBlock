package com.logblock.backend.AdminServiceTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logblock.backend.DataSource.Model.Reporting;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.DataSource.Repository.ReportRepository;
import com.logblock.backend.AuthenticationService.AccountService;
import com.logblock.backend.AdminService.AdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@SpringBootTest
public class AdminServiceTest {

    @Mock
    private PostRepository postRepository; // Mock PostRepository

    @Mock
    private ReportRepository reportRepository; // Mock ReportRepository

    @Mock
    private AccountService accountService; // Mock AccountService

    @InjectMocks
    private AdminService adminService; // Inject mocks into AdminService

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testRetrieveAllReports() {
        // Arrange: Create sample report data
        List<Reporting> reports = new ArrayList<>();
        reports.add(new Reporting(1, 101, new Date())); // Using constructor with reporterID, reportedPostID, and
                                                        // reportedDate
        reports.add(new Reporting(2, 102, new Date()));

        // Mock reportRepository to return the list of reports
        when(reportRepository.findAllReports()).thenReturn(reports);

        // Act: Call retrieveAllReports method
        List<Reporting> result = adminService.retrieveAllReports();

        // Assert: Verify the result
        assertEquals(2, result.size());
        assertEquals(101, result.get(0).getReportedPostID());
        assertEquals(102, result.get(1).getReportedPostID());

        // Verify that the findAllReports method was called once
        verify(reportRepository, times(1)).findAllReports();
    }

    @Test
    public void testRemovePost() {
        // Simulate the postRepository returning a successful deletion
        when(postRepository.removePost(101)).thenReturn(1);

        // Act: Call the removePost method
        int result = adminService.removePost(101);

        // Assert: Check the result
        assertEquals(1, result); // The result should be 1 (successful deletion)

        // Verify: Ensure the method was called
        verify(postRepository, times(1)).removePost(101);
    }

    @Test
    public void testRemovePostNotFound() {
        // Arrange: Mock the case where the post does not exist
        when(postRepository.removePost(101)).thenReturn(0);

        // Act: Call removePost method
        int result = adminService.removePost(101);

        // Assert: Verify the result
        assertEquals(0, result); // Expected result is 0 (post not found)

        // Verify that removePost method was called once
        verify(postRepository, times(1)).removePost(101);
    }

    @Test
    public void testCloseAccount() {
        // Arrange: Mock AccountService to return success when deleting account
        when(accountService.deleteAccount(1)).thenReturn(1);

        // Act: Call closeAccount method
        int result = adminService.closeAccount(1);

        // Assert: Verify the result
        assertEquals(1, result); // Expected result is 1 (account successfully closed)

        // Verify that deleteAccount method was called once
        verify(accountService, times(1)).deleteAccount(1);
    }

    @Test
    public void testCloseAccountNotFound() {
        // Arrange: Mock AccountService to return failure when deleting account
        when(accountService.deleteAccount(1)).thenReturn(0);

        // Act: Call closeAccount method
        int result = adminService.closeAccount(1);

        // Assert: Verify the result
        assertEquals(0, result); // Expected result is 0 (account not found)

        // Verify that deleteAccount method was called once
        verify(accountService, times(1)).deleteAccount(1);
    }
}
