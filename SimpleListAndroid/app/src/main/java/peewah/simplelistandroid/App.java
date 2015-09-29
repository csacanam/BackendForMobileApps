package peewah.simplelistandroid;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by csacanam on 28/09/15.
 */
public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        //Initialize Parse Connection
        Parse.initialize(this, "APPLICATION_ID", "CLIENT_KEY");
    }
}
