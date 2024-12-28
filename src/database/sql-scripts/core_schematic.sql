-- LogBlock standard Database Image already initiated (default to logblock)
CREATE TABLE Profile (
    userID SERIAL PRIMARY KEY,
    userEmail VARCHAR(320) UNIQUE,
    displayName VARCHAR(50) DEFAULT '',
    biographyDesc VARCHAR(500) DEFAULT '',
    profileImage VARCHAR(2000) DEFAULT'',
    privLevel INTEGER NOT NULL
);

CREATE TABLE Posting (
    postID SERIAL PRIMARY KEY,
    originalAuthor INTEGER REFERENCES Profile(userID) NOT NULL,
    postCaption VARCHAR(500) DEFAULT '',
    postCreation DATE DEFAULT CURRENT_DATE,
    postLastUpdate DATE DEFAULT CURRENT_DATE
);

CREATE TABLE PostingMedia (
    postID NOT NULL INTEGER REFERENCES Posting(postID),
    mediaID INTEGER NOT NULL,
    mediaURI VARCHAR(2000) DEFAULT '',
    PRIMARY KEY (postID, mediaID)
);
CREATE SEQUENCE postingmedia_seq START 1 INCREMENT 1

CREATE TABLE PostingTagging (
    postID INTEGER REFERENCES Posting(postID),
    tagTargetID INTEGER REFERENCES Profile(userID),
    PRIMARY KEY (postID, tagTargetID)
);

CREATE TABLE PostingUpvote (
    postID INTEGER REFERENCES Posting(postID),
    upvoterID INTEGER REFERENCES Profile(userID),
    upvoteDate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (postID, upvoterID)
);

CREATE TABLE PinnedPosting (
    userID INTEGER REFERENCES Profile(userID),
    postID INTEGER REFERENCES Posting(postID),
    pinnedDate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (userID, postID)
);

CREATE TABLE BlockedProfile (
    userID INTEGER REFERENCES Profile(userID),
    targetUserID INTEGER REFERENCES Profile(userID),
    blockedDate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (userID, targetUserID)
);

CREATE TABLE Commenting (
    postID INTEGER REFERENCES Posting(postID),
    commentID INTEGER NOT NULL,
    commentAuthor INTEGER REFERENCES Profile(userID) NOT NULL,
    commentCaption VARCHAR(500) DEFAULT '',
    commentCreation DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (postID, commentID)
);
CREATE SEQUENCE commenting_seq START 1 INCREMENT 1

CREATE TABLE Connection (
    connector INTEGER REFERENCES Profile(userID),
    connectedTo INTEGER REFERENCES Profile(userID),
    connectionDate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (connector, connectedTo)
);

CREATE TABLE ESS (
    postID INTEGER REFERENCES Posting(postID),
    solutionID SERIAL,
    solutionAuthor INTEGER REFERENCES Profile(userID) NOT NULL,
    solutionCaption VARCHAR(500) DEFAULT '',
    solutionCreation DATE DEFAULT CURRENT_DATE,
    solutionLastUpdate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (postID, solutionID)
);

CREATE TABLE ESSMedia (
    postID INTEGER REFERENCES Posting(postID),
    solutionID INTEGER,
    solutionMediaID SERIAL,
    solutionMediaURI VARCHAR(2000) DEFAULT '',
    PRIMARY KEY (postID, solutionID, solutionMediaID),
    FOREIGN KEY (postID, solutionID) REFERENCES ESS(postID, solutionID)
);

CREATE TABLE Reporting (
    reportID SERIAL PRIMARY KEY,
    reporter INTEGER REFERENCES Profile(userID) NOT NULL,
    reportPostID INTEGER REFERENCES Posting(postID) NOT NULL,
    reportDate DATE DEFAULT CURRENT_DATE 
);