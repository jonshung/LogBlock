export default function SearchBar() {
    return (
        <div className="absolute flex items-center w-[465px] h-[50px] left-[20px] top-[20px] bg-[#e8e8e8] rounded-[15px]">
            <img
                src="https://img.icons8.com/?size=30&id=XU3XKgdpT0qG&format=png&color=000000"
                className="ml-[10px]"
            />
            <input
                type="text"
                className="w-[450px] h-[50px] ml-[5px] text-black bg-[#e8e8e8] rounded-[15px] outline-none"
                placeholder="Search"
            />
        </div>
    );
}