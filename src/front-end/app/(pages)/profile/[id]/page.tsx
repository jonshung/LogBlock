import { notFound } from "next/navigation";
import { getProfileData } from "@/app/utils/ProfileAPI";
import { getConnectionsTo } from "@/app/utils/ConnectionAPI";
import { ProfileComponent } from "./ProfileComponent";
import { InfiniteScrollingContainer } from "@/app/components/infinite-scrolling";
import { profileFeedGenerate } from "@/app/utils/feed-generation/profile-feed-generator";
export const dynamic = "force-dynamic"

export default async function Page({ params }: { params: Promise<{ id: number }> }) {
    const id = (await params).id;
    const profile_data = await getProfileData(id.toString());
    if (profile_data == null) {
        return notFound();
    }

    const name = profile_data.displayName;
    const bio = profile_data.bioDesc;

    let connectors = await getConnectionsTo(id.toString());
    if (connectors == null) {
        connectors = [];
    }

    return (
        <main className="relative w-full top-[90px] flex flex-col gap-8">
            <ProfileComponent name={name} bio={bio} connectors={connectors.length} />
            <InfiniteScrollingContainer generatingFunction={profileFeedGenerate} profileContext={profile_data} />
        </main>
    );
}