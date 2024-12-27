export default function ReportBox() {
    const name = "Henry Clauss";
    const tags = ["Threads/Violence", "Spam", "Hate Speech", "Harassment", "False Information"];
    const time = "02/11/2024 22:14:32";

    return (
        <div className="w-full px-[25px] py-[20px] mb-[15px] bg-[#f4f4f4] rounded-[15px]">
            <div className="flex">
                <img
                    src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png"
                    width={60}
                    height={60}
                    className="h-[60px] w-[60px]"
                />
                <span className="w-[15px]" />
                <div>
                    {tags.length > 1 ? (
                        <p className="text-xl text-black"><span className="font-medium">{name}</span> reported this post with tags:</p>
                    ) : (
                        <p className="text-xl text-black"><span className="font-medium">{name}</span> reported this post with tag:</p>
                    )}
                    <div className="flex flex-wrap gap-[10px] mt-[5px]">
                        {tags.map((tag, index) => (
                            <div
                                key={index}
                                className="flex items-center justify-center h-[30px] px-[10px] bg-[#d9d9d9] rounded-[15px]"
                            >
                                <p className="text-black">
                                    {tag}
                                </p>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <div className="flex items-center justify-between mt-[20px]">
                <p className="text-black">{time}</p>
                <div className="flex gap-[15px] text-xl">
                    <button className="w-[90px] h-[30px] text-black border border-black rounded-[15px]">Reject</button>
                    <button className="w-[90px] h-[30px] text-white bg-[#0195f7] rounded-[15px]">Accept</button>
                </div>
            </div>
        </div>
    );
}