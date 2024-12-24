export default function ConnectorsSearch() {
    return (
        <div className="relative">
            <input
                type="text"
                className="flex items-center w-[250px] h-[30px] bg-[#d9d9d9] rounded-[15px] text-black text-sm font-normal pl-[10px] outline-none"
                placeholder="Search"
            />
            <button className="absolute left-[220px] top-[5px]">
                <img
                    src="https://img.icons8.com/?size=20&id=XU3XKgdpT0qG&format=png&color=9f9f9f9f"
                />
            </button>
        </div>
    );
}