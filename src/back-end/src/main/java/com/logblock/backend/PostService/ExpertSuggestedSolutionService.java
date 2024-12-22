package com.logblock.backend.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.ESSId;
import com.logblock.backend.DataSource.Model.ExpertSuggestedSolution;
import com.logblock.backend.DataSource.Repository.ExpertSuggestedSolutionRepository;

@Service
public class ExpertSuggestedSolutionService {

    @Autowired
    private ExpertSuggestedSolutionRepository ESSRepository;

    /**
     * Update an existing Expert Suggested Solution.
     *
     * @param postID  ID of the post
     * @param ESSID   ID of the Expert Suggested Solution to update
     * @param ESSInfo Updated Expert Suggested Solution information
     * @return ID of the updated Expert Suggested Solution
     */
    public int updateESS(int postID, int ESSID, ExpertSuggestedSolution ESSInfo) {
        return ESSRepository.updateESS(new ESSId(postID, ESSID), ESSInfo); // Call the update method in repository
    }

    /**
     * Create a new Expert Suggested Solution.
     *
     * @param ESSInfo The Expert Suggested Solution to create
     * @return ID of the created Expert Suggested Solution
     */
    public int createESS(ExpertSuggestedSolution ESSInfo) {
        return ESSRepository.addESS(ESSInfo); // Call the add method in repository
    }

    /**
     * Delete an Expert Suggested Solution.
     *
     * @param postID ID of the post
     * @param ESSID  ID of the Expert Suggested Solution to delete
     * @return 1 if successful, otherwise 0
     */
    public int deleteESS(int postID, int ESSID) {
        return ESSRepository.removeESS(new ESSId(postID, ESSID)); // Call the remove method in repository
    }

    /**
     * Retrieve Expert Suggested Solution information.
     *
     * @param postID ID of the post
     * @param ESSID  ID of the Expert Suggested Solution
     * @return ExpertSuggestedSolution object if found, otherwise null
     */
    public ExpertSuggestedSolution retrieveESSInfo(int postID, int ESSID) {
        return ESSRepository.findById(new ESSId(postID, ESSID)).get(); // Call the retrieve method in repository
    }
}
