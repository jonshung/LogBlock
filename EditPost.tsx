'use client';

import React, { useState } from 'react';
import { PostData, Media } from '@/app/interfaces/common-interfaces';

interface EditPostProps {
    post: PostData;
    onSave: (updatedPost: PostData) => void;
    onCancel: () => void;
}

export default function EditPost({ post, onSave, onCancel }: EditPostProps) {
    const [content, setContent] = useState(post.postCaption);
    const [tags, setTags] = useState(post.tags.map(tag => tag.username).join(', '));
    const [media, setMedia] = useState<Media[]>(post.media);

    const handleMediaUploaded = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const newMedia = Array.from(e.target.files).map(file => ({
                mediaID: Date.now(),
                mediaURI: URL.createObjectURL(file),
            }));
            setMedia([...media, ...newMedia]);
        }
    };

    const handleMediaRemoved = (mediaID: number) => {
        setMedia(media.filter(item => item.mediaID !== mediaID));
    };

    const handleSave = () => {
        const updatedPost: PostData = {
            ...post,
            postCaption: content,
            tags: tags.split(',').map(tag => ({ userID: Date.now(), username: tag.trim() })),
            media,
        };
        onSave(updatedPost); // Gọi hàm lưu bài viết với dữ liệu mới
    };

    return (
        <div className="fixed h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
            <div className="flex items-center justify-center h-screen w-screen left-0 top-0">
                <div className="relative w-[1000px] h-[856px] bg-white rounded-[15px]">
                    <div className="flex items-center justify-between h-[50px] mx-[10px]">
                        <p
                            onClick={onCancel}
                            className="text-xl text-[#b0b0b0] font-bold hover:cursor-pointer"
                        >
                            Cancel
                        </p>
                        <p className="text-[1.625rem] text-black font-bold">Edit Post</p>
                        <p
                            onClick={handleSave}
                            className="text-xl text-[#0195F7] font-bold hover:cursor-pointer"
                        >
                            Edit
                        </p>
                    </div>
                    <hr className="border border-[#b0b0b0]" />
                    <div className="grid w-[960px] h-[771px] ml-[20px] mt-[15px]">
                        {/* Content */}
                        <div>
                            <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Content</p>
                            <textarea
                                name="content"
                                value={content}
                                onChange={(e) => setContent(e.target.value)}
                                placeholder="Edit content here..."
                                className="w-full h-[350px] p-[10px] text-black border border-[#b0b0b0] rounded-[15px] resize-none"
                            />
                        </div>
                        {/* Tags */}
                        <div>
                            <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Tags</p>
                            <textarea
                                name="tags"
                                value={tags}
                                onChange={(e) => setTags(e.target.value)}
                                placeholder="Edit tags here..."
                                className="w-full h-[40px] px-[10px] py-[7px] text-black border border-[#b0b0b0] rounded-[15px] resize-none"
                            />
                        </div>
                        {/* Media */}
                        <div>
                            <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Media</p>
                            <div className="w-full h-[270px] text-black border border-[#b0b0b0] rounded-[15px] resize-none">
                                {media.length > 0 ? (
                                    <div className="flex grid-cols-3 gap-[15px] p-[15px]">
                                        {media.map(item => (
                                            <div key={item.mediaID} className="relative">
                                                <img
                                                    src={item.mediaURI}
                                                    alt={`media-${item.mediaID}`}
                                                    width={300}
                                                    height={300}
                                                    className="object-cover rounded-[15px]"
                                                />
                                                <img
                                                    src="https://img.icons8.com/?size=18&id=81432&format=png&color=000000"
                                                    onClick={() => handleMediaRemoved(item.mediaID)}
                                                    className="absolute right-[5px] top-[5px] cursor-pointer"
                                                />
                                            </div>
                                        ))}
                                    </div>
                                ) : (
                                    <div className="relative flex items-center justify-center w-full h-full">
                                        <p className="absolute text-[1.5rem] font-bold">No media uploaded</p>
                                        <input
                                            name="media"
                                            type="file"
                                            accept="image/*, video/*"
                                            onChange={handleMediaUploaded}
                                            multiple
                                            className="absolute w-full h-full left-0 top-0 opacity-0"
                                        />
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}