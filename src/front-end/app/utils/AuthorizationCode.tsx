'use server'

import { cookies } from "next/headers";

const session_token_name = process.env.SESSION_TOKEN;
const getSession = async () => {
    if(session_token_name === undefined) return '';
    return (await cookies()).get(session_token_name)?.value ?? '';
}
const fetchAuthorized = async (url: string, body: string | null = null) => {
    const usrToken = await getSession();
    if(usrToken == "") {
        return null;
    }
    try {
        const data = fetch(url, {
            headers: {
                Cookie: `${session_token_name}=${usrToken};`,
                "Content-Type": "application/json",
            },
            method: ((body == null) ? "GET" : "POST"),
            body: body
        });
        return data;
    } catch(error) {
        return null;
    }
}

export {
    getSession,
    fetchAuthorized
}