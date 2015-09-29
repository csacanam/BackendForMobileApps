package utilities;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.ListItem;
import peewah.simplelistandroid.R;

/**
 * Created by csacanam on 28/09/15.
 */
public class ListItemAdapter extends ArrayAdapter<ListItem>
{

    private Activity context;
    private ArrayList<ListItem> listItems;

    public ListItemAdapter(Context context, int resource, ArrayList<ListItem> objects)
    {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.listItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View item = convertView;
        ViewHolder holder;

        if(item == null)
        {
            //Inflate view
            item = context.getLayoutInflater().inflate(R.layout.list_item, null);

            //Create holder and set the value of the TextView
            holder = new ViewHolder();
            holder.setTvContent((TextView) item.findViewById(R.id.tvList));

            //Add the holder to the view as a tag
            item.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) item.getTag();
        }

        //Get ListItem
        ListItem listItem = listItems.get(position);

        //Show in the UI
        holder.getTvContent().setText(listItem.getContent());

        return item;
    }

    /**
     * Class ViewHolder to recycle the views in the ListView
     */
    public class ViewHolder
    {
        private TextView tvContent;

        public TextView getTvContent()
        {
            return tvContent;
        }

        public void setTvContent(TextView tvContent)
        {
            this.tvContent = tvContent;
        }
    }
}
