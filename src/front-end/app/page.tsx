import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import ConnectorsBox from "@/app/components/connectors/connectors-box";
import CreateBox from "@/app/components/home/create-box";

export default function Page() {
    return (
        <main>
            <Header />
            <SideNav />
            <ConnectorsBox />
            <CreateBox />
            {/* <div className="w-[750px] h-[1000px] bg-[#f4f4f4] rounded-[15px] absolute top-[200px] left-[558px]"></div> */}
        </main>
    );
}
