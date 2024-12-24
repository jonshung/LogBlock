import localfont from "next/font/local";
import { Inter } from "next/font/google";

export const armageda = localfont({
    src : [{
        path: "../../public/Armageda Wide.ttf"
    }],
    variable: "--font-armageda"
})

export const inter = Inter({ subsets: ["latin"] });