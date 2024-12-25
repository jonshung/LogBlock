"use client"

import React, { useEffect, useState } from "react";

import { PostData, Comment } from "./interfaces/common-interfaces";
//import Post from "./components/posts/post";
import CreateBox from "./components/home/create-box";
import ConnectorsBox from "./components/connectors/connectors-box";

export default function Page() {
    const [posts, setPosts] = useState<PostData[]>([]);
    useEffect(() => {
        const mockPosts: PostData[] = [
            {
                postID: 1,
                originalAuthor: 101,
                authorName: 'Nguyễn Văn A',
                authorAvatar: 'https://img.icons8.com/?size=100&id=23264&format=png&color=000000',
                postCaption: 'Đây là bài đăng đầu tiên của tôi!',
                postCreation: '2024-12-23',
                postLastUpdate: '2024-12-23',
                media: [
                    {
                        mediaID: 1,
                        mediaURI: 'https://img.icons8.com/?size=100&id=c4coyqckQ4EU&format=png&color=000000',
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
                        authorAvatar: 'https://img.icons8.com/?size=100&id=23240&format=png&color=000000',
                    },
                    {
                        commentID: 2,
                        commentAuthor: 202,
                        commentCaption: 'Tôi đồng ý!',
                        commentCreation: '2024-12-23T13:00:00',
                        authorName: 'Nguyễn Thị D',
                        authorAvatar: 'https://img.icons8.com/?size=100&id=2zX81vvl75DJ&format=png&color=000000',
                    },
                ],
            },
            {
                postID: 2,
                originalAuthor: 102,
                authorName: "Trần Thị B",
                authorAvatar: "https://img.icons8.com/?size=100&id=23264&format=png&color=000000",
                postCaption: "Đây là bài đăng thứ hai của tôi!",
                postCreation: "2024-12-24",
                postLastUpdate: "2024-12-24",
                media: [],
                upvoteCount: 20,
                tags: [],
                comments: [],
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

    const deletePost = (postID: number) => {
        setPosts((prevPosts) => prevPosts.filter((post) => post.postID !== postID));
    };

    const handleSaveEditPost = (updatedPost: PostData) => {
        setPosts(prevPosts =>
            prevPosts.map(post => (post.postID === updatedPost.postID ? updatedPost : post))
        );
    };

    
    return (
        <>
            <div className="relative w-full h-full">
                <CreateBox />
            
                {/* Vùng hiển thị bài đăng */}
                <div className="absolute top-[200px] w-[750px]">
                    {/*posts.map((post) => (
                        <Post
                            key={post.postID}
                            post={post}
                            addComment={(newComment: Comment) => addCommentToPost(post.postID, newComment)}
                            deletePost={deletePost}
                            onSave={handleSaveEditPost}
                            onSave={handleSaveEditPost}
                        />
                    ))*/}
                </div>
            </div>

            <div className="relative flex justify-center">
                <ConnectorsBox />
            </div>
        </>
    );
}
