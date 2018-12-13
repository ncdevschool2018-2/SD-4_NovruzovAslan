import {User} from "./user";

export class UserInfo {
  id: string;
  name: string;
  // user: User;
  dateOfBirth: Date;


  static cloneBase(userInfo: UserInfo): UserInfo {
    let clonedUserInfo: UserInfo = new UserInfo();
    clonedUserInfo.id = userInfo.id;
    // clonedUserInfo.user = userInfo.user;
    clonedUserInfo.name = userInfo.name;
    clonedUserInfo.dateOfBirth = userInfo.dateOfBirth;
    return clonedUserInfo;
  }
}
