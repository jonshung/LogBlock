"use client";

import Logo from "@/app/components/logo";
import { usePathname } from "next/navigation";

const abs_links = [
    { name: "Home", href: "/" },
    { name: "Explore", href: "/explore" },
]
const dyn_links = [
    { name: "Profile", href: "/profile" }
]

export default function Header() {
    const pathname = usePathname()
    let currentLink = dyn_links.find((link) => pathname.startsWith(link.href));
    if(currentLink === undefined) {
        currentLink = abs_links.find((link) => pathname == link.href);
    }

    return (
        <div className="fixed flex items-center text-black bg-white h-[90px] w-screen z-50">
            <div className="absolute flex-none ml-[40px]">
                <Logo />
            </div>

            {currentLink ?
                <div className="flex justify-center w-full">
                    <h1 className="text-2xl font-bold">
                        {currentLink.name}
                    </h1>
                </div> : <></>
            }
        </div>
    );
}
