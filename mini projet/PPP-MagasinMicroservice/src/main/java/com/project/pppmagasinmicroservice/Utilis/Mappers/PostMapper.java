package com.project.pppmagasinmicroservice.Utilis.Mappers;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Entity.Topic;
import com.project.pppmagasinmicroservice.Repositories.CommentRepository;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostRequest;
import com.project.pppmagasinmicroservice.Utilis.DTO.PostResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;



    @Mapping(target = "topic", source = "topic")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "hashTag", source = "postRequest.hashtag")
    @Mapping(target = "username" ,ignore = true)
    public abstract Post map(PostRequest postRequest, Topic topic);



    @Mapping(target = "topic",  ignore = true)
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "hashTag", source = "postRequest.hashtag")
    public abstract Post mapWithOutTopic(PostRequest postRequest);





    @Mapping(target = "id", source = "pubId")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "hashTag", source = "hashTag")
    @Mapping(target = "topic", source = "topic.name")
    @Mapping(target = "username", source = "post.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToTO(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(Timestamp.valueOf(post.getCreatedDate()).getTime());
    }


}
