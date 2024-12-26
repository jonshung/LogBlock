import { Metadata } from "next";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import ConnectorsBox from "@/app/components/connectors/connectors-box";

export const metadata: Metadata = {
  title: "LogBlock",
  description: "Social Media for Developers",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <div>
      <Header />
      <div className="flex grid grid-cols-3 h-screen">
        <SideNav />
        <div>{children}</div>
        <div className="relative flex justify-center">
          <ConnectorsBox />
        </div>
      </div>
    </div>
  );
}
