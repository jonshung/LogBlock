"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

const links = [
    { name: "Home", href: "/",
      icon_main: "https://img.icons8.com/?size=50&id=1iF9PyJ2Thzo&format=png&color=000000",
      icon_sub: "https://img.icons8.com/?size=50&id=1iF9PyJ2Thzo&format=png&color=C4C4C4C4" },
    { name: "Explore", href: "/explore",
      icon_main: "https://img.icons8.com/?size=50&id=XU3XKgdpT0qG&format=png&color=000000",
      icon_sub: "https://img.icons8.com/?size=50&id=XU3XKgdpT0qG&format=png&color=C4C4C4C4" },
    { name: "Report", href: "/report",
      icon_main: "https://img.icons8.com/?size=50&id=RrbNhNCy3YDh&format=png&color=000000",
      icon_sub: "https://img.icons8.com/?size=50&id=RrbNhNCy3YDh&format=png&color=C4C4C4C4" },
    { name: "Profile", href: "/profile",
      icon_main: "https://img.icons8.com/?size=50&id=83190&format=png&color=000000",
      icon_sub: "https://img.icons8.com/?size=50&id=83190&format=png&color=C4C4C4C4" },
];

export default function NavLinks() {
    const pathname = usePathname();
    let current = "";

    links.map((link) => {
        if (pathname.startsWith(link.href) && link.href.length > current.length) {
            current = link.href;
        }
    });

    const isAdmin = true;

    return (
        <>
            {links.map((link) => {
                if (!isAdmin && link.name === "Report") {
                    return null;
                }

                return (
                    <Link
                        key={link.name}
                        href={link.href}
                    >
                        {link.href === current ? <img src={link.icon_main} /> : <img src={link.icon_sub} />}
                    </Link>
                )
            })}
        </>
    );
}