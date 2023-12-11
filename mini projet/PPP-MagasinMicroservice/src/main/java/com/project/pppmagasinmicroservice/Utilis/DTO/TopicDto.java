package com.project.pppmagasinmicroservice.Utilis.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {
    private long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
