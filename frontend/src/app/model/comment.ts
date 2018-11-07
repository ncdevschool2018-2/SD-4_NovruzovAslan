import {Product} from "./product";
import {User} from "./user";

export class Comment {
  id: string;
  product: Product;
  user: User;
  comment: string;
  date: Date;

  static cloneBase(comment: Comment): Comment {
    let clonedComment: Comment = new Comment();
    clonedComment.id = comment.id;
    clonedComment.product = comment.product;
    clonedComment.user = comment.user;
    clonedComment.comment = comment.comment;
    clonedComment.date = comment.date;
    return clonedComment;
  }
}
