import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VotePayload } from 'src/app/shared/models/vote-payload';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http: HttpClient) { }

  vote(votePayload: VotePayload): Observable<any> {
    return this.http.post('http://localhost:8098/api/votes/', votePayload);
  }
  votecomment(votePayload: VotePayload): Observable<any> {
    return this.http.post('http://localhost:8098/api/votes/comment', votePayload);
  }
}
