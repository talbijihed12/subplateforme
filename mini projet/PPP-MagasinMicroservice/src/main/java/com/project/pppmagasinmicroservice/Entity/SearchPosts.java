package com.project.pppmagasinmicroservice.Entity;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Data
@Setter
@Getter
public class SearchPosts {
    private String postName;
    private String hashTag;
    private String description;

}
