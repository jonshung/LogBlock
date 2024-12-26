export default function SearchBar() {
    return (
        <div className="flex items-center w-full h-[50px] bg-[#e8e8e8] rounded-[15px]">
            <img
                src="https://img.icons8.com/?size=30&id=59878&format=png&color=000000"
                className="ml-[10px]"
            />
            <input
                type="text"
                className="w-full h-full ml-[5px] text-black bg-[#e8e8e8] rounded-[15px] outline-none"
                placeholder="Search"
            />
        </div>
    );
}