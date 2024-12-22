import Header from "@/app/components/header";
<<<<<<< HEAD
import SideNav from "@/app/components/sidenav";
=======
import Navigator from "@/app/components/navigation-bar";
// import ConnectorsBox from "@/app/components/connectors/connectors-box";

import { inter } from "@/app/ui/fonts";
>>>>>>> 7e155dddf62c4b8ace9fbcdded90ee819813c99d

export default function Page() {
    return (
        <main>
            <Header pageName="Explore" />
            <SideNav />
            {/* <div className="w-[750px] h-[1000px] bg-[#f4f4f4] rounded-[15px] absolute top-[200px] left-[558px]"></div> */}
        </main>
    );
}