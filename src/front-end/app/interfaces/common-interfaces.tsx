interface Media {
    mediaID: number;
    mediaURI: string;
}

interface Tag {
    userID: number;
    username: string;
}

interface PostData {
    postID: number;
    originalAuthor: number;
    authorName: string;
    authorAvatar: string;
    postCaption: string;
    postCreation: string;
    postLastUpdate: string;
    media: Media[];
    upvoteCount: number;
    tags: Tag[];
    comments: Comment[];
}

interface ProfileData {
    userEmail: string;
    displayName: string;
    userID: number;
    bioDesc: string;
    profileImage: string;
    privLevel: number;
};

interface Comment {
    commentID: number;
    commentAuthor: number;
    commentCaption: string;
    commentCreation: string;
    authorName: string;
    authorAvatar: string;
}

export type {
    Media,
    Tag,
    PostData,
    Comment,
    ProfileData
}