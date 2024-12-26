"use client";

import { usePathname } from "next/navigation";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";

export default function Layout({ children }: { children: React.ReactNode }) {
    return (
        <div>
            <Header />
            <div className="grid grid-cols-3 h-screen">
                <SideNav />
                {children}
            </div>
        </div>
    );
}