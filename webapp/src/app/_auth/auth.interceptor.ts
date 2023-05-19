import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {UserAuthService} from "../services/user-auth.service";
import {Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(private userAuthService: UserAuthService, private router:Router) {
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(req.headers.get("No-Auth") === 'True'){
      return next.handle(req.clone());
    }
    //we want to add the jwt token to header for each request if user is logged in
    const token = this.userAuthService.getToken();

    //take this token and add it to the header if it's not null
    if(token){
      req = this.addToken(req, token);
    }


    return next.handle(req).pipe(
      catchError(
        (err:HttpErrorResponse) => {
          console.log(err.status);
          if(err.status ===401){
            this.router.navigate(['/login'])
          } else if(err.status === 403){
            this.router.navigate(['/forbidden']);
          }
          return throwError("Something went wrong");
        }
      )
    );

  }

  private addToken(request:HttpRequest<any>, token:string){
    return request.clone(
      {
        setHeaders:{
          Authorization : `Bearer ${token}`
        }
      }
    );
  }

}
