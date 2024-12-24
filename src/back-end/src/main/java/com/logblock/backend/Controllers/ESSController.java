package com.logblock.backend.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.EssDTO;
import com.logblock.backend.DataSource.Model.ExpertSuggestedSolution;
import com.logblock.backend.PostService.ExpertSuggestedSolutionService;

@RestController
@RequestMapping("/ess")
public class ESSController {

    @Autowired
    private ExpertSuggestedSolutionService essService;

    /**
     * Update an existing Expert Suggested Solution.
     *
     * @param postID  ID of the post
     * @param ESSID   ID of the Expert Suggested Solution
     * @param ESSInfo Updated Expert Suggested Solution information
     * @return Response with the status of the update
     */
    @PutMapping("/{postID}/{ESSID}")
    public ResponseEntity<?> updateESS(@PathVariable int postID, @PathVariable int ESSID,
            @RequestBody ExpertSuggestedSolution ESSInfo) {
        int result = essService.updateESS(postID, ESSID, ESSInfo);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Create a new Expert Suggested Solution.
     *
     * @param ESSInfo The Expert Suggested Solution to create
     * @return Response with the newly created Expert Suggested Solution ID
     */
    @PostMapping
    public ResponseEntity<?> createESS(@RequestBody EssDTO ESSInfo) {
        int result = essService.createESS(ESSInfo);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Delete an Expert Suggested Solution.
     *
     * @param postID ID of the post
     * @param ESSID  ID of the Expert Suggested Solution to delete
     * @return Response with the status of the deletion
     */
    @DeleteMapping("/{postID}/{ESSID}")
    public ResponseEntity<?> deleteESS(@PathVariable int postID, @PathVariable int ESSID) {
        int result = essService.deleteESS(postID, ESSID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Retrieve an Expert Suggested Solution.
     *
     * @param postID ID of the post
     * @param ESSID  ID of the Expert Suggested Solution
     * @return Response with the Expert Suggested Solution information
     */
    @GetMapping("/{postID}/{ESSID}")
    public ResponseEntity<EssDTO> retrieveESSInfo(@PathVariable int postID, @PathVariable int ESSID) {
        Optional<ExpertSuggestedSolution> essInfo = essService.getESS(postID, ESSID);
        return essInfo.isPresent() ? ResponseEntity.ok(EssDTO.toDTO(essInfo.get())) : ResponseEntity.noContent().build();
    }
}
