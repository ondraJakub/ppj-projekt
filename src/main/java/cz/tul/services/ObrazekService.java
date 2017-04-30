package cz.tul.services;

import cz.tul.data.Obrazek;
import cz.tul.data.User;
import cz.tul.repositories.ObrazekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Service
public class ObrazekService {

    @Autowired
    private ObrazekRepository obrazekRepository;

    public void create(Obrazek obrazek) {
        obrazekRepository.save(obrazek);
    }

    public void update(Obrazek obrazek) {
        obrazekRepository.save(obrazek);
    }

    public Obrazek getObrazek(Integer id){
        return obrazekRepository.findOne(id);
    }

    public void pridejLike(Obrazek obrazek) {
        obrazek.setPocet_likes(obrazek.getPocet_likes() + 1);
        update(obrazek);
    }

    public void pridejDisLike(Obrazek obrazek) {
        obrazek.setPocet_dislikes(obrazek.getPocet_dislikes() + 1);
        update(obrazek);
    }

    public List<Obrazek> getObrazky() {
        List<Obrazek> obrazky = StreamSupport.stream(obrazekRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return obrazky;
    }

    public List<Obrazek> najdiPodleAutora(User user){
        if(user != null) {
            return obrazekRepository.najdiPodleAutora(user);
        }else{
            return null;
        }
    }

    public List<Obrazek> najdiPodleNazvu(String name){
        if(name != null){
            return obrazekRepository.najdiPodleNazvu(name);
        }else{
            return null;
        }
    }

    public List<Obrazek> najdiPodleTagu(List<String> tags) {
        if(tags != null) {
            return obrazekRepository.najdiPodleTagu(tags);
        }else{
            return null;
        }
    }

    public void deleteObrazky() {
        obrazekRepository.deleteAll();
    }

    public void deleteObrazek(Obrazek obrazek) {
        obrazekRepository.delete(obrazek);
    }
}
