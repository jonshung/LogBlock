package com.logblock.backend.ProfileServiceTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.AuthenticationService.AccountService;
import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Repository.ProfileRepository;
import com.logblock.backend.ProfileService.ProfileService;

import jakarta.transaction.Transactional;

/**
 * Template for service and data access testing for LogBlock.
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
public class ProfileInsertionTest {
    // Start from here.
    @Autowired
    private ProfileService profileService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileRepository userRepo;

    @Test
    void testScenarioInsertionStandard() {
        // Insertion
        List<List<String>> test_inputs = List.of(
                List.of(
                        "johndoe@mail.com", // User Email
                        "John Doe", // Display Name
                        "This is John Doe", // Biography description
                        "https://google.com", // Profile image
                        "2" // Privilege level
                ),
                List.of(
                        "willsmith@mail.com", // User Email
                        "Will Smith", // Display Name
                        "Will Smith was here", // Biography description
                        "https://example.com/", // Profile image
                        "1" // Privilege level
                ));

        List<Integer> ids = new ArrayList<>(test_inputs.size());
        for (List<String> tcase : test_inputs) {
            int id = this.accountService.createAccount(tcase.get(0));
            ids.add(id);
            this.profileService.updateDisplayName(id, tcase.get(1));
            this.profileService.updateBiography(id, tcase.get(2));
            this.profileService.updateProfileImg(id, tcase.get(3));
            this.profileService.updatePrivilegeLevel(id, Integer.parseInt(tcase.get(4)));
        }

        assertEquals(this.userRepo.findAllUsers().size(), test_inputs.size());
        // Checking
        for (int i = 0; i < test_inputs.size(); i++) {
            List<String> tcase = test_inputs.get(i);
            Profile u = this.profileService.getProfileByEmail(tcase.get(0));
            assertNotNull(u);
            assertEquals(ids.get(i), Integer.valueOf(u.getUserID()));
            assertEquals(tcase.get(0), u.getUserEmail());
            assertEquals(tcase.get(1), u.getDisplayName());
            assertEquals(tcase.get(2), u.getBioDesc());
            assertEquals(tcase.get(3), u.getProfileImg());
            assertEquals(Integer.parseInt(tcase.get(4)), u.getPrivLevel());
        }
    }
}
