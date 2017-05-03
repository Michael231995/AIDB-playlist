package service;

import domain.Music;
import domain.musicRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class musicService {

    @Autowired
    protected musicRep musicRep;

    public List<Music> findAll() {
        return musicRep.findAll();
    }

    public Music save(Music entry) {
        return musicRep.save(entry);
    }

    public void delete(Long id) {
        musicRep.delete(id);
    }

    public Music findOne(Long id) {
        return musicRep.findOne(id);
    }
}
