package app.wizzeye.app;

import com.google.gson.annotations.SerializedName;

public class JSONResponse {

    @SerializedName("content")
    private Data[] contents;

    public Data[] getContents() {
        return contents;
    }

    public void setContents(Data[] contents) {
        this.contents = contents;
    }

}
