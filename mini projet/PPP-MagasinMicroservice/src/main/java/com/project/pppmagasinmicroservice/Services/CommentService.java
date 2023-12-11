package com.project.pppmagasinmicroservice.Services;

import com.project.pppmagasinmicroservice.Entity.Comment;
import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Repositories.CommentRepository;
import com.project.pppmagasinmicroservice.Repositories.PostRepository;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentTO;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentToResponse;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostRequest;
import com.project.pppmagasinmicroservice.Utilis.Exception.CommentNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.Exception.PostNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.Exception.SpringPlateformeException;
import com.project.pppmagasinmicroservice.Utilis.Mappers.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
public class CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepositry;




    public void save(CommentTO commentTO) {
        Post post = postRepository.findById(commentTO.getPostId())
                .orElseThrow(() -> new NotFoundException(commentTO.getPostId().toString()));
        Comment comment = commentMapper.map(commentTO, post);
        commentRepositry.save(comment);


    }
    @Transactional
    public void update(CommentTO commentTO, Long idComment) {
        Comment existingComment= commentRepositry.findById(idComment)
                .orElseThrow(() -> new CommentNotFoundException(idComment));

        Comment updatedPost = commentMapper.mapWithoutPost(commentTO);
        existingComment.setText(updatedPost.getText());


        commentRepositry.save(existingComment);
    }

    public CommentToResponse getComment(Long idComment) {
        Comment comment = commentRepositry.findById(idComment)
                .orElseThrow(() -> new NotFoundException(idComment.toString()));
        return commentMapper.mapToTO(comment);

    }



    public List<CommentToResponse> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(postId.toString()));
        return commentRepositry.findByPost(post)
                .stream()
                .map(commentMapper::mapToTO)
                .collect(toList());
    }


    public void deleteComment(Long id){
        Comment comment = this.commentRepositry.findById(id).orElseThrow(() -> new SpringPlateformeException("comment not found with id -" + id));

        this.commentRepositry.delete(comment);

    }
}

