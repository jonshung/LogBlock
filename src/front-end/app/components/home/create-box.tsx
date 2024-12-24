"use client";

import { triggerOpenDialog } from "./create-post";
import CreatePost from "@/app/components/home/create-post";
// import CreatePostTemp from "@/app/components/home/create-post-temp";

import { useState } from "react";

export default function CreateBox() {
    // const [state, setPopup] = useState(false);

    // const togglePopup = () => {
    //     setPopup(!state);
    // }

    return (
        <>
            <div
                onClick={triggerOpenDialog}
                style={{ cursor: "pointer" }}
                className="absolute flex items-center left-[558px] top-[90px] w-[750px] h-[90px] bg-[#f4f4f4] rounded-[15px]"
            >
                <img
                    src="https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png"
                    width={50}
                    height={50}
                    className="ml-[25px]"
                />
                <span className="w-[15px]" />
                <p className="text-xl text-[#3a3a3a]">What do you think?</p>
                <CreatePost />
            </div>

            {/* {state && (
                <div className="fixed h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
                    <CreatePostTemp />
                    <button onClick={togglePopup} className="absolute top-[50px] right-[50px] text-black bg-[#f4f4f4] rounded-[15px] p-[10px]">
                        Close
                    </button>
                </div>
            )} */}
        </>
    );
}