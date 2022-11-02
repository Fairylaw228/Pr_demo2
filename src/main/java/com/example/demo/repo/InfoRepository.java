package com.example.demo.repo;

import com.example.demo.models.InfoPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InfoRepository extends CrudRepository<InfoPost, Long> {

    List<InfoPost> findByNameContains(String name);
    List<InfoPost> findByName(String name);

}