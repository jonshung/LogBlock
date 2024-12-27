export default function SearchCard() {
    const name = "Henry Clauss";
    const connectors = 53;

    return (
        <div className="flex items-center w-full h-[65px] text-black tetx-lg font-medium">
            <img
                src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png"
                width={50}
                height={50}
                className="ml-[10px]"
            />
            <div className="flex flex-col w-full mx-[15px] text-black">
                <p className="w-full">{name}</p>
                <p className="">{connectors} connectors</p>
            </div>
            <button className="w-[130px] h-[30px] text-black border border-[#b0b0b0] rounded-[15px]">
                Connect
            </button>
        </div>
    );
}