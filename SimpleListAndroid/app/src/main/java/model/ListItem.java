package model;

import android.os.Parcel;


/**
 * Created by csacanam on 28/09/15.
 */
public class ListItem
{

    private String content;

    public ListItem(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return content;
    }
}
