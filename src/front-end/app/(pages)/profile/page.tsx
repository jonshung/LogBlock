import { notFound, redirect } from "next/navigation";
import { fetchAuthorized } from "../../utils/AuthorizationCode";
import HttpStatusCode from "../../utils/HTTPStatusCode";

export default async function Page() {
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}/profiles/`);
    } catch(e) {
        // nothing
    }
    if(data == null || !data) {
        return <p>Loading</p>
    }
    if(data.status != HttpStatusCode.OK_200) {
        return notFound();
    }
    const id: number = await data.json();
    redirect(`/profile/${id}`);
}