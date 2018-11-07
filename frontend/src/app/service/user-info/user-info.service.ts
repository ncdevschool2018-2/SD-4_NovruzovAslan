import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { UserInfo } from "../../model/userInfo";

@Injectable({
  providedIn: "root"
})
export class UserInfoService {
  constructor(private http: HttpClient) {}

  // Ajax request for userInfo data
  getUserInfos(): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>("http://localhost:8081/api/userinfos");
  }

  saveUserInfo(userInfo: UserInfo): Observable<UserInfo> {
    return this.http.post<UserInfo>("http://localhost:8081/api/userinfos", userInfo);
  }

  deleteUserInfo(userInfoId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/userinfos/" + userInfoId);
  }
}
