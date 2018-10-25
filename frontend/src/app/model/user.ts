export class User {
  id: string;
  username: string;
  password: string;

  static cloneBase(user: User): User {
    let clonedUser: User = new User();
    clonedUser.id = user.id;
    clonedUser.username = user.username;
    clonedUser.password = user.password;
    return clonedUser;
  }
}
