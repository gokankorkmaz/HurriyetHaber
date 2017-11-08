package kodluyoruz.com.hurriyethaber.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoViewModel {
    @SerializedName("Id")
    @Expose
    private String id;


    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Files")
    @Expose
    private List<File> files = null;


    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Url")
    @Expose
    private String url;

    public String getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public List<File> getFiles() {
        return files;
    }


    public String getTitle() {
        return title;
    }


    public class File {

        @SerializedName("FileUrl")
        @Expose
        private String fileUrl;

        public String getFileUrl() {
            return fileUrl;
        }

    }


}
