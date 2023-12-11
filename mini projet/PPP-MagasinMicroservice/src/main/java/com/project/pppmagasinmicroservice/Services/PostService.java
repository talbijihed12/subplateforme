package com.project.pppmagasinmicroservice.Services;

import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Entity.SearchPosts;
import com.project.pppmagasinmicroservice.Entity.Topic;
import com.project.pppmagasinmicroservice.Repositories.ITopicRepo;
import com.project.pppmagasinmicroservice.Repositories.PostRepository;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostRequest;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostResponse;
import com.project.pppmagasinmicroservice.Utilis.Exception.PostNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.Exception.SpringPlateformeException;
import com.project.pppmagasinmicroservice.Utilis.Exception.TopicNotFoundException;
import com.project.pppmagasinmicroservice.Utilis.FiegnClient.UserClass;
import com.project.pppmagasinmicroservice.Utilis.FiegnClient.UserFiegnClient;
import com.project.pppmagasinmicroservice.Utilis.Mappers.PostMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
public class PostService {
    @Autowired
    private ITopicRepo iTopicRepo;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostRepository postRepository;
    private final UserFiegnClient userFiegnClient;

    public PostService(UserFiegnClient userFiegnClient) {
        this.userFiegnClient = userFiegnClient;
    }


    @Transactional
    public void save(PostRequest postRequest) {
        Topic topic = iTopicRepo.findByName(postRequest.getTopic())
                .orElseThrow(() -> new TopicNotFoundException(postRequest.getTopic()));
        postRepository.save(postMapper.map(postRequest, topic));

    }

    @Transactional
    public void update(PostRequest postRequest,Long pubId) {
        Post existingPost = postRepository.findById(pubId)
                .orElseThrow(() -> new PostNotFoundException(pubId));

        Post updatedPost = postMapper.mapWithOutTopic(postRequest);
        existingPost.setPostName(updatedPost.getPostName());
        existingPost.setDescription(updatedPost.getDescription());
        existingPost.setHashTag(updatedPost.getHashTag());

        postRepository.save(existingPost);
    }


    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString()));
        return postMapper.mapToTO(post);

    }
    @Transactional(readOnly = true)

    public ResponseEntity<List<Post>> searchPost(SearchPosts searchPosts) {

        try {
            List productDetails = postRepository.findAll()
                    .stream()
                    .filter(showModel -> Objects.equals(searchPosts.getPostName(), showModel.getPostName()) ||
                            Objects.equals(searchPosts.getDescription(), showModel.getDescription()) ||
                            Objects.equals(searchPosts.getHashTag(), showModel.getHashTag())

                    )
                    .limit(5).collect(Collectors.toList());


            return ResponseEntity.status(HttpStatus.OK).body(productDetails);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToTO)
                .collect(toList());
    }


    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubplateforme(Long subplateformeId) {
        Topic subplateforme = iTopicRepo.findById(subplateformeId)
                .orElseThrow(() -> new TopicNotFoundException(subplateformeId.toString()));
        List<Post> posts = postRepository.findAllByTopic(subplateforme);
        return posts
                .stream()
                .map(postMapper::mapToTO)
                .collect(toList());
    }



    public void deletePost(Long id){
        Post post = this.postRepository.findById(id).orElseThrow(() -> new SpringPlateformeException("post not found with id -" + id));
        this.postRepository.delete(post);

    }
    public String getUserByusername(String username) {
        return userFiegnClient.getUserByUsername(username).getUsername();
    }
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        String user= userFiegnClient.getUserByUsername(username).getUsername();
        return postRepository.findByUsername(user);
    }


}
