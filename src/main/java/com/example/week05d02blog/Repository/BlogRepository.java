package com.example.week05d02blog.Repository;

import com.example.week05d02blog.Model.Blog;
import com.example.week05d02blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query("SELECT b FROM blogs b WHERE b.user.id = ?1")
    List<Blog> findAllByUser(Integer userId);

    Blog findBlogById(Integer id);


    Blog findBlogByIdAndUser(Integer id, User user);

    @Query("SELECT b FROM blogs b WHERE b.title = '%' || ?1 || '%' AND b.user.id = ?2")
    List<Blog> findBlogByTitleAndUserId(String title, Integer user_id);
//
//
    @Query("SELECT b FROM blogs b WHERE b.title = '%' || ?1 || '%'")
    List<Blog> findBlogByTitle(String title);

}
