package com.ouhamza.devoxx.web;

import com.ouhamza.devoxx.domaine.Speaker;
import com.ouhamza.devoxx.domaine.SpeakerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

/**
 * @author Lhouceine OUHAMZA
 */
@Controller
public class SpeackerController {

    @Autowired
    private SpeakerRepo speakerRepo;

    @RequestMapping("/ui/speaker/{twiter}")
    public String getSpeacker(@PathVariable String twiter, Model model){

        Speaker speaker =  speakerRepo.findByTwitter(twiter);
        if(speaker ==null){
            throw new SpeakersNotFoundException();
        }
        model.addAttribute("speaker", speaker);
        return "speakers/show.html";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class SpeakersNotFoundException extends RuntimeException{

    }
}
