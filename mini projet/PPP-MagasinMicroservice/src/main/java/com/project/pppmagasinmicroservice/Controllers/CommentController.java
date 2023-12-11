package com.project.pppmagasinmicroservice.Controllers;


import com.project.pppmagasinmicroservice.Services.CommentService;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentTO;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentToResponse;
import com.project.pppmagasinmicroservice.Utilis.DTO.MessageResponse;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostRequest;
import com.project.pppmagasinmicroservice.Utilis.Exception.CommentNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.Exception.PostNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Content-Type"})
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentTO commentTO) {

        commentService.save(commentTO);
        return new ResponseEntity<>(CREATED);

    }
    @PutMapping("/update/{idComment}")
    public ResponseEntity<String> updatePost(@PathVariable Long idComment, @RequestBody CommentTO commentTO) {

        try {
            commentService.update(commentTO,idComment);
            return ResponseEntity.ok("Comment has been updated.");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{idComment}")
    public ResponseEntity<CommentToResponse> getComment(@PathVariable Long idComment) {
        return status(HttpStatus.OK).body(commentService.getComment(idComment));
    }


    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentToResponse>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(new MessageResponse("Comment deleted successfully!"));
    }
}
