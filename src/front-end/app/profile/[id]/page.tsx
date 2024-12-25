import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import { ProfileData } from "../../interfaces/common-interfaces";
import { redirect } from 'next/navigation'
import HttpStatusCode from "@/app/utils/HTTPStatusCode";
import { fetchAuthorized } from "@/app/utils/AuthorizationCode";

export const dynamic = 'force-dynamic'

function ProfileComponent(name: string, bio: string, connectors: number) {
    return (
        <main>
            <Header />
            <SideNav />
            <div className="fixed w-[750px] h-[1000px] left-[558px] top-[90px]">
                <div className="relative flex flex-col items-center h-[190px] px-[25px] bg-[#f4f4f4] rounded-[15px]">
                    <div className="flex items-start h-[100px] my-[20px]">
                        <img
                            src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png"
                            width={100}
                            height={100}
                            className="mr-[30px]"
                        />
                        <div className="grid grid-rows-3 items-center w-[570px] h-[100px] text-black">
                            <p className="text-3xl font-medium">{name}</p>
                            <p>{connectors} connectors</p>
                            <p className="text-lg font-medium">{bio}</p>
                        </div>
                    </div>
                    <button className="relative w-[700px] h-[30px] text-black border border-black border-[0.5px] rounded-[15px]">
                        Edit profile
                    </button>
                </div>
            </div>
        </main>
    );
}

async function Page({
    params,
  }: {
    params: Promise<{ id: string }>
  }) {
    const id = (await params).id;
    let data = null;
    try {
        data = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME}:${process.env.BACKEND_PORT}/profiles/${id}`);
    } catch(e) {
        // nothing
    }
    if(data == null || data.status != HttpStatusCode.OK_200) {
        redirect('/404');
    }
    const parsed: ProfileData = await data.json();
    const name = parsed.displayName;
    const connectors = 50;
    const bio = parsed.bioDesc;
    return ProfileComponent(name, bio, connectors);
}

export default Page;