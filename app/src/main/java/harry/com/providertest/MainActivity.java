package harry.com.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ArrayList<String> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
        list.setAdapter(adapter);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText branch = (EditText) findViewById(R.id.branch);

        Button create = (Button) findViewById(R.id.button1);
        final Button read = (Button) findViewById(R.id.button2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editName = name.getText().toString();
                String editCity = city.getText().toString();
                String editBranch = branch.getText().toString();
//                URI uri = UserDictionary.Words.CONTENT_URI;
                ContentValues contentValues = new ContentValues();
                contentValues.put("firstName", editName);
                contentValues.put("lastName", editCity);
                contentValues.put("branch", editBranch);

//                contentValues.put(CustomProvider.uri, 1);
                getContentResolver().insert(Uri.parse("content://com.harry1.CustomProvider/my_provider"), contentValues);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arr = {"_id", "firstName", "lastName", "branch"};
                Cursor cursor = getContentResolver().query(Uri.parse("content://com.harry1.CustomProvider/my_provider"), arr, null, null, null);
                result.clear();

//                cursor.moveToFirst();

//                adapter.clear();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String first1 = cursor.getString(cursor.getColumnIndex("firstName"));
                    String last1 = cursor.getString(cursor.getColumnIndex("lastName"));
                    String branch1 = cursor.getString(cursor.getColumnIndex("branch"));


//                    String s2 = cursor.getString(cursor.getColumnIndex(UserDictionary.Words.FREQUENCY));
                    Log.d("trainin", "inside");
                    result.add(id + " " + first1 + " " + last1 + " " + branch1);
                }
                Log.d("trainin", "outside");

                adapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
