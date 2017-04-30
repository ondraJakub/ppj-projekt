package cz.tul.services;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import cz.tul.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    public void update(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getTag(TagId id){
        return tagRepository.findOne(id);
    }


    public List<Tag> getTagy() {
        List<Tag> obrazky = StreamSupport.stream(tagRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return obrazky;
    }

    public void deleteTagy() {
        tagRepository.deleteAll();
    }

    public void deleteTag(TagId tag) {
        tagRepository.delete(tag);
    }
}
