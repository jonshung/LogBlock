package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESSMedia")
@IdClass(ESSMediaId.class)
public class ESSMedia {

    @Id
    @Column(name = "postID")
    private int postID;
    @Id
    @Column(name = "solutionID")
    private int solutionID;
    @Id
    @Column(name = "solutionMediaID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int solutionMediaID;

    @Column(name = "solutionMediaURI")
    private String solutionMediaURI;

    public int getPostID() {
        return postID;
    }

    public int getSolutionID() {
        return solutionID;
    }

    public int getSolutionMediaID() {
        return solutionMediaID;
    }

    public String getSolutionMediaURI() {
        return solutionMediaURI;
    }

    public void setSolutionMediaURI(String solutionMediaURI) {
        this.solutionMediaURI = solutionMediaURI;
    }

}
