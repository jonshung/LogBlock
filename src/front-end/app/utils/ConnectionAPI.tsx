import { ConnectionData } from "../interfaces/common-interfaces";
import { fetchAuthorized } from "./AuthorizationCode";
import HttpStatusCode from "./HTTPStatusCode";

export const getConnectionsFrom = async (userID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/connections/from/${userID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: ConnectionData[] = await data.json();
    return parsed;
}

export const getConnectionsTo = async (userID: string) => {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/connections/to/${userID}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        return null;
    }
    const parsed: ConnectionData[] = await data.json();
    return parsed;
}