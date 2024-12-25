import Link from "next/link";

export default function ConnectorsCard() {
    const name = "Henry Clauss";

    return (
        <Link
            href="/"
            className="flex items-center w-full h-[65px] text-black tetx-lg font-medium"
        >
            <img
                src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png"
                width={50}
                height={50}
            />
            <p className="w-full ml-[10px]">{name}</p>
        </Link>
    );
}