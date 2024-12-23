import Link from "next/link";
import { armageda } from "@/app/ui/fonts";

export default function Logo() {
    return (
        <Link
            href="/"
            className="flex items-center bg-black rounded-[10px]"
        >
            <p className={`${armageda.className} text-4xl text-white p-[7px]`}>LogBlock</p>
        </Link>
    );
}