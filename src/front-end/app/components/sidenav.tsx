import NavLinks from "@/app/components/nav-links";

export default function SideNav() {
    return (
        <div className="flex items-center ml-[40px] h-screen w-[50px]">
            <div className="fixed grid items-center gap-[20px]">
                <NavLinks />
            </div>
        </div>
    );
}