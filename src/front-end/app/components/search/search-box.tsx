import SearchBar from "@/app/components/search/search-bar";
import SearchCard from "@/app/components/search/search-card";

export default function SearchBox() {
    return (
        <div className="relative w-full top-[90px] mx-[20px] mb-[20px] bg-[#f4f4f4] rounded-[15px]">
            <div className="mx-[20px] mt-[20px] mb-[10px]">
                <SearchBar />
                <p className="my-[10px] text-lg text-black font-medium">Connect suggestion</p>
                <div className="w-full h-full overflow-x-hidden overflow-y-auto">
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                    <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />
                    <SearchCard />
                </div>
            </div>
        </div>
    );
}