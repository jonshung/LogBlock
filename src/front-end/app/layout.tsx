import { Metadata } from "next";
import { inter } from "@/app/components/fonts";
import "@/app/globals.css";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";

export const metadata: Metadata = {
  title: "LogBlock",
  description: "Social Media for Developers",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={`${inter.className} antialiased`}>
        {<div>
          <Header />
          <div className="flex grid grid-cols-3 h-screen">
            <SideNav />
            {children}
          </div>
        </div>}
      </body>
    </html>

  );
}
