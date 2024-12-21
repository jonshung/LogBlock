export default function Header({ pageName }: { pageName: string }) {
    return (
        <div className="fixed flex items-center text-black bg-white h-[90px] w-screen z-50">
            <h1 className="absolute ml-[40px]">LogBlock</h1>
            <h1 className="mx-auto text-2xl font-bold">{pageName}</h1>
        </div>
    );
}
