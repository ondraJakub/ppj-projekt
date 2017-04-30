package cz.tul.services;

import cz.tul.data.Komentar;
import cz.tul.repositories.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */
@Service
public class KomentarService {
    @Autowired
    private KomentarRepository komentarRepository;

    public void create(Komentar komentar) {
        komentarRepository.save(komentar);
    }

    public void update(Komentar komentar) {
        komentarRepository.save(komentar);
    }

    public Komentar getKomentar(Integer id){
        return komentarRepository.findOne(id);
    }

    public void pridejLike(Komentar komentar) {
        komentar.setPocet_likes(komentar.getPocet_likes() + 1);
        update(komentar);
    }

    public void pridejDisLike(Komentar komentar) {
        komentar.setPocet_dislikes(komentar.getPocet_dislikes() + 1);
        update(komentar);
    }

    public List<Komentar> getKomentare() {
        List<Komentar> obrazky = StreamSupport.stream(komentarRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return obrazky;
    }

    public List<Komentar> najdiKomentareObrazku(Integer imageId) {
        if(imageId >= 0) {
            return komentarRepository.najdiKomentareObrazku(imageId);
        }else{
            return null;
        }
    }

    public void deleteKomentare() {
        komentarRepository.deleteAll();
    }

    public void deleteKomentar(Komentar komentar) {
        komentarRepository.delete(komentar);
    }
}
