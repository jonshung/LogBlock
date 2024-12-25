"use client";

import { useRouter } from "next/navigation";

export default function SignIn() {
  const router = useRouter();
  return (
    <div className= "container">
      <div className="white-box">
        <div style={{margin : '10px', fontWeight:'bold', fontSize:'35px'}}> Sign in </div>
        <button
          className="button-white"
          onClick={() => router.push("/")}
          onMouseEnter={(e) => (e.currentTarget.style.background = '#f0ede4')}
          onMouseLeave={(e) => (e.currentTarget.style.background = '#ffffff')}
        >
          <img
            src="https://img.icons8.com/?size=100&id=17949&format=png&color=000000"
            alt="Google icon"
            width={20}
            height={20}
          >
          </img>
          <span style={{marginLeft:'10px'}}>
            Continue with Google
          </span> 
        </button>
        <button
          className="button-white"
          onClick={() => router.push("/")}
          onMouseEnter={(e) => (e.currentTarget.style.background = '#f0ede4')}
          onMouseLeave={(e) => (e.currentTarget.style.background = '#ffffff')}
        >
          <img
            src="https://img.icons8.com/?size=100&id=62856&format=png&color=000000"
            alt="Google icon"
            width={20}
            height={20}
          >
          </img>
          <span style={{marginLeft:'10px'}}>
            Continue with Github
          </span> 
        </button>
        
      </div>
      
    </div>
  );
}

