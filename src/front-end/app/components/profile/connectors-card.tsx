export default function ConnectorsCard({ name, connectors, image }: { name: string, connectors: number, image: string }) {
    return (
        <div className="flex items-center justify-start gap-[15px] h-[75px] px-[10px]">
            <img
                src={image}
                width={50}
                height={50}
            />
            <div className="flex flex-col justify-center w-full text-black">
                <p className="text-xl font-medium">{name}</p>
                <p>{connectors} connectors</p>
            </div>
            <button className="w-[180px] h-[30px] text-black border border-[#b0b0b0] rounded-[15px]">
                Connected
            </button>
        </div>
    );
}