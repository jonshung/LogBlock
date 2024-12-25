export const dynamic = 'force-dynamic'

interface ProfileData {
    userEmail: string;
    displayName: string;
    userID: number;
    bioDesc: string;
    profileImage: string;
    privLevel: number;
};

export default async function Page() {
    // const data = await fetch("http://back-end:8080/profiles/1");
    // const parsed: ProfileData = await data.json();
    // const name = parsed.displayName;
    // const connectors = 50;
    // const bio = parsed.bioDesc;
    const name = "chocoCaro";
    const connectors = 50;
    const bio = "My passion for Python is boundless";

    return (
        <main>
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