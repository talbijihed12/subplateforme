import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreatePostPayload } from 'src/app/shared/models/create-post-payload';
import { PostModel } from 'src/app/shared/models/post.model';
import { ModifyPostPayload } from 'src/app/shared/models/modify-post-payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Array<PostModel>> {
    return this.http.get<Array<PostModel>>('http://localhost:8092/posts');
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.http.post('http://localhost:8092/posts/add', postPayload);
  }

  getPost(id: number): Observable<PostModel>{
    return this.http.get<PostModel>('http://localhost:8092/posts/' + id);
  }
  updatePost(id: number, postPayload: ModifyPostPayload): Observable<any> {
    const url = `http://localhost:8092/posts/update/${id}`;
  
    return this.http.put(url, postPayload, { responseType: 'text' });
  }
  

  getAllPostsByUser(name: string): Observable<PostModel[]> {
    return this.http.get<PostModel[]>('http://localhost:8092/posts/by-user/' + name);
  }
  getPostsBySubplateforme(id: number): Observable<PostModel[]> {
    return this.http.get<PostModel[]>('http://localhost:8092/posts/by-subplateforme/' + id)
  }
  deleteComment(id: number) {
    return this.http.delete(
      'http://localhost:8092/posts/delete/' + id
    );
  }
}
