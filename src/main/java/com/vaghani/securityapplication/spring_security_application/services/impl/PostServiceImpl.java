package com.vaghani.securityapplication.spring_security_application.services.impl;

import com.vaghani.securityapplication.spring_security_application.dto.PostDTO;
import com.vaghani.securityapplication.spring_security_application.entities.PostEntity;
import com.vaghani.securityapplication.spring_security_application.exceptions.RequiredFieldMissingException;
import com.vaghani.securityapplication.spring_security_application.exceptions.ResourceNotFoundException;
import com.vaghani.securityapplication.spring_security_application.repositories.PostRepository;
import com.vaghani.securityapplication.spring_security_application.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> allPosts = postRepository.findAll();
        return allPosts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with ID: " + postId + " not found!"));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity toSavePost = modelMapper.map(postDTO, PostEntity.class);
        List<String> subErrors = new ArrayList<>();
        if (toSavePost.getTitle() == null || toSavePost.getTitle().trim() == null) {
            subErrors.add("Title cannot be empty in the post!");
        }
        if (toSavePost.getDescription() == null || toSavePost.getDescription().trim() == null) {
            subErrors.add("Description cannot be empty in the post!");
        }
        if (!subErrors.isEmpty()) {
            throw new RequiredFieldMissingException("Some fields are missing!", subErrors);
        }
        try {
            PostEntity savedPost = postRepository.save(toSavePost);
            log.info("Post created with post id: {}", savedPost.getId());
            return modelMapper.map(savedPost, PostDTO.class);
        } catch (RuntimeException runtimeException) {
            log.info("Exception occured while saving the Post: {}", runtimeException.getMessage());
            throw new RuntimeException("Exception occured while saving the Post, please try again!");
        }
    }
}
