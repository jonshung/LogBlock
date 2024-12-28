import Link from "next/link";

export default function ConnectorsCard({ name, image }: { name: string; image: string }) {
    return (
        <Link
            href="/"
            className="flex items-center w-full h-[65px] text-black tetx-lg font-medium"
        >
            <img
                src={image}
                width={50}
                height={50}
            />
            <p className="w-full ml-[10px]">{name}</p>
        </Link>
    );
}