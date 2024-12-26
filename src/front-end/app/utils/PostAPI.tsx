import { PostData } from "../interfaces/common-interfaces";
import { fetchAuthorized } from "./AuthorizationCode";
import HttpStatusCode from "./HTTPStatusCode";

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