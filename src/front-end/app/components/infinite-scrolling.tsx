'use client'

import { useEffect, useState } from "react";
import { PostDataBundle, ProfileData } from "../interfaces/common-interfaces";
import Post from "./posts/post";
import { useInView } from "react-intersection-observer";
import { useRouter } from "next/navigation";
import { bundlePostAndComment, getPostData } from "../utils/PostAPI";

export const POST_PER_PAGE = 4;

interface InfiniteScrollingContainerProps {
    generatingFunction: (excluding: number[], lim: number) => Promise<PostDataBundle[]>
    profileContext: ProfileData | null
};

export const InfiniteScrollingContainer = ({ generatingFunction, profileContext }: InfiniteScrollingContainerProps) => {
    const [generatedPosts, addGeneratedPosts] = useState<number[]>([]);
    const [postsBundle, setPostsBundle] = useState<PostDataBundle[]>([]);
    const [hasMore, setHasMore] = useState<boolean>(true);
    const [scrollTrigger, isInView] = useInView({
        threshold: 1
    });
    const router = useRouter();

    useEffect(() => {
        if (isInView && hasMore) {
          loadMorePosts();
        }
      }, [isInView, hasMore]);
      
    const loadMorePosts = async () => {
        if(!hasMore) return;
        const posts = await generatingFunction(generatedPosts, POST_PER_PAGE);
        if(posts.length == 0) {
            setHasMore(false);
            return;
        }
        posts.forEach((post) => {
            generatedPosts.push(post.postData.postData.postID);
        })
        setPostsBundle((prev) => [...prev, ...posts] )
    };

    const reload = async (postID: number) => {
        for(let i = 0; i < postsBundle.length; i++) {
            let bundle = postsBundle[i];
            if(bundle.postData.postData.postID != postID) continue;
            let postData = null;
            postData = await getPostData(postID.toString());
            if(!postData) return;

            const newBundle = await bundlePostAndComment([postData]);
            if(!newBundle) return;
            
            postsBundle[i] = newBundle[0];
            break;
        }
        router.refresh();
    }

    return (
        <div className="w-full block">
            <div>
                { postsBundle.map((postBundle) => (
                    <Post
                        key={postBundle.postData.postData.postID}
                        user={profileContext}
                        post={postBundle.postData}
                        comments={postBundle.comments}
                        postMedias={postBundle.postMedia}
                        reloadTrigger={reload}
                    />
                )) }
            </div>
            <div>
                {(hasMore && <div ref={scrollTrigger}>Loading...</div>)}
            </div>
        </div>
    );
}