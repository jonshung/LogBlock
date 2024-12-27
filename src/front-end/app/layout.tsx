import { inter } from "@/app/components/fonts";
import "@/app/globals.css";

export default function RootLayout({ children }: { children: React.ReactNode }) {
    return (
        <html lang="en">
            <head>
            <script src="https://upload-widget.cloudinary.com/latest/global/all.js" type="text/javascript" />
            </head>
            <body className={`${inter.className} antialiased`}>
                {children}
            </body>
        </html>
    );
}
