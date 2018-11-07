import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Comment } from "../../model/comment";

@Injectable({
  providedIn: "root"
})
export class CommentService {
  constructor(private http: HttpClient) {}

  // Ajax request for comment data
  getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>("http://localhost:8081/api/comments");
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>("http://localhost:8081/api/comments", comment);
  }

  deleteComment(commentId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/comments/" + commentId);
  }
}
