import { UserInfo } from "./userInfo";
import { Role } from "./role";

export class User {
  id: string;
  username: string;
  password: string;
  role: Role;
  userInfo: UserInfo;

  static cloneBase(user: User): User {
    let clonedUser: User = new User();
    clonedUser.id = user.id;
    clonedUser.username = user.username;
    clonedUser.password = user.password;
    clonedUser.role = user.role;
    clonedUser.userInfo = user.userInfo;
    return clonedUser;
  }
}
