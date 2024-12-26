import { redirect } from "next/navigation";
import { fetchAuthorized } from "../../utils/AuthorizationCode";
import HttpStatusCode from "../../utils/HTTPStatusCode";

export default async function Page() {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME}:${process.env.BACKEND_PORT}/profiles/`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status == HttpStatusCode.NOT_FOUND_404) {
        redirect('/404');
    }
    const id: number = await data.json();
    redirect(`/profile/${id}`);
}