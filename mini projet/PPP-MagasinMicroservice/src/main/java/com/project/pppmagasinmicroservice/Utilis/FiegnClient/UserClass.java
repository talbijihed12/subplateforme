package com.project.pppmagasinmicroservice.Utilis.FiegnClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserClass {
    private Long id;
    private String username;
    private String email;
}
