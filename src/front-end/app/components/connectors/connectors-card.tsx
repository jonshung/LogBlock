"use client";

import { useState } from "react";

export default function ConnectorsCard({ name, image }: { name: string; image: string }) {
    const [directMessage, setDirectMessage] = useState(false);

    const handleDirectMessage = () => {
        setDirectMessage(!directMessage);
    };

    return (
        <>
            <div
                onClick={handleDirectMessage}
                className="flex items-center w-full h-[65px] text-black tetx-lg font-medium hover:cursor-pointer"
            >
                <img
                    src={image}
                    width={50}
                    height={50}
                />
                <p className="w-full ml-[10px]">{name}</p>
            </div>
        </>
    );
}