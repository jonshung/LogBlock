import Header from "@/app/components/header";
import Navigator from "@/app/components/navigation-bar";
// import ConnectorsBox from "@/app/components/connectors/connectors-box";

import { inter } from "@/app/ui/fonts";

export default function Page() {
    return (
        <main className={`${inter.className} bg-white`}>
            <Header pageName="Explore" />
            <Navigator />
            {/* <div className="w-[750px] h-[1000px] bg-[#f4f4f4] rounded-[15px] absolute top-[200px] left-[558px]"></div> */}
        </main>
    );
}