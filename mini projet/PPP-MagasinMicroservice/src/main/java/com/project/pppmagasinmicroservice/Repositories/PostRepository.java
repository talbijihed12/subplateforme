package com.project.pppmagasinmicroservice.Repositories;

import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Entity.Topic;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostResponse;
import com.project.pppmagasinmicroservice.Utilis.FiegnClient.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTopic(Topic topic);



    List<PostResponse> findByUsername(String userJson);
}
