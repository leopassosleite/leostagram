import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HomeService } from "./home.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private login: HomeService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let authReq = req;
        const token = this.login.getToken();
        console.log("inside interceptor")

        if (token != null) {
            authReq = authReq.clone({
                setHeaders: { Authorization: `Bearer ${token}` },
            });
        }
        return next.handle(authReq);
    }
}

export const authInterceptorProvaiders = [
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true,
    },
];