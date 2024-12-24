import SearchBar from "@/app/components/search/search-bar";
import SearchCard from "@/app/components/search/search-card";

export default function SearchBox() {
    return (
        <div className="fixed w-[505px] h-[846px] left-[1336px] top-[90px] bg-[#f4f4f4] rounded-[15px]">
            <SearchBar />
            <p className="relative left-[20px] top-[80px] text-lg text-black font-medium">Connect suggestion</p>
            <div className="absolute w-[470px] h-[716px] left-[20px] top-[110px] overflow-x-hidden overflow-y-auto">
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
                <hr className="w-[465px] border border-[#b0b0b0]-100 my-[5px]" />
                <SearchCard />
            </div>
        </div>
    );
}