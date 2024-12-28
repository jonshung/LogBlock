import ConnectorsCard from "@/app/components/connectors/connectors-card";
import ConnectorsSearch from "@/app/components/connectors/connectors-search";

export default function ConnectorsBox() {
    return (
        <div className="relative top-[90px] ml-[20px] mr-[100px] mb-[20px] bg-[#f4f4f4] rounded-[15px]">
            <div className="mx-[20px]">
                <div className="flex items-center grid grid-cols-2 w-full h-[64px] left-[20px]">
                    <p className="text-black text-xl font-normal">Connectors</p>
                    <ConnectorsSearch />
                </div>
                <div className="relative w-full overflow-x-hidden overflow-y-auto">
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                    <ConnectorsCard />
                </div>
                <div className="w-full h-[10px] bg-[#f4f4f4]" />
            </div>
        </div>
    );
}