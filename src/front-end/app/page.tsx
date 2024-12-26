"use client";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";

import { useRouter } from "next/navigation";

export default function Page() {
    const router = useRouter();
    return (
        <main className="relative w-full h-full">
            <Header />
            <SideNav />
            <button
                onClick={() => router.push("/signIn")}
                className="absolute w-[100px] h-[50px] left-[500px] top-[100px] bg-blue-500 text-white rounded-md"
            >
                Sign in
            </button>
        </main>
    );
}