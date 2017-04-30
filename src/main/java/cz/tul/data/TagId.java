package cz.tul.data;

import java.io.Serializable;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */
public class TagId implements Serializable {
    String titulek;
    Integer obrazekId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagId tagId = (TagId) o;

        if (titulek != null ? !titulek.equals(tagId.titulek) : tagId.titulek != null) return false;
        return obrazekId != null ? obrazekId.equals(tagId.obrazekId) : tagId.obrazekId == null;

    }

    @Override
    public int hashCode() {
        int result = titulek != null ? titulek.hashCode() : 0;
        result = 31 * result + (obrazekId != null ? obrazekId.hashCode() : 0);
        return result;
    }
}
