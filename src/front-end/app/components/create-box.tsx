"use client";

import { useState } from "react";

export default function CreateBox() {
    const [dialogue, setDialogue] = useState(false);
    const [confirmDialogue, setConfirmDialogue] = useState(false);
    const [create, setCreate] = useState(false);
    const [media, setMedia] = useState<FileList | null>(null);

    const toggleUp = () => {
        setCreate(false);
        setDialogue(!dialogue);
    }

    const handleCancel = () => {
        setConfirmDialogue(!confirmDialogue);
    }

    const handleCreate = async () => {
        setCreate(!create);
        setConfirmDialogue(false);
        setDialogue(false);

        if (media) {
            for (let i = 0; i < media.length; i++) {
                const data = new FormData();

                data.append("file", media[i]);
                data.append("upload_preset", "logblock");

                const res = await fetch("https://api.cloudinary.com/v1_1/dumr9ghyv/image/upload", {
                    method: "POST",
                    body: data
                });

                const file = await res.json();
                console.log(file.url); // file.url is the URL of the uploaded media
            }

            setMedia(null);
        }
    }

    const handleDiscard = () => {
        setConfirmDialogue(!confirmDialogue);
        setDialogue(!dialogue);
    }

    const handleCancelConfirmDialogue = () => {
        setConfirmDialogue(!confirmDialogue);
    }

    const handleMediaUploaded = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setMedia(e.target.files);
        }
    }

    const handleMediaRemoved = (file: File) => {
        if (media) {
            const newMedia = Array.from(media).filter(f => f !== file);
            const dataTransfer = new DataTransfer();
            newMedia.forEach(file => dataTransfer.items.add(file));
            setMedia(dataTransfer.files.length ? dataTransfer.files : null);
        }
    }

    return (
        <>
            <div className="absolute flex items-start w-full h-full">
                <div
                    onClick={toggleUp}
                    style={{ cursor: "pointer" }}
                    className="flex items-center w-full h-[90px] bg-[#f4f4f4] rounded-[15px]"
                >
                    <img
                        src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png"
                        width={50}
                        height={50}
                        className="ml-[25px]"
                    />
                    <span className="w-[15px]" />
                    <p className="text-xl text-[#3a3a3a]">What do you think?</p>
                </div>
            </div>

            {dialogue && (
                <div className="fixed h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
                    <div className="flex items-center justify-center h-screen w-screen left-0 top-0">
                        <div className="relative w-[1000px] h-[856px] bg-white rounded-[15px]">
                            <div className="flex items-center justify-between h-[50px] mx-[10px]">
                                <p
                                    onClick={handleCancel}
                                    className="text-xl text-[#b0b0b0] font-bold hover:cursor-pointer"
                                >
                                    Cancel
                                </p>
                                <p className="text-[1.625rem] text-black font-bold">Create new post</p>
                                <p
                                    onClick={handleCreate}
                                    className="text-xl text-[#0195F7] font-bold hover:cursor-pointer"
                                >
                                    Create
                                </p>
                            </div>
                            <hr className="border border-[#b0b0b0]" />
                            <div className="grid w-[960px] h-[771px] ml-[20px] mt-[15px]">
                                <div>
                                    <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Content</p>
                                    <textarea
                                        name="content"
                                        placeholder="Type something here..."
                                        className="w-full h-[350px] p-[10px] text-black border border-[#b0b0b0] rounded-[15px] resize-none"
                                    />
                                </div>
                                <div>
                                    <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Tags</p>
                                    <textarea
                                        name="tags"
                                        placeholder="Tag someone here..."
                                        className="w-full h-[40px] px-[10px] py-[7px] text-black border border-[#b0b0b0] rounded-[15px] resize-none"
                                    />
                                </div>
                                <div>
                                    <p className="ml-[7px] text-[1.1875rem] text-black font-bold">Media</p>
                                    <div className="w-full h-[270px] text-black border border-[#b0b0b0] rounded-[15px] resize-none overflow-x-hidden overflow-y-scroll">
                                        {media ? (
                                            <div className="flex grid-cols-3 gap-[15px] p-[15px]">
                                                {Array.from(media).map((file, index) => (
                                                    <div
                                                        key={index}
                                                        className="relative"
                                                    >
                                                        <img
                                                            src={URL.createObjectURL(file)}
                                                            alt={file.name}
                                                            width={300}
                                                            height={300}
                                                            className="object-cover rounded-[15px]"
                                                        />
                                                        <img
                                                            src="https://img.icons8.com/?size=18&id=81432&format=png&color=000000"
                                                            onClick={() => handleMediaRemoved(file)}
                                                            className="absolute right-[5px] top-[5px] cursor-pointer"
                                                        />
                                                    </div>
                                                ))}
                                            </div>
                                        ) : (
                                            <div className="relative flex items-center justify-center w-full h-full">
                                                <div className="flex grid gap-[15px] justify-center w-full h-full">
                                                    <img
                                                        src="https://img.icons8.com/?size=100&id=cD26kdwTbCzt&format=png&color=000000"
                                                        className="absolute left-[408px] top-[40px] transform scale-x-[-1] rotate-[-5deg]"
                                                    />
                                                    <img
                                                        src="https://img.icons8.com/?size=80&id=11322&format=png&color=000000"
                                                        className="absolute left-[483px] top-[42px] transform rotate-[-10deg]"
                                                    />
                                                    <div className="absolute w-[75px] h-[75.5px] left-[454.5px] top-[73px] rounded-[15px] bg-white rotate-[5deg]" />
                                                    <img
                                                        src="https://img.icons8.com/?size=100&id=7qnxH7kdZG9g&format=png&color=000000"
                                                        className="absolute left-[442px] top-[55px] rotate-[5deg]"
                                                    />
                                                    <p className="absolute left-[305px] top-[155px] text-[1.5rem] font-bold">Drag photos/videos/files here</p>
                                                    <button className="absolute left-[375px] top-[200px] w-[200px] h-[35px] text-white font-bold bg-[#0195f7] rounded-[15px]">
                                                        Select from computer
                                                    </button>
                                                </div>
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
            )}

            {(confirmDialogue && !create) && (
                <div className="fixed h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
                    <div className="flex items-center justify-center h-screen w-screen left-0 top-0">
                        <div className="w-[600px] h-[300px] bg-white rounded-[15px]">
                            <div className="flex flex-col items-center justify-center w-full h-[180px] text-black">
                                <p className="text-3xl font-bold">Discard post?</p>
                                <p className="text-xl font-medium">If you leave, your edits will not be saved.</p>
                            </div>
                            <hr className="border border-[#d0d0d0]" />
                            <button
                                onClick={handleDiscard}
                                className="w-full h-[58px] text-xl text-[#ff0000] font-medium hover:bg-[#f9f9f9]"
                            >
                                Discard
                            </button>
                            <hr className="border border-[#d0d0d0]" />
                            <button
                                onClick={handleCancelConfirmDialogue}
                                className="w-full h-[58px] text-xl text-black font-medium rounded-b-[15px] hover:bg-[#f9f9f9]"
                            >
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}