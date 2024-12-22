import Header from "@/app/components/header";
import Navigator from "@/app/components/navigation-bar";
import ConnectorsBox from "@/app/components/connectors/connectors-box";

import { inter } from "@/app/ui/fonts";

export default function Page() {
    return (
        <main className={`${inter.className} bg-white`}>
            <Header pageName="Home" />
            <Navigator />
            <ConnectorsBox />
            <div className="absolute flex items-center left-[558px] top-[90px] w-[750px] h-[90px] bg-[#f4f4f4] rounded-[15px]">
                <img
                    src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png"
                    alt=""
                    width={50}
                    height={50}
                    className="ml-[25px]"
                />
                <span className="w-[15px]" />
                <p className="text-xl text-[#3a3a3a]">What do you think?</p>
            </div>
            {/* <div className="w-[750px] h-[1000px] bg-[#f4f4f4] rounded-[15px] absolute top-[200px] left-[558px]"></div> */}
        </main>
    );
}
