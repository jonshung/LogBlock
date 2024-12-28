import ConnectorsCard from "@/app/components/connectors/connectors-card";
import ConnectorsSearch from "@/app/components/connectors/connectors-search";

export default function ConnectorsBox() {
    const connectorCards = [
        { name: "Henry Clauss", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png" },
        { name: "David John", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/David_John_rnrdui.png" },
        { name: "Oliver Mia", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Oliver_Mia_lcqgjv.png" },
        { name: "khi nào giỏi code thì đổi tên", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/khi_n%C3%A0o_gi%E1%BB%8Fi_code_th%C3%AC_%C4%91%E1%BB%95i_t%C3%AAn_fucvla.png" },
        { name: "Jack Ma", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Jack_Ma_urpn7f.png" },
        { name: "Study with me | Layla", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Study_with_me_Layla_pkowwj.png" },
        { name: "Ben Foster", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Ben_Foster_ffnulo.png" },
        { name: "code là đam mê", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/code_l%C3%A0_%C4%91am_m%C3%AA_k5bnj7.png" },
        { name: "Karim Benzema", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769389/Karim_Benema_zxqx1f.png" },
        { name: "Vô danh", image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/V%C3%B4_danh_bfb5gs.png" }
    ];

    return (
        <div className="relative h-[840px] top-[90px] mx-[60px] bg-[#f4f4f4] rounded-[15px] overflow-hidden">
            <div className="mx-[20px]">
                <div className="flex items-center grid grid-cols-2 w-full h-[64px] left-[20px]">
                    <p className="text-black text-xl font-normal">Connectors</p>
                    <ConnectorsSearch />
                </div>
                <div className="relative w-full h-[705px] overflow-x-hidden overflow-y-auto">
                    {connectorCards.map((card, index) => (
                        <div key={index}>
                            <ConnectorsCard name={card.name} image={card.image} />
                        </div>
                    ))}
                </div>
                <div className="w-full h-[10px] bg-[#f4f4f4]" />
            </div>
        </div>
    );
}