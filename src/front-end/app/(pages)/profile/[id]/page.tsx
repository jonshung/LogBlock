import { notFound } from 'next/navigation'
import { getProfileData } from "@/app/utils/ProfileAPI";
import { getConnectionsTo } from "@/app/utils/ConnectionAPI";

export const dynamic = 'force-dynamic'

function ProfileComponent(name: string, bio: string, connectors: number) {
    return (
        <>
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
        </>
    );
}

async function Page({
    params,
  }: {
    params: Promise<{ id: number }>
  }) {
    const id = (await params).id;
    const profile_data = await getProfileData(id.toString());
    if(profile_data == null) {
        return notFound();
    }

    const name = profile_data.displayName;
    const bio = profile_data.bioDesc;

    let connectors = await getConnectionsTo(id.toString());
    if(connectors == null) {
        connectors = [];
    }
    return ProfileComponent(name, bio, connectors.length);
}

export default Page;