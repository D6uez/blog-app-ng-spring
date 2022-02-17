package io.david.springblogbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.david.springblogbackend.models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
