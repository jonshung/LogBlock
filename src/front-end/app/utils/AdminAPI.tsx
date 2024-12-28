'use server'

import { fetchAuthorized } from "./AuthorizationCode";
import HttpStatusCode from "./HTTPStatusCode";

export const checkAdmin = async() => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/admin/`);
    } catch(e) {
        // nothing
    }
    return data != null && data.status == HttpStatusCode.OK_200;
}