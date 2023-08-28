package com.example.week05d02blog.Service;

import com.example.week05d02blog.Api.ApiException;
import com.example.week05d02blog.Model.Blog;
import com.example.week05d02blog.Model.User;
import com.example.week05d02blog.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;


    // show all blogs for all authenticated users
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }



    // show my blogs
    public List<Blog> findMyBlogs(User user) {
        return blogRepository.findAllByUser(user.getId());
    }

    public Blog findOwnById(Integer id, User user) {
        Blog blog = blogRepository.findBlogByIdAndUser(id, user);

        if (blog == null) {
            throw new ApiException("blog not found.");
        }

        return blog;
    }

    public Blog findAnyById(Integer id) {
        Blog blog = blogRepository.findBlogById(id);

        if (blog == null) {
            throw new ApiException("blog not found.");
        }

        return blog;
    }

    public List<Blog> findByTitle(String title, User user) {
        List<Blog> blogs = blogRepository.findBlogByTitleAndUserId(title, user.getId());

        if (blogs.isEmpty()) {
            throw new ApiException("blog not found.");
        }

        return blogs;
    }

    public List<Blog> findByTitle(String title) {
        List<Blog> blogs = blogRepository.findBlogByTitle(title);

        if (blogs.isEmpty()) {
            throw new ApiException("blog not found.");
        }

        return blogs;
    }


    public Blog addBlog(Blog blog, User user) {
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Integer id, Blog updatedBlog, User user) {
        Blog blog = blogRepository.findBlogByIdAndUser(id, user);

        if (blog == null) {
            throw new ApiException("blog not found.");
        }

        blog.setBody(updatedBlog.getBody());
        blog.setTitle(updatedBlog.getTitle());

        blogRepository.save(blog);

        return blog;
    }

    public Blog deleteBlog(Integer id, User user) {
        Blog blog = blogRepository.findBlogByIdAndUser(id, user);

        if (blog == null) {
            throw new ApiException("blog not found.");
        }

        blogRepository.deleteById(id);

        return blog;
    }
}
