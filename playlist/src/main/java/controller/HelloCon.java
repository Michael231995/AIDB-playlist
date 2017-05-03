package controller;

import domain.Music;
import service.musicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HelloCon {

    @Autowired
    protected musicService musicService;

    private static final String music_playlist = "music_playlist";
    private static final String ENTRIES_KEY = "entries";
    private static final String FORM_KEY = "form";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayPlaylist( Model model ) {

        model.addAttribute(ENTRIES_KEY, musicService.findAll());
        model.addAttribute(FORM_KEY, new Music());

        return music_playlist;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String changePlayist(
            Model model,
            @Valid @ModelAttribute(FORM_KEY) Music form,
            BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {
            model.addAttribute(ENTRIES_KEY, musicService.findAll());
            return music_playlist;
        }

        musicService.save(form);

        return "redirect:/";
    }


    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public String deleteEntry (Model model, @PathVariable Long id) {

        musicService.delete (id);

        return "redirect:/";
    }


    @RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
    public String editEntry (Model model, @PathVariable Long id) {
        model.addAttribute (FORM_KEY, musicService.findOne (id));
        return music_playlist;
    }

    @RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
    public String editSavePlaylist (Model model,
                                    @PathVariable Long id,
                                    @Valid @ModelAttribute(FORM_KEY) Music form,
                                    BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {
            model.addAttribute(ENTRIES_KEY, musicService.findAll());
            return music_playlist;
        }

        Music existing = musicService.findOne (id);
        existing.setSongName (form.getSongName());
        existing.setArtistName(form.getArtistName());
        existing.setDuration(form.getDuration());
        musicService.save (existing);

        return "redirect:/";
    }



}
