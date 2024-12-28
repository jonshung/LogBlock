'use server'

import { PostData } from "@/app/interfaces/common-interfaces";
import { bundlePostAndComment } from "@/app/utils/PostAPI";
import { getCurrentProfileData } from "@/app/utils/ProfileAPI";
import { fetchAuthorized } from "../AuthorizationCode";
import HttpStatusCode from "../HTTPStatusCode";
import { profileFeedGenerate } from "./profile-feed-generator";

export const newsFeedGenerate = async ( excluding: number[], lim: number ) => {
    const currentUser = await getCurrentProfileData();
    if(currentUser == null) {
        return []
    }
    let data: PostData[] = [];
    try {
        let resp = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/feeds/news/${currentUser.userID}`, 
            JSON.stringify({ 'exclude': excluding, 'limit': Math.max(1, lim-1) }));
        if(resp != null && resp.status == HttpStatusCode.OK_200) {
            data = await resp.json();
        }
    } catch(e) {
        // nothing
    }
    const profilesMap = await profileFeedGenerate(excluding, 1);
    const postMap = await bundlePostAndComment(data);
    return postMap.concat(profilesMap);
}