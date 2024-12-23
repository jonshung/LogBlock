export default function SearchCard() {
    const name = "Henry Clauss";
    const connectors = 53;

    return (
        <div className="flex items-center w-[465px] h-[70px]">
            <img
                src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png"
                width={50}
                height={50}
                className="ml-[10px]"
            />
            <div className="flex flex-col w-[275px] ml-[15px] text-black">
                <p className="text-xl font-medium">{name}</p>
                <p className="text-lg">{connectors} connectors</p>
            </div>
            <button className="w-[90px] h-[30px] text-black border border-[#b0b0b0] rounded-[15px]">
                Connect
            </button>
        </div>
    );
}