import CreateBox from "@/app/components/create-box";

import { InfiniteScrollingContainer } from "@/app/components/infinite-scrolling";
import { newsFeedGenerate } from "@/app/utils/feed-generation/news-feed-generator";
import { getCurrentProfileData } from "@/app/utils/ProfileAPI";

export default async function Page() {
    const cUser = await getCurrentProfileData();
    return (
        <main className="relative flex top-[90px]">
            <div className="absolute flex flex-col w-full h-full">
                <CreateBox cUser={cUser} />
                {/* Vùng hiển thị bài đăng */}
                <InfiniteScrollingContainer generatingFunction={newsFeedGenerate} profileContext={cUser}/>
            </div>
        </main>
    );
}
