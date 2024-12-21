export default function ConnectorsCard({ name }: { name: string }) {
    return (
        <div className="flex items-center w-[465px] h-[65px] text-black tetx-lg font-medium">
            <img
                src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png"
                width={50}
                height={50}
            />
            <span className="w-[10px]" />
            <p>{name}</p>
        </div>
    );
}