import { explorationFeedGenerate } from "@/app/utils/feed-generation/exploration-feed-generator";
import { InfiniteScrollingContainer } from "../infinite-scrolling";
import { getCurrentProfileData } from "@/app/utils/ProfileAPI";

export default async function Page() {
    const cUser = await getCurrentProfileData();
    return (
        <main className="relative flex top-[90px]">
            <div className="absolute flex flex-col w-full h-full">
                {/* Vùng hiển thị bài đăng */}
                <InfiniteScrollingContainer generatingFunction={explorationFeedGenerate} profileContext={cUser}/>
            </div>
        </main>
    );
}