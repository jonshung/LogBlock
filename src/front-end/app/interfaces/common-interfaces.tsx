interface PostMediaData {
    mediaID: number;
    postID: number;
    mediaURI: string;
}

interface TagData {
    userID: number;
    username: string;
}

interface PostData {
    postID: number;
    originalAuthor: number;
    caption: string;
    creationDate: string;
    lastModifiedDate: string;
}

interface PostDataExtended {
    postData: PostData;
    authorImage: string;
    authorDisplayName: string;
}

interface ProfileData {
    userID: number;
    userEmail: string;
    displayName: string;
    bioDesc: string;
    profileImg: string;
};

interface ConnectionData {
    connectorID: number;
    connectedID: number;
    connectionDate: string;
}

interface CommentingData {
    commentID: number;
    postID: number;
    authorID: number;
    caption: string;
    creationDate: string;
}

interface CommentingDataExtended {
    commentData: CommentingData
    authorImage: string;
    authorDisplayName: string;
}

interface PostDataBundle {
    postData: PostDataExtended;
    postMedia: PostMediaData[];
    comments: CommentingDataExtended[];
};

export type {
    PostMediaData,
    TagData,
    PostData,
    CommentingData,
    ProfileData,
    ConnectionData,
    CommentingDataExtended,
    PostDataExtended,
    PostDataBundle
}