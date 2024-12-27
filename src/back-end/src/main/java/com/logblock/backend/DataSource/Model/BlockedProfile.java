package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="blockedprofile")
@IdClass(BlockedProfileId.class)
public class BlockedProfile {

    @Id
    @Column(name = "userid")
    private int userID;

    @Id
    @Column(name = "targetuserid")
    private int targetUserID;

    @Column(name = "blockeddate")
    private Date blockedDate;

    /**
     * 
     * @param blockedDate
     * @param targetUserID
     * @param userID
     */
    public BlockedProfile(Date blockedDate, int targetUserID, int userID) {
        this.blockedDate = blockedDate;
        this.targetUserID = targetUserID;
        this.userID = userID;
    }

    public BlockedProfile() {
        
    }

    public int getUserID() {
        return userID;
    }

    public int getTargetUserID() {
        return targetUserID;
    }

    public Date getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(Date blockedDate) {
        this.blockedDate = blockedDate;
    }
}
