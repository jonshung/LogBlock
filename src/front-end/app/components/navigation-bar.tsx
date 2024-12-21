import Link from "next/link";

export default function Navigator() {
    return (
        <div className="flex items-center ml-[40px] h-screen w-[50px]">
            <div className="fixed grid items-center gap-[20px]">
                <Link href="/">
                    <img src="https://img.icons8.com/?size=50&id=1iF9PyJ2Thzo&format=png&color=000000"/>
                </Link>
                <Link href="/explore">
                    <img src="https://img.icons8.com/?size=50&id=XU3XKgdpT0qG&format=png&color=C4C4C4C4"/>
                </Link>
                <Link href="/profile">
                    <img src="https://img.icons8.com/?size=50&id=Or3Eb98tGbJL&format=png&color=C4C4C4C4"/>
                </Link>
            </div>
        </div>
    );
}