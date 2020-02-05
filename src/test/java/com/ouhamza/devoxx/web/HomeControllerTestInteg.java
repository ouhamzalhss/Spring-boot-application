package com.ouhamza.devoxx.web;

import com.ouhamza.devoxx.domaine.Speaker;
import com.ouhamza.devoxx.domaine.SpeakerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTestInteg {

    @Autowired
    private SpeakerRepo speakerRepo;


    @LocalServerPort
    private int port;

    @Test
    public void testFindByTwitter() throws Exception{
        Speaker speaker = speakerRepo.save(new Speaker("ouhamza","Lhouceine","mytwitter"));
        assertEquals(speakerRepo.findByTwitter("mytwitter").getId(),speaker.getId());
    }

    @Test
    public void homeTest(){
        String url = "http://localhost:"+port+"/";
        //String body = new RestTemplate().getForObject(url,String.class);
        String body = new TestRestTemplate("hero","hero").getForObject(url,String.class);
        assertEquals(body,"Hello devoxx");
    }
}