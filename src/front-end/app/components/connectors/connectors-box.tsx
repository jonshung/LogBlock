import ConnectorsCard from "@/app/components/connectors/connectors-card";
import ConnectorsSearch from "@/app/components/connectors/connectors-search";

export default function ConnectorsBox() {
    return (
        <div className="fixed w-[505px] h-[846px] left-[1336px] top-[90px] bg-[#f4f4f4] rounded-[15px]">
            <div className="relative flex items-center w-[465px] h-[64px] left-[20px]">
                <p className="text-black text-xl font-normal">Connectors</p>
                <span className="w-[105px]" />
                {/* Search box */}
                <ConnectorsSearch />
            </div>
            {/* Connectors */}
            <div className="relative w-[470px] h-[772px] left-[20px] overflow-x-hidden overflow-y-auto">
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
        </div>
    );
}