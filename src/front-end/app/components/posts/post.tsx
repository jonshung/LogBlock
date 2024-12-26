'use client';

import { useState } from 'react';
import EditPost from './edit-post';
import { PostData, Media, Tag, Comment, ProfileData, } from '@/app/interfaces/common-interfaces';

interface PostProps {
    post: PostData;
    addComment: (newComment: Comment) => void;
    deletePost: (postID: number) => void;
    onSave: (updatedPost: PostData) => void;
    user : ProfileData | null;
}

const Post: React.FC<PostProps> = ({ post, addComment, deletePost, onSave, user }) => {
    if (!user) {
        return <div>Loading user information...</div>;
    }


    const [isLiked, setIsLiked] = useState(false);
    const [isSharedDialogOpen, setSharedDialogOpen] = useState(false);
    const [comments, setComments] = useState<Comment[]>(post.comments || []);
    const [newComment, setNewComment] = useState('');
    const [showComments, setShowComments] = useState(false);
    const [visibleComments, setVisibleComments] = useState(2);
    const [editMenuOpen, setEditMenuOpen] = useState(false);
    const [isEditing, setIsEditing] = useState(false);


    const handleLike = () => {
        setIsLiked((prev) => !prev);
        post.upvoteCount += isLiked ? -1 : 1;
    };

    const toggleComments = () => setShowComments((prev) => !prev);

    const handleAddComment = () => {
        if (newComment.trim()) {
            const newCommentData: Comment = {
                commentID: Date.now(),
                commentAuthor: 1, // Mock user ID
                commentCaption: newComment,
                commentCreation: new Date().toISOString(),
                authorName: user.displayName, // Mock user name
                authorAvatar : user.profileImage, // Mock user avatar
            };

            addComment(newCommentData); // Add comment via prop function
            setComments((prev) => [...prev, newCommentData]); // Update local comments
            setNewComment(''); // Clear input
        }
    };

    const handleDeletePost = () => {
        deletePost(post.postID);
        setEditMenuOpen(false);
        setIsEditing(true);
    };

    const handleSaveEdit = () => {
        setEditMenuOpen(false);
        setIsEditing(true);
    };

    const handleCancelEdit = () => {
        setEditMenuOpen(false);
        setIsEditing(false);
    };

    return (
        <div className="post">
            {/* Header */}
            <div className="post-header">
                <img src={post.authorAvatar} alt={post.authorName} className="avatar" />
                <div>
                    <p className="name">{post.authorName}</p>
                    <p className="date">{post.postCreation}</p>
                </div>

                <div className='edit-button'>
                    <button
                        onClick={() => setEditMenuOpen((prev) => !prev)}
                    >
                        ...
                    </button>

                    {/* Menu chỉnh sửa bài viết */}
                    {editMenuOpen && (
                        <div className='edit-menu'
                        >
                            <button
                                className='edit-menu-button'
                                onClick={handleSaveEdit}
                            >
                                <img
                                    src='https://img.icons8.com/?size=100&id=59856&format=png&color=000000'
                                    alt='Delete'
                                    style={{ width: '20px', height: '20px' }}
                                />
                                Edit </button>

                            <button
                                className='edit-menu-button'
                                onClick={handleDeletePost}
                            >
                                <img
                                    src='https://img.icons8.com/?size=100&id=8329&format=png&color=000000'
                                    alt='Delete'
                                    style={{ width: '20px', height: '20px' }}
                                />
                                Delete post
                            </button>
                        </div>
                    )}
                    {isEditing && (
                        <EditPost
                            post={post}
                            onSave={(updatedPost) => {
                                onSave(updatedPost);
                                setIsEditing(false);
                            }}
                            onCancel={handleCancelEdit}
                        />
                    )}
                </div>
            </div>

            {/* Content */}
            <div className="post-content">{post.postCaption}</div>

            {/* Media */}
            <div>
                {post.media.map((media) => (
                    <img
                        className="post-image"
                        key={media.mediaID}
                        src={media.mediaURI}
                        alt={`media-${media.mediaID}`}
                    />
                ))}
            </div>

            {/* Actions */}
            <div
                className="post-actions"
                style={{
                    display: 'flex',
                    justifyContent: 'flex-start',
                    alignItems: 'center',
                    gap: '10px',
                }}
            >
                <button
                    onClick={handleLike}
                    className={isLiked ? 'like' : 'not-like'}
                    onMouseEnter={(e) => (e.currentTarget.style.background = '#d6d4d4')}
                    onMouseLeave={(e) => (e.currentTarget.style.background = '#f1f1f1')}
                    style={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        padding: '10px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        backgroundColor: '#f1f1f1',
                        gap: '8px',
                    }}
                >
                    <img
                        src='https://img.icons8.com/?size=100&id=KpwukvSgSCZ8&format=png&color=000000'
                        alt='Commenting icon'
                        style={{ width: '20px', height: '20px' }}
                    /> {post.upvoteCount}
                </button>


                <button
                    onClick={toggleComments}
                    className="comments"
                    onMouseEnter={(e) => (e.currentTarget.style.background = '#d6d4d4')}
                    onMouseLeave={(e) => (e.currentTarget.style.background = '#f1f1f1')}
                    style={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        padding: '10px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        backgroundColor: '#f1f1f1',
                    }}
                >
                    <img
                        src='https://img.icons8.com/?size=100&id=143&format=png&color=000000'
                        alt='Commenting icon'
                        style={{ width: '20px', height: '20px' }}
                    />
                </button>
                <button
                    onClick={() => setSharedDialogOpen(true)}
                    className="share"
                    onMouseEnter={(e) => (e.currentTarget.style.background = '#d6d4d4')}
                    onMouseLeave={(e) => (e.currentTarget.style.background = '#f1f1f1')}
                    style={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        padding: '10px',
                        borderRadius: '5px',
                        border: 'none',
                        cursor: 'pointer',
                        backgroundColor: '#f1f1f1',
                    }}
                >
                    <img
                        src='https://img.icons8.com/?size=100&id=t1lGYcKNLhnE&format=png&color=000000'
                        alt='Share icon'
                        style={{ width: '20px', height: '20px' }}
                    />
                </button>
            </div>


            {/* Comments Section */}
            {showComments && (
                <div className="comments-Dialog">
                    {/* Add Comment */}
                    <div style={{ display: 'flex', alignItems: 'center', width: '100%' }}>
                        {/* Avatar */}
                        <img
                            src={user.profileImage}
                            alt={user.displayName}
                            style={{
                                width: '50px',
                                height: '50px',
                                borderRadius: '50%',
                                marginRight: '10px',
                                marginBottom: '10px',
                            }}
                        />
                        {/* Textarea */}
                        <textarea
                            value={newComment}
                            onChange={(e) => setNewComment(e.target.value)}
                            placeholder="Write a comment..."
                            maxLength={500}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter' && !e.shiftKey) {
                                    e.preventDefault();
                                    handleAddComment();
                                }
                            }}
                            style={{
                                flex: 1,
                                padding: '10px',
                                border: '1px solid #d7d9d7',
                                borderRadius: '15px',
                                resize: 'none',
                                height: '50px',
                            }}
                        />
                    </div>

                    {visibleComments < comments.length && (
                        <button
                            style={{ fontWeight: 'bold', margin: '5px' }}
                            onClick={() => setVisibleComments((prev) => prev + 2)}
                        >
                            Show more comments
                        </button>
                    )}

                    {/* Comments Section */}
                    {comments.slice(0, visibleComments).map((comment) => (
                        <div
                            key={comment.commentID}
                            style={{
                                display: 'flex',
                                alignItems: 'flex-start',
                                marginBottom: '15px',
                            }}
                        >
                            {/* Avatar */}
                            <img
                                src={comment.authorAvatar || 'https://via.placeholder.com/50'}
                                alt={comment.authorName}
                                style={{
                                    width: '50px',
                                    height: '50px',
                                    borderRadius: '50%',
                                    marginRight: '10px',
                                }}
                            />

                            {/* Nội dung comment */}
                            <div style={{ flex: 1 }}>
                                {/* Tên người dùng */}
                                <p style={{ margin: '0', fontWeight: 'bold', color: '#000' }}>
                                    {comment.authorName}
                                </p>
                                {/* Nội dung comment */}
                                <p style={{ margin: '5px 0 0', color: '#555' }}>
                                    {comment.commentCaption}
                                </p>
                            </div>
                        </div>
                    ))}

                </div>
            )}


            {/* Share Dialog */}
            {isSharedDialogOpen && (
                <div
                    style={{
                        position: 'fixed',
                        top: 0,
                        left: 0,
                        width: '100%',
                        height: '100%',
                        backgroundColor: 'rgba(0, 0, 0, 0.5)',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        zIndex: 1000,
                    }}
                >
                    <div
                        style={{
                            backgroundColor: 'white',
                            padding: '20px',
                            borderRadius: '10px',
                            width: '30%',
                            height: '15%',
                            color: '#000000',
                            fontWeight: 'bold'
                        }}
                    >
                        <p style={{ fontSize: '25px', textAlign: 'center', marginBottom: '30px' }}>Link is saved to clipboard</p>

                        <hr style={{ border: '1px solid #ccc', margin: '10px' }} />

                        <button
                            style={{
                                backgroundColor: '#ffffff',
                                color: '#4075c9',
                                cursor: 'pointer',
                                width: '100%',
                                fontWeight: 'bold',
                                borderRadius: '13px'
                            }}
                            onClick={() => setSharedDialogOpen(false)}
                            onMouseEnter={(e) => (e.currentTarget.style.background = '#d6d4d4')}
                            onMouseLeave={(e) => (e.currentTarget.style.background = '#ffffff')}
                        >OK</button>
                    </div>
                </div>
            )}
        </div>
    );
};
export default Post;