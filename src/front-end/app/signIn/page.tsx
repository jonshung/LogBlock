"use client";

import { useRouter } from "next/navigation";
import { useGoogleLogin } from "@react-oauth/google";

export default function SignIn() {
  const router = useRouter();
  // const googleSignIn = useGoogleLogin({
  //   onSuccess: async (codeResponse) => {
  //     try {
  //       const response = await fetch('/api/auth/google', { // backend endpoint
  //         method: 'POST',
  //         headers: {
  //           'Content-Type': 'application/json',
  //         },
  //         body: JSON.stringify({
  //           code: codeResponse.code, // send the authorization code to the backend
  //         }),
  //       });

  //       if (!response.ok) {
  //         const errorData = await response.json();
  //         throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
  //       }

  //       const data = await response.json();
  //       console.log("data from backend", data);

  //       if (data.success) {
  //         localStorage.setItem('token', data.token);
  //         window.location.href = '/';
  //       } else {
  //         console.error("Login failed on backend:", data.message);
  //       }
  //     } catch (error) {
  //       console.error('Error during login:', error);
  //     }
  //   },
  //   onError: (error) => console.error('Google Login Failed:', error),
  //   flow: 'auth-code',
  // });

  return (
    <main className="flex justify-center items-center w-screen h-screen bg-[#f4f4f4]">
      <div className="flex flex-col items-center justify-center gap-[30px] w-[700px] h-[300px] bg-white rounded-[15px] text-black">
        <p className="text-5xl font-extrabold">Sign in</p>
        <button
          onClick={() => router.push("/")}
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

