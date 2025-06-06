package com.logblock.backend.DataSource.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logblock.backend.DataSource.Model.ESSId;
import com.logblock.backend.DataSource.Model.ExpertSuggestedSolution;

import jakarta.transaction.Transactional;

@Repository
public interface ExpertSuggestedSolutionRepository extends JpaRepository<ExpertSuggestedSolution, ESSId> {

    /**
     * Retrieve all Expert Suggested Solutions.
     *
     * @return List of all Expert Suggested Solutions
     */
    @Query("SELECT ess FROM ExpertSuggestedSolution ess")
    List<ExpertSuggestedSolution> retrieveAllESS();

    /**
     * Add a new Expert Suggested Solution.
     *
     * @param newESS Expert Suggested Solution to be added
     * @return ID of the newly added Expert Suggested Solution
     */
    @Transactional
    default int addESS(ExpertSuggestedSolution newESS) {
        // Save the new ESS and return its ID
        ExpertSuggestedSolution savedESS = save(newESS);
        return savedESS.getSolID();
    }

    /**
     * Update an existing Expert Suggested Solution.
     *
     * @param postID     ID of the post
     * @param ESSID      ID of the Expert Suggested Solution to update
     * @param ESSContent Updated Expert Suggested Solution content
     * @return ID of the updated Expert Suggested Solution
     */
    @Transactional
    default int updateESS(ESSId essID, ExpertSuggestedSolution ESSContent) {
        Optional<ExpertSuggestedSolution> existingESSOpt = findById(essID);
        if (existingESSOpt.isPresent()) {
            ExpertSuggestedSolution existingESS = existingESSOpt.get();
            existingESS.setSolCaption(ESSContent.getSolCaption());
            existingESS.setSolCreationDate(ESSContent.getSolCreationDate());
            existingESS.setSolLastModifiedDate(ESSContent.getSolLastModifiedDate());
            save(existingESS); // Save the updated ESS
            return existingESS.getSolID();
        }
        return -1; // Return -1 if ESS doesn't exist or doesn't belong to the given post
    }

    /**
     * Remove an Expert Suggested Solution by postID and solID.
     *
     * @param postID ID of the post
     * @param ESSID  ID of the Expert Suggested Solution to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removeESS(ESSId essID) {
        Optional<ExpertSuggestedSolution> ESSOpt = findById(essID);
        if (ESSOpt.isPresent()) {
            ExpertSuggestedSolution ESS = ESSOpt.get();
            delete(ESS); // Delete the ESS
            return 1;
        }
        return 0; // Return 0 if ESS doesn't exist or doesn't belong to the given post
    }
}
