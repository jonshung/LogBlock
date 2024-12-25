import { Metadata } from "next";
import { inter } from "@/app/components/fonts";
import "@/app/globals.css";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import SearchBox from "@/app/components/search/search-box";

export const metadata: Metadata = {
  title: "LogBlock",
  description: "Social Media for Developers",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
      <>
        <div>{children}</div>
        <div className="relative flex justify-center">
            <SearchBox />
        </div>
      </>
  );
}
