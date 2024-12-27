'use server'

import { ProfileData } from "../interfaces/common-interfaces";
import { fetchAuthorized } from "./AuthorizationCode";
import HttpStatusCode from "./HTTPStatusCode";

export const getProfileData = async (userID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/profiles/${userID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: ProfileData = await data.json();
    return parsed;
};

export const getCurrentProfileData = async () => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/profiles/`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const id: number = await data.json();
    return getProfileData(id.toString());
}