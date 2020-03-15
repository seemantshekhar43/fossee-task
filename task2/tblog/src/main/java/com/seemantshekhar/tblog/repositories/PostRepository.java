package com.seemantshekhar.tblog.repositories;

import com.seemantshekhar.tblog.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Post Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //Method to find post page by page
    Page<Post> findAll(Pageable pageable);
}
