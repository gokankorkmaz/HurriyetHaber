package kodluyoruz.com.hurriyethaber.Model;


public class FavoriModel {
    private String Title;
    private String Description;
    private String link;
    private String id;


    public FavoriModel(String id, String title, String description, String link) {
        Title = title;
        Description = description;
        this.link = link;
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
