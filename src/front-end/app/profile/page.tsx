import { notFound, redirect } from "next/navigation";
import { fetchAuthorized, getSession, session_token_name } from "../utils/AuthorizationCode";
import HttpStatusCode from "../utils/HTTPStatusCode";

export default async function Page() {
    const data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME}:${process.env.BACKEND_PORT}/profiles/`);
    if(data == null || data.status == HttpStatusCode.NOT_FOUND_404) {
        return notFound();
    }
    const id: number = await data.json();
    redirect(`/profile/${id}`);
}