export default function ConnectorsSearch() {
    return (
        <div className="relative flex items-center justify-end">
            <input
                type="text"
                className="flex items-center w-full h-[30px] bg-[#d9d9d9] rounded-[15px] text-black text-sm font-normal pl-[10px] outline-none"
                placeholder="Search"
            />
            <button className="absolute mr-[10px]">
                <img src="https://img.icons8.com/?size=20&id=XU3XKgdpT0qG&format=png&color=9f9f9f9f" />
            </button>
        </div>
    );
}