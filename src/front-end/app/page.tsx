"use client"

import React, { useEffect, useState } from "react";

import Header from "@/app/components/header";
import SideNav from "@/app/components/sidenav";
import ConnectorsBox from "@/app/components/connectors/connectors-box";
import CreatePost, { triggerOpenDialog } from "@/app/components/home/create-post";

interface Media {
    mediaID: number;
    mediaURI: string;
}

interface Tag {
    userID: number;
    username: string;
}

interface PostData {
    postID: number;
    originalAuthor: number;
    authorName: string;
    authorAvatar: string;
    postCaption: string;
    postCreation: string;
    postLastUpdate: string;
    media: Media[];
    upvoteCount: number;
    tags: Tag[];
    comments: Comment[];
}

interface Comment {
    commentID: number;
    commentAuthor: number;
    commentCaption: string;
    commentCreation: string;
    authorName: string;
    authorAvatar: string;
}

export default function Page() {
    const [posts, setPosts] = useState<PostData[]>([]);
    useEffect(() => {
        const mockPosts: PostData[] = [
            {
                postID: 1,
                originalAuthor: 101,
                authorName: 'Nguyễn Văn A',
                authorAvatar: 'https://via.placeholder.com/50',
                postCaption: 'Đây là bài đăng đầu tiên của tôi!',
                postCreation: '2024-12-23',
                postLastUpdate: '2024-12-23',
                media: [
                    {
                        mediaID: 1,
                        mediaURI: 'https://via.placeholder.com/500',
                    },
                ],
                upvoteCount: 10,
                tags: [
                    {
                        userID: 102,
                        username: 'Trần Thị B',
                    },
                ],
                comments: [
                    {
                        commentID: 1,
                        commentAuthor: 201,
                        commentCaption: 'Bài đăng thú vị quá!',
                        commentCreation: '2024-12-23T12:00:00',
                        authorName: 'Lê Văn C',
                        authorAvatar: 'https://via.placeholder.com/50',
                    },
                    {
                        commentID: 2,
                        commentAuthor: 202,
                        commentCaption: 'Tôi đồng ý!',
                        commentCreation: '2024-12-23T13:00:00',
                        authorName: 'Nguyễn Thị D',
                        authorAvatar: 'https://via.placeholder.com/50',
                    },
                ],
            },
            {
                postID: 2,
                originalAuthor: 102,
                authorName: 'Trần Thị B',
                authorAvatar: 'https://via.placeholder.com/50',
                postCaption: 'Hôm nay thật tuyệt vời!',
                postCreation: '2024-12-24',
                postLastUpdate: '2024-12-24',
                media: [],
                upvoteCount: 15,
                tags: [],
                comments: []
            },
        ];

        setPosts(mockPosts);
    }, []);

    const addCommentToPost = (postID: number, newComment: Comment) => {
        setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.postID === postID
                    ? { ...post, comments: [...post.comments, newComment] }
                    : post
            )
        );
    };

    return (
        <main>
            <Header />
            <SideNav />
            <ConnectorsBox />
            <div className="absolute flex items-center left-[558px] top-[90px] w-[750px] h-[90px] bg-[#f4f4f4] rounded-[15px]">
                <img
                    src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png"
                    width={50}
                    height={50}
                    className="ml-[25px]"
                />
                <span className="w-[15px]" />
                <div>
                    <button
                        className="text-xl text-[#3a3a3a]"
                        onClick={triggerOpenDialog}
                    >What do you think?</button>
                    <CreatePost />
                </div>
            </div>
            {/* Vùng hiển thị bài đăng */}
            <div className="absolute top-[200px] left-[558px] w-[750px]">
                {/* {posts.map((post) => (
                    <Post key={post.postID} post={post} 
                    addComment={(newComment: Comment) =>
                            addCommentToPost(post.postID, newComment)
                        }
                    />
                ))} */}
            </div>
        </main>
    );
}
