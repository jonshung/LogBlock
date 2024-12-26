"use client";

import { usePathname } from "next/navigation";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import ConnectorsBox from "@/app/components/connectors/connectors-box";
import SearchBox from "@/app/components/search/search-box";

export default function Layout({ children }: { children: React.ReactNode }) {
    const pathname = usePathname();

    return (
        <div>
            <Header />
            <div className="grid grid-cols-3 h-screen">
                <SideNav />
                <div>
                    {children}
                </div>
                {pathname === "/" ? (
                    <ConnectorsBox />
                ) : pathname === "/explore" ? (
                    <SearchBox />
                ) : (
                    <div />
                )}
            </div>
        </div>
    );
}