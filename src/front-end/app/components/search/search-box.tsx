import SearchBar from "@/app/components/search/search-bar";
import SearchCard from "@/app/components/search/search-card";

export default function SearchBox() {
    const connectorCards = [
        { name: "Henry Clauss", connectors: 53, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png" },
        { name: "David John", connectors: 1053, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/David_John_rnrdui.png" },
        { name: "Oliver Mia", connectors: 526, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Oliver_Mia_lcqgjv.png" },
        { name: "khi nào giỏi code thì đổi tên", connectors: 7, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/khi_n%C3%A0o_gi%E1%BB%8Fi_code_th%C3%AC_%C4%91%E1%BB%95i_t%C3%AAn_fucvla.png" },
        { name: "Jack Ma", connectors: 2000000, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Jack_Ma_urpn7f.png" },
        { name: "Study with me | Layla", connectors: 274, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Study_with_me_Layla_pkowwj.png" },
        { name: "Ben Foster", connectors: 20, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Ben_Foster_ffnulo.png" },
        { name: "code là đam mê", connectors: 31, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/code_l%C3%A0_%C4%91am_m%C3%AA_k5bnj7.png" },
        { name: "Karim Benzema", connectors: 580218, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769389/Karim_Benema_zxqx1f.png" },
        { name: "Vô danh", connectors: 0, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/V%C3%B4_danh_bfb5gs.png" }
    ];

    return (
        <div className="relative h-[840px] top-[90px] mx-[60px] bg-[#f4f4f4] rounded-[15px] overflow-hidden">
            <div className="relative h-full mx-[20px] mt-[20px] mb-[10px]">
                <SearchBar />
                <p className="my-[10px] text-lg text-black font-medium">Connect suggestion</p>
                <div className="relative w-full h-[705px] overflow-x-hidden overflow-y-auto">
                    {connectorCards.map((card, index) => (
                        <div key={index}>
                            <SearchCard name={card.name} connectors={card.connectors} image={card.image} />
                            {index < connectorCards.length - 1 && <hr className="w-full border border-[#b0b0b0]-100 my-[5px]" />}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}