import { Metadata } from "next";
import { GoogleOAuthProvider } from "@react-oauth/google";

import { inter } from "@/app/components/fonts";
import "@/app/globals.css";

export const metadata: Metadata = {
    title: "LogBlock",
    description: "Social Media for Developers",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
    return (
        <html lang="en">
            <body className={`${inter.className} antialiased`}>
                <GoogleOAuthProvider clientId={process.env.GOOGLE_CLIENT_ID || ""}>
                    {children}
                </GoogleOAuthProvider>
            </body>
        </html>
    );
}
