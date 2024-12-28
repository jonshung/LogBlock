'use server'

import { CommentingDataExtended, PostData, PostDataBundle, PostDataExtended, PostMediaData } from "../interfaces/common-interfaces";
import { fetchAuthorized } from "./AuthorizationCode";
import { getCommentingsDataOf } from "./CommentingAPI";
import HttpStatusCode from "./HTTPStatusCode";
import { getProfileData } from "./ProfileAPI";

export const getPostData = async (postID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/posts/${postID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: PostData = await data.json();
    return parsed;
}

export const getPostDataBy = async (userID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/posts/by/${userID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: PostData[] = await data.json();
    return parsed;
}

export const bundlePostAndComment = async(posts: PostData[]) => {
    let postMap: PostDataBundle[] = [];

    for(let i = 0; i < posts.length; i++) {
        const post = posts[i];
        const comments = await getCommentingsDataOf(post.postID.toString());
        let extension_map: CommentingDataExtended[] = [];

        let postMedias: PostMediaData[] | null = await getPostingMediaDataOf(post.postID.toString());
        if(!postMedias) postMedias = [];

        const post_author = await getProfileData(post.originalAuthor.toString());
        if(post_author == null) continue;

        for(let j = 0; j < comments.length; ++j) {
            const authorData = await getProfileData(comments[j].authorID.toString());
            if(authorData == null) continue;

            let extension: CommentingDataExtended = {
                commentData: comments[j],
                authorImage: authorData.profileImg,
                authorDisplayName: authorData.displayName
            };
            extension_map.push(extension);
        }

        const extension_post: PostDataExtended = {
            postData: post,
            authorDisplayName: post_author.displayName,
            authorImage: post_author.profileImg
        };
        postMap.push({ postData: extension_post, comments: extension_map, postMedia: postMedias });
    }
    return postMap;
}

export const createPost = async (postData: PostData) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/posts/create/`,
            JSON.stringify(postData)
        );
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return 0;
    }
    const id = await data.json();
    return id;
}

export const getPostingMediaDataOf = async (postID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/posts/media/${postID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: PostMediaData[] = await data.json();
    return parsed;
}

export const addPostingMediaData = async (postMediaData: PostMediaData) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/posts/media/add`,
            JSON.stringify(postMediaData)
        );
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return 0;
    }
    const id = await data.json();
    return id;
}