package com.project.pppmagasinmicroservice.Utilis.Mappers;


import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.project.pppmagasinmicroservice.Entity.Comment;
import com.project.pppmagasinmicroservice.Entity.Post;
import com.project.pppmagasinmicroservice.Repositories.CommentRepository;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentTO;
import com.project.pppmagasinmicroservice.Utilis.DTO.CommentToResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;


@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    private CommentRepository commentRepositry;
    @Mapping(target = "idComment", ignore = true)
    @Mapping(target = "text", source = "commentTO.text")
    @Mapping(target = "post", source = "post")
    public abstract Comment map(CommentTO commentTO, Post post);

    @Mapping(target = "idComment", ignore = true)
    @Mapping(target = "text", source = "commentTO.text")
    @Mapping(target = "post", ignore = true)
    public abstract Comment mapWithoutPost(CommentTO commentTO);


    @Mapping(target = "postId", expression = "java(comment.getPost().getPubId())")
    @Mapping(target = "duration", expression = "java(getDuration(comment))")

    public abstract CommentToResponse mapToTO(Comment comment);
    String getDuration(Comment comment) {
        return TimeAgo.using(Timestamp.valueOf(comment.getCreatedDate()).getTime());
    }



}
