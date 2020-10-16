package ca.bcit.assn1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    EditText searchBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         searchBox = findViewById(R.id.searchBox);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSearchEntry(View v) {
        Intent i = new Intent(this, FirstFragment.class);
        Date timeSearched = new Date();
        if (!(searchBox.getText().toString().trim().length() == 0)) {
            i.putExtra("searchQuery", searchBox.getText().toString());
            i.putExtra("date", timeSearched.toString());
            startActivity(i);
        } else {
            String errEmptyText = "You must enter a value into the search box before searching.";
            Toast errEmptyTextToast = Toast.makeText(this, errEmptyText, Toast.LENGTH_SHORT);
            errEmptyTextToast.show();
        }

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
