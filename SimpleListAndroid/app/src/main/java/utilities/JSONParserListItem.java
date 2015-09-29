package utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import model.ListItem;

/**
 * Created by csacanam on 28/09/15.
 */
public class JSONParserListItem
{

    public JSONParserListItem()
    {
    }

    public ArrayList<ListItem> getData(String url)
    {
        ArrayList<ListItem> listItems = new ArrayList<>();
        try
        {
            URL listItemsURL = new URL(url);

            URLConnection tc = listItemsURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

            String line;
            while ((line = in.readLine()) != null)
            {
                JSONArray array = new JSONArray(line);

                for (int i = 0; i < array.length(); i++)
                {

                    JSONObject jo = (JSONObject) array.get(i);
                    ListItem listItem = new ListItem(jo.getString("content"));
                    listItems.add(listItem);
                }

            }

        } catch (MalformedURLException e)
        {

        } catch (IOException e)
        {

        } catch (JSONException e)
        {
        }

        return listItems;

    }


}
