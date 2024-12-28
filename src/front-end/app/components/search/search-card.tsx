export default function SearchCard({ name, connectors, image }: { name: string, connectors: number, image: string }) {
    return (
        <div className="flex items-center w-full h-[65px] px-[10px] text-black tetx-lg font-medium">
            <img
                src={image}
                width={50}
                height={50}
            />
            <div className="flex flex-col w-full mx-[15px] text-black">
                <p>{name}</p>
                <p>{connectors} connectors</p>
            </div>
            <button className="w-[140px] h-[30px] text-black border border-[#b0b0b0] rounded-[15px]">
                Connect
            </button>
        </div>
    );
}