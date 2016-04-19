package testapp.com.youpod;
public class VideoItem
{
    private String title;
    private String description;
    private String id;

    public VideoItem(String id, String title)
    {
        this.id = id;
        this.title = title;
        this.description = "";
    }

    public String getId() {
        return id;
    }
    public String getTitle() {return title; }
    public String getDescription() {
        return description;
    }

}