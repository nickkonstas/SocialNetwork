package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.dto.SearchResult;
import com.udemy.socialnetwork.model.entity.Profile;
import com.udemy.socialnetwork.model.repository.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private ProfileDao profileDao;

    @Value("${search.results.pagesize}")
    private int pageSize;

    public Page<SearchResult> search(String text, int pageNumber) {

        //pageNumber - 1 because it's zero based
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        Page<Profile> results =  profileDao.findByInterestsNameContainingIgnoreCase(text, pageRequest);


        return results.map(p -> new SearchResult(p));
    }
}
