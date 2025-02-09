package labs.example;

import java.util.List;

public class MangaSummary {
    private String id;

    private List<String> genres;

    private String title;

    private String type;

    private Boolean nsfw;

    private Long total_chapter;

    private String thumb;

    private String sub_title;

    private String summary;

    private Long update_at;

    private Long created_at;

    private String status;

    private List<String> authors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public Long getTotal_chapter() {
        return total_chapter;
    }

    public void setTotal_chapter(Long total_chapter) {
        this.total_chapter = total_chapter;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Long update_at) {
        this.update_at = update_at;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "MangaSummary{" +
                "id='" + id + '\'' +
                ", genres=" + genres +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", nsfw=" + nsfw +
                ", total_chapter=" + total_chapter +
                ", thumb='" + thumb + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", summary='" + summary + '\'' +
                ", update_at=" + update_at +
                ", created_at=" + created_at +
                ", status='" + status + '\'' +
                ", authors=" + authors +
                '}';
    }
}
