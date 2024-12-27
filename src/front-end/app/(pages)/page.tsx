import CreateBox from "@/app/components/create-box";
import Post from "@/app/components/posts/post";

import { PostData, CommentingData, ProfileData, CommentingDataExtended, PostDataExtended } from '@/app/interfaces/common-interfaces';
import { getPostData, getPostDataBy } from "@/app/utils/PostAPI";
import { getProfileData } from "@/app/utils/ProfileAPI";
import { getCommentingsDataOf } from "@/app/utils/CommentingAPI";


export default async function Page() {
    // const mockProfile = await getProfileData("1");
    // if (mockProfile == null) {
    //     return <></>
    // }

    const addCommentToPost = (postID: number, newComment: CommentingData) => {
        /**setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.postID === postID
                    ? { ...post, comments: [...post.comments, newComment] }
                    : post
            )
        );*/
    };

    const deletePost = (postID: number) => {
        //setPosts((prevPosts) => prevPosts.filter((post) => post.postID !== postID));
    };

    const handleSaveEditPost = (updatedPost: PostData) => {
        //setPosts(prevPosts =>
        //    prevPosts.map(post => (post.postID === updatedPost.postID ? updatedPost : post))
        //);
    };

    // const posts = await getPostDataBy(mockProfile.userID.toString());
    // if (posts == null) {
    //     return <></>;
    // }


    // interface PostDataBundle {
    //     postData: PostDataExtended;
    //     comments: CommentingDataExtended[];
    // };
    // let postMap: PostDataBundle[] = [];

    // for (let i = 0; i < posts.length; i++) {
    //     const post = posts[i];
    //     const comments = await getCommentingsDataOf(post.postID.toString());
    //     const post_author = await getProfileData(post.originalAuthor.toString());
    //     if (comments == null || post_author == null) continue;

    //     let extension_map: CommentingDataExtended[] = [];

    //     for (let j = 0; j < comments.length; ++j) {
    //         const authorData = await getProfileData(comments[j].authorID.toString());
    //         if (authorData == null) continue;

    //         let extension: CommentingDataExtended = {
    //             commentData: comments[j],
    //             authorImage: authorData.profileImg,
    //             authorDisplayName: authorData.displayName
    //         };
    //         extension_map.push(extension);
    //     }

    //     const extension_post: PostDataExtended = {
    //         postData: post,
    //         authorDisplayName: post_author.displayName,
    //         authorImage: post_author.profileImg
    //     };
    //     postMap.push({ postData: extension_post, comments: extension_map });
    // }

    return (
        <main className="relative top-[90px]">
            <CreateBox />
            
            {/* Vùng hiển thị bài đăng */}
            <div className="w-full">
                {/* {postMap.map((post: PostDataBundle) => (
                    <Post
                        key={post.postData.postData.postID}
                        user={mockProfile}
                        //key={post.postID}
                        post={post.postData}
                        comments={post.comments}
                    //addComment={(newComment: CommentData) => addCommentToPost(post.postID, newComment)}
                    //deletePost={deletePost}
                    //onSave={handleSaveEditPost}
                    />
                ))} */}
            </div>
        </main>
    );
}
