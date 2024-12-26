export default function SignIn() {
  return (
    <main className="flex justify-center items-center w-screen h-screen bg-[#f4f4f4]">
      <div className="flex flex-col items-center justify-center gap-[30px] w-[700px] h-[300px] bg-white rounded-[15px] text-black">
        <p className="text-5xl font-extrabold">Sign in</p>
        <button
          className="flex items-center justify-center w-[500px] h-[55px] hover:bg-[#f9f9f9] border border-black rounded-[30px] text-xl font-medium"
        >
          <img src="https://img.icons8.com/?size=35&id=17949&format=png&color=000000" />
          <span className="w-[6px]" />
          <p>Continue with Google</p>
        </button>
      </div>
    </main>
  );
}

