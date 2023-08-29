package com.example.week05d02blog.Controller;


import com.example.week05d02blog.Api.ApiResponseWithData;
import com.example.week05d02blog.DTO.UserDTO;
import com.example.week05d02blog.Model.Blog;
import com.example.week05d02blog.Model.User;
import com.example.week05d02blog.Service.AuthService;
import com.example.week05d02blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class UserBlogsController {

    private final BlogService blogService;


    // show all blogs only to authenticated users
    @GetMapping("/get")
    public ResponseEntity<List<Blog>> findAllBlogs() {
        return ResponseEntity.ok(blogService.findAll());
    }


    // show only my own blogs
    @GetMapping("/my-blogs")
    public ResponseEntity<List<Blog>> findMyBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogService.findMyBlogs(user));
    }


    // search for any blog
    @GetMapping("/any/{id}")
    public ResponseEntity<Blog> findAnyById(@PathVariable Integer id) {
        return ResponseEntity.ok(blogService.findAnyById(id));
    }


    // search for blog that's written by me
    @GetMapping("/my-blogs/id/{id}")
    public ResponseEntity<Blog> findMyById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogService.findOwnById(id, user));
    }


    // search for blogs by title [only authenticated users]
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Blog>> findMyByTitle(@PathVariable String title) {
        return ResponseEntity.ok(blogService.findByTitle(title));
    }


    // search for blogs by title [only authenticated users]
    @GetMapping("/search/global/title/{title}")
    public ResponseEntity<List<Blog>> findByTitle(@PathVariable String title, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogService.findByTitle(title, user));
    }



    @PostMapping("/add")
    public ResponseEntity<ApiResponseWithData<Blog>> addBlog(@RequestBody @Valid Blog blog, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithData<>("the blog have been created.", blogService.addBlog(blog, user))));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseWithData<Blog>> updateBlog(@PathVariable Integer id, @RequestBody @Valid Blog blog, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((new ApiResponseWithData<>("the blog have been updated.", blogService.updateBlog(id, blog, user))));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseWithData<Blog>> deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok((new ApiResponseWithData<>("the blog have been deleted.", blogService.deleteBlog(id, user))));
    }

}

