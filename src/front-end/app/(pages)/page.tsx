import CreateBox from "@/app/components/create-box";

import { InfiniteScrollingContainer } from "./infinite-scrolling";
import { newsFeedGenerate } from "../utils/feed-generation/news-feed-generator";
import { getCurrentProfileData } from "../utils/ProfileAPI";

export default async function Page() {
    const cUser = await getCurrentProfileData();
    return (
        <main className="relative flex top-[90px]">
            <div className="absolute flex flex-col w-full h-full">
                <CreateBox />
                {/* Vùng hiển thị bài đăng */}
                <InfiniteScrollingContainer generatingFunction={newsFeedGenerate} profileContext={cUser}/>
            </div>
        </main>
    );
}
