import ConnectorsCard from "@/app/components/connectors/connectors-card";

export default function ConnectorsBox() {
    return (
        <div className="fixed w-[505px] left-[1336px] top-[90px] bg-[#f4f4f4] rounded-[15px]">
            <div className="relative flex items-center w-[465px] h-[64px] left-[20px]">
                <p className="text-black text-xl font-normal">Connectors</p>
                <span className="w-[105px]" />
                {/* Search box */}
                <div className="relative flex items-center w-[250px] h-[30px] bg-[#d9d9d9] rounded-[15px]">
                    <p className="ml-[14px] text-[15px] text-[#9f9f9f] font-bold">Search</p>
                    <span className="w-[154px]" />
                    <img src="https://img.icons8.com/?size=20&id=XU3XKgdpT0qG&format=png&color=9f9f9f9f" />
                </div>
            </div>
            {/* Connectors */}
            <div className="relative w-[465px] left-[20px] overflow-hidden">
                <ConnectorsCard name="Henry Clauss" />
                <ConnectorsCard name="David John" />
                <ConnectorsCard name="Oliver Mia" />
                <ConnectorsCard name="Hendrick Jale" />
                <ConnectorsCard name="khi nào giỏi code thì đổi tên" />
                <ConnectorsCard name="Jack Ma" />
                <ConnectorsCard name="Study with me | Layla" />
                <ConnectorsCard name="Ben Foster" />
                <ConnectorsCard name="code là đam mê" />
                <ConnectorsCard name="Karim Benzema" />
                <ConnectorsCard name="Vô danh" />
            </div>
            <div className="h-[10px]" />
        </div>
    );
}