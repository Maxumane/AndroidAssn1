package ca.bcit.assn1;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondFragment extends AppCompatActivity {

    String dates, search;
    TextView link, title, publishDate, descr, info, src;
    ImageView imageLink;
    public void onCreate(Bundle savedInstanceData) {

        super.onCreate(savedInstanceData);
        setContentView(R.layout.fragment_second);
        imageLink = findViewById(R.id.imgLink);
        publishDate = findViewById(R.id.publishDate);
        info = findViewById(R.id.info);
        link = findViewById(R.id.link);
        title = findViewById(R.id.title);
        src = findViewById(R.id.src);
        descr = findViewById(R.id.descr);
        Intent intent = getIntent();
        dates = intent.getStringExtra("dates");
        search = intent.getStringExtra("search");
        try{
            JSONObject json = new JSONObject(intent.getStringExtra("info"));
            publishDate.setText(json.getString("publishedAt"));
            link.setText(json.getString("url"));
            src.setText(json.getJSONObject("source").getString("name"));
            descr.setText(json.getString("description"));
            title.setText(json.getString("title"));
            info.setText(json.getString("content"));
            String imgLoc = json.getString(("urlToImage"));
            Picasso.get().load(imgLoc).into(imageLink);
        }catch(JSONException e){
            Log.d("Error Occurred", "Error Occurred");
        }
    }

}
