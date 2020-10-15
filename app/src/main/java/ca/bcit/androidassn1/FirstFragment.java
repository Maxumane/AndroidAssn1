package ca.bcit.assn1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class FirstFragment extends AppCompatActivity {
    String search;
    String date;


    ArrayList<JSONObject> articleAr = new ArrayList();

    ArrayList<String> titleAr = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        Intent intent = getIntent();
        final ListView list = findViewById(R.id.list);
        search = intent.getStringExtra("user_text");
        date = intent.getStringExtra("date");
        Result(list);
        final Intent intentSecond = new Intent(this, SecondFragment.class);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                intentSecond.putExtra("info", articleAr.get(pos).toString());
                intentSecond.putExtra("search", search);
                intentSecond.putExtra("dates", date);
                startActivity(intentSecond);
            }
        });

    }

    public void Result(final ListView listView){
        String loc = "https://newsapi.org/v2/everything?q=" + search +"&from=" + date +
                "&sortBy=publishedAt&apiKey=b09c82ffb5e04665a865932eb6f61af6";
        RequestQueue req = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, loc, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject res) {
                        JSONArray jsonRes = res.optJSONArray("articles");
                        for (int x = 0; x < jsonRes.length(); x++) {
                            try {
                                titleAr.add(jsonRes.getJSONObject(x).getString("title"));
                                articleAr.add(jsonRes.getJSONObject(x));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<String> arrAdapt = new ArrayAdapter<>(FirstFragment.super.getApplicationContext(),
                                android.R.layout.simple_list_item_1, titleAr);
                        listView.setAdapter(arrAdapt);
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("Error occured", "Errors may have been detected");
            }
        });
        req.add(jsonObjReq);
    }

}
