import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { User } from "../../model/user";

@Injectable({
  providedIn: "root"
})
export class UserService {
  constructor(private http: HttpClient) {}

  // Ajax request for user data
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8081/api/user");
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>("http://localhost:8081/api/user", user);
  }

  deleteUser(userId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/user/" + userId);
  }
}
