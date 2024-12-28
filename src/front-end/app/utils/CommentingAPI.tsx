'use server'

import { CommentingData, PostData } from "../interfaces/common-interfaces";
import { fetchAuthorized } from "./AuthorizationCode";
import HttpStatusCode from "./HTTPStatusCode";

export const getCommentingData = async (postID: string, commentID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/comments/${postID}/${commentID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: CommentingData = await data.json();
    return parsed;
}

export const getCommentingsDataOf = async (postID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/comments/${postID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return [];
    }
    const parsed: CommentingData[] = await data.json();
    return parsed;
}

export const addCommentingTo = async (commentData: CommentingData) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/comments/add/`, 
            JSON.stringify(commentData)
        );
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return [];
    }
    const parsed: CommentingData[] = await data.json();
    return parsed;
}