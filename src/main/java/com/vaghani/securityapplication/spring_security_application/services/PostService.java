package com.vaghani.securityapplication.spring_security_application.services;

import com.vaghani.securityapplication.spring_security_application.dto.PostDTO;

import java.util.List;

public interface PostService {


    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    PostDTO createNewPost(PostDTO postDTO);
}
