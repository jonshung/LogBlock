import { NextResponse, NextRequest } from 'next/server'
import HttpStatusCode from './app/utils/HTTPStatusCode';
import { fetchAuthorized } from './app/utils/AuthorizationCode';

const USE_AUTH = process.env.ENABLE_AUTH_GUARD;

export async function middleware(request: NextRequest) {
    if(USE_AUTH == "0") {
        return NextResponse.next();
    }

    try {
        let authorization_test = null;
        authorization_test = await fetchAuthorized(`${process.env.BACKEND_HOSTNAME_SERVER}:${process.env.BACKEND_PORT_SERVER}`);
        
        if(authorization_test == null || authorization_test.status == HttpStatusCode.FORBIDDEN_403) {
            return NextResponse.redirect(new URL("/auth", request.nextUrl));
        }
    } catch(error) {
        return NextResponse.redirect(new URL("/auth", request.nextUrl));
    }

    return NextResponse.next();
}

// Routes Middleware should not run on
export const config = {
    matcher: ['/((?!auth|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)'],
}