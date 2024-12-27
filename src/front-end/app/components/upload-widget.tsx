import { useEffect, useRef } from "react";

declare global {
    interface Window {
        cloudinary: any;
    }
}

export default function UploadWidget() {
    const cloudinaryRef = useRef<any>(null);
    const widgetRef = useRef<any>(null);

    useEffect(() => {
        cloudinaryRef.current = window.cloudinary;
        if (cloudinaryRef.current) {
            widgetRef.current = cloudinaryRef.current.createUploadWidget({
                cloudName: process.env.CLOUDINARY_CLOUD_NAME,
                uploadPreset: "logblock",
            });
        }
    });

    return (
        <button
            onClick={() => widgetRef.current.open()}
            className="border border-black z-50"
        >
            Upload
        </button>
    );
}