import NavLinks from "@/app/components/nav-links";

export default function SideNav() {
    return (
        <div className="flex items-center justify-end h-screen w-[90px]">
            <div className="fixed grid items-center gap-[20px]">
                <NavLinks />
            </div>
        </div>
    );
}