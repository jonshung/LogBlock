'use client'

import { useEffect, useState } from "react";
import { PostDataBundle, ProfileData } from "../interfaces/common-interfaces";
import Post from "../components/posts/post";
import { useInView } from "react-intersection-observer";

export const POST_PER_PAGE = 4;

interface InfiniteScrollingContainerProps {
    generatingFunction: (excluding: number[], lim: number) => Promise<PostDataBundle[]>
    profileContext: ProfileData | null
};

export const InfiniteScrollingContainer = ({ generatingFunction, profileContext }: InfiniteScrollingContainerProps) => {
    const [generatedPosts, addGeneratedPosts] = useState<number[]>([]);
    const [postsBundle, setPostsBundle] = useState<PostDataBundle[]>([]);
    const [hasMore, setHasMore] = useState<boolean>(true);
    const [scrollTrigger, isInView] = useInView();
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

    return (
        <div className="w-full">
            <div>
                { postsBundle.map((postBundle) => (
                    <Post
                        key={postBundle.postData.postData.postID}
                        user={profileContext}
                        post={postBundle.postData}
                        comments={postBundle.comments}
                    />
                )) }
            </div>
            <div>
                {(hasMore && <div ref={scrollTrigger}>Loading...</div>)}
            </div>
        </div>
    );
}