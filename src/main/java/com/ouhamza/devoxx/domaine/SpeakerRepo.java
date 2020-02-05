package com.ouhamza.devoxx.domaine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author Ouhamza
 */

public interface SpeakerRepo extends CrudRepository<Speaker, Long> {

    @RestResource(path = "by-twitter")
    Speaker findByTwitter(@Param("id") String twitter);
    List<Speaker> findByLastName(String lastName);
}
