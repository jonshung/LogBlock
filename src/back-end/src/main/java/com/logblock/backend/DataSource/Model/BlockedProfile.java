package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="BlockedProfile")
@IdClass(BlockedProfileId.class)
public class BlockedProfile {

    @Id
    @Column(name = "userID")
    private int userID;

    @Id
    @Column(name = "targetUserID")
    private int targetUserID;

    @Column(name = "blockedDate")
    private Date blockedDate;

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
