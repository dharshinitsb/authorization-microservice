package com.cts.spannerdemo.repo;

import com.cts.spannerdemo.model.customer;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends SpannerRepository<customer,Integer> {
    customer findByUsername(String userName);
    // customer findByUserName(String userName);
}
