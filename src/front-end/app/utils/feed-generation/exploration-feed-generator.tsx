'use server'

import { PostData } from "@/app/interfaces/common-interfaces";
import { fetchAuthorized } from "../AuthorizationCode";
import HttpStatusCode from "../HTTPStatusCode";
import { bundlePostAndComment } from "../PostAPI";

export const explorationFeedGenerate = async ( excluding: number[], lim: number ) => {
    let data: PostData[] = [];
    try {
        let resp = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/feeds/exploration/`, 
            JSON.stringify({ 'exclude': excluding, 'limit': lim }));
        if(resp != null && resp.status == HttpStatusCode.OK_200) {
            data = await resp.json();
        }
    } catch(e) {
        // nothing
    }
    const postMap = await bundlePostAndComment(data);
    return postMap;
}