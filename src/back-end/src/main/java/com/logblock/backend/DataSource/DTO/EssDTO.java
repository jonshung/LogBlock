package com.logblock.backend.DataSource.DTO;

import java.util.Date;

import com.logblock.backend.DataSource.Model.ExpertSuggestedSolution;

public class EssDTO {
    
    public int solutionID;
    public int postID;
    public int solutionAuthor;
    public String solutionCaption;
    public Date solutionCreationDate;
    public Date solutionLastModifiedDate;

    public EssDTO(int solutionID, int postID, int solutionAuthor, String solutionCaption, Date solutionCreationDate,
            Date solutionLastModifiedDate) {
        this.solutionID = solutionID;
        this.postID = postID;
        this.solutionAuthor = solutionAuthor;
        this.solutionCaption = solutionCaption;
        this.solutionCreationDate = solutionCreationDate;
        this.solutionLastModifiedDate = solutionLastModifiedDate;
    }

    public static ExpertSuggestedSolution toESS(EssDTO dto) {
        ExpertSuggestedSolution e = new ExpertSuggestedSolution(dto.postID, dto.solutionAuthor, dto.solutionCaption, dto.solutionCreationDate, dto.solutionLastModifiedDate);
        if(dto.solutionID > 0) e.setSolutionID(dto.solutionID);
        return e;
    }

    public static EssDTO toDTO(ExpertSuggestedSolution e) {
        return new EssDTO(e.getSolID(), e.getPostID(), e.getSolAuthor(), e.getSolCaption(), e.getSolCreationDate(), e.getSolLastModifiedDate());
    }
}
