package Library.Dto.java.DTOLibraryItems;

import java.time.LocalDate;

public class MusicItem extends AudioMediaItem{
    private MusicArtist artist;
    private String album;

    public MusicItem(String title, String description, LocalDate publishDate, int runtime, MusicArtist artist, String album) {
        super(title, description, publishDate, runtime);
        this.artist = artist;
        this.album = album;
    }

    public Author getArtist() {
        return artist;
    }

    public void setArtist(MusicArtist artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
