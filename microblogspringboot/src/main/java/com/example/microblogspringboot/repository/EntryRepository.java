package com.example.microblogspringboot.repository;

import com.example.microblogspringboot.domain.Entry;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findAll();
    
}