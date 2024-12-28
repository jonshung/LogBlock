"use client";

import ConnectorsCard from "@/app/components/profile/connectors-card";
import { useState } from "react";

export function ProfileComponent({ name, bio, connectors }: { name: string, bio: string, connectors: number }) {
    const connected = 49;
    const profileImage = "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/chocoCaro_mmmpkq.png";

    const connectorCards = [
        { name: "Henry Clauss", connectors: 53, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Henry_Clauss_hq9ijp.png" },
        { name: "David John", connectors: 1053, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/David_John_rnrdui.png" },
        { name: "Oliver Mia", connectors: 526, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/Oliver_Mia_lcqgjv.png" },
        { name: "khi nào giỏi code thì đổi tên", connectors: 7, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/khi_n%C3%A0o_gi%E1%BB%8Fi_code_th%C3%AC_%C4%91%E1%BB%95i_t%C3%AAn_fucvla.png" },
        { name: "Jack Ma", connectors: 2000000, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Jack_Ma_urpn7f.png" },
        { name: "Study with me | Layla", connectors: 274, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Study_with_me_Layla_pkowwj.png" },
        { name: "Ben Foster", connectors: 20, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/Ben_Foster_ffnulo.png" },
        { name: "code là đam mê", connectors: 31, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769388/code_l%C3%A0_%C4%91am_m%C3%AA_k5bnj7.png" },
        { name: "Karim Benzema", connectors: 580218, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769389/Karim_Benema_zxqx1f.png" },
        { name: "Vô danh", connectors: 0, image: "https://res.cloudinary.com/dumr9ghyv/image/upload/v1734769387/V%C3%B4_danh_bfb5gs.png" }
    ];

    const [editDialogue, setEditDialogue] = useState(false);
    const [connectorsDialogue, setConnectorsDialogue] = useState(false);
    const [connectedDialogue, setConnectedDialogue] = useState(false);

    const toggleUpEdit = () => {
        setEditDialogue(!editDialogue);
    }

    const handleEditDone = () => {
        setEditDialogue(!editDialogue);
    }

    const toggleUpConnectors = () => {
        setConnectorsDialogue(!connectorsDialogue);
        setConnectedDialogue(false);
    }

    const switchConnectedDialogue = () => {
        setConnectedDialogue(!connectedDialogue);
    }

    return (
        <>
            <div className="w-full px-[25px] py-[20px] bg-[#f4f4f4] rounded-[15px]">
                <div className="flex">
                    <img
                        src={profileImage}
                        width={100}
                        height={100}
                    />
                    <span className="w-[20px]" />
                    <div className="grid grid-rows-3 items-center h-[100px] text-black">
                        <p className="text-3xl font-medium">{name}</p>
                        <p
                            onClick={toggleUpConnectors}
                            className="hover:underline cursor-pointer"
                        >
                            {connectors} connectors
                        </p>
                        <p className="text-lg font-medium">{bio}</p>
                    </div>
                </div>
                <button
                    onClick={toggleUpEdit}
                    className="w-full h-[30px] mt-[20px] text-black border border-black rounded-[15px]"
                >
                    Edit profile
                </button>
            </div>

            {editDialogue && (
                <div className="fixed flex items-center justify-center h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
                    <div className="w-[600px] bg-white rounded-[15px]">
                        <div className="flex items-center justify-between h-[50px] mx-[10px] font-bold">
                            <p
                                onClick={toggleUpEdit}
                                className="text-lg text-[#b0b0b0] hover:cursor-pointer"
                            >
                                Cancel
                            </p>
                            <p className="text-[1.625rem] text-black">Edit profile</p>
                            <p
                                onClick={handleEditDone}
                                className="text-lg text-[#0195f7] hover:cursor-pointer"
                            >
                                Done
                            </p>
                        </div>
                        <hr className="border border-[#b0b0b0]" />
                        <div className="flex flex-col mx-[25px] my-[15px] gap-[10px] text-black">
                            <div className="flex">
                                <div className="w-full">
                                    <p className="text-[1.1875rem] font-bold">Name</p>
                                    <textarea
                                        name="name"
                                        defaultValue={name}
                                        className="w-full px-[15px] py-[10px] border border-[#b0b0b0] rounded-[15px] resize-none"
                                    />
                                </div>
                                <span className="w-[30px]" />
                                <img
                                    src={profileImage}
                                    width={100}
                                    height={100}
                                    onClick={() => { }}
                                    className="hover:cursor-pointer"
                                />
                            </div>
                            <div>
                                <p className="text-[1.1875rem] font-bold">Bio</p>
                                <textarea
                                    name="bio"
                                    defaultValue={bio}
                                    className="w-full px-[15px] py-[10px] border border-[#b0b0b0] rounded-[15px] resize-none"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            )}

            {connectorsDialogue && (
                <div className="fixed flex items-center justify-center h-screen w-screen left-0 top-0 bg-[#a6a6a6] bg-opacity-70 z-50">
                    <div className="w-[600px] bg-white rounded-[15px]">
                        <div className="flex h-[100px]">
                            <div
                                onClick={switchConnectedDialogue}
                                className={connectedDialogue ? (
                                    "flex flex-col items-center justify-center w-full text-[#a4a4a4] hover:cursor-pointer"
                                ) : (
                                    "flex flex-col items-center justify-center w-full text-black hover:cursor-pointer"
                                )}
                            >
                                <p className="text-[1.625rem] font-bold">Connectors</p>
                                <p className="text-xl font-medium">{connectors}</p>
                            </div>
                            <div
                                onClick={switchConnectedDialogue}
                                className={connectedDialogue ? (
                                    "flex flex-col items-center justify-center w-full text-black hover:cursor-pointer"
                                ) : (
                                    "flex flex-col items-center justify-center w-full text-[#a4a4a4] hover:cursor-pointer"
                                )}
                            >
                                <p className="text-[1.625rem] font-bold">Connected</p>
                                <p className="text-xl font-medium">{connected}</p>

                            </div>
                        </div>
                        <div className="flex">
                            <hr className={connectedDialogue ? (
                                "w-full border border-[#a4a4a4]"
                            ) : (
                                "w-full border border-black"
                            )} />
                            <hr className={connectedDialogue ? (
                                "w-full border border-black"
                            ) : (
                                "w-full border border-[#a4a4a4]"
                            )} />
                        </div>
                        <div className="px-[25px] py-[10px]">
                            {connectorCards.map((card, index) => {
                                return (
                                    <>
                                        <ConnectorsCard key={index} name={card.name} connectors={card.connectors} image={card.image} />
                                        {index !== connectorCards.length - 1 && <hr className="border border-[#b0b0b0]-100" />}
                                    </>
                                );
                            })}
                        </div>
                    </div>
                    <button
                        onClick={toggleUpConnectors}
                        className=" absolute top-[30px] right-[30px] text-xl text-white"
                    >
                        x
                    </button>
                </div>
            )}
        </>
    );
}