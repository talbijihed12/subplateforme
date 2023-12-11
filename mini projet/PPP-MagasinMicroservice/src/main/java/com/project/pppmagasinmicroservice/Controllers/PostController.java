package com.project.pppmagasinmicroservice.Controllers;

import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Entity.SearchPosts;
import com.project.pppmagasinmicroservice.Repositories.PostRepository;
import com.project.pppmagasinmicroservice.Services.PostService;
import com.project.pppmagasinmicroservice.Utilis.DTO.MessageResponse;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostRequest;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostResponse;
import com.project.pppmagasinmicroservice.Utilis.Exception.PostNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.FiegnClient.UserFiegnClient;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin( origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Content-Type"})
public class PostController {
    private final PostService postService;
    @Autowired
    PostRepository postRepository;


    @PostMapping("/add")
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {

        postService.save(postRequest);
        return new ResponseEntity(HttpStatus.CREATED);

    }
    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String name) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(name));

    }
    @PutMapping("/update/{pubId}")
    public ResponseEntity<String> updatePost(@PathVariable Long pubId, @RequestBody PostRequest postRequest) {

        try {
            postService.update(postRequest,pubId);
            return ResponseEntity.ok("Post has been updated.");
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }


    @GetMapping("by-subplateforme/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubplateforme(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsBySubplateforme(id));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new MessageResponse("Post deleted successfully!"));
    }
    @PostMapping("/search")
    public ResponseEntity<List<Post>> searchPost(@RequestBody SearchPosts searchPosts) {
        return postService.searchPost(searchPosts);
    }


}
