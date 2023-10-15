package me.ensa.recyleview;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import me.ensa.recyleview.adapter.StarAdapter;
import me.ensa.recyleview.beans.Star;
import me.ensa.recyleview.service.StarService;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);

        //insérer le code

        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void init() {
        service.create(new Star("leeuw med", "https://images.unsplash.com/photo-1577880216142-8549e9488dad?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDYxOA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 4));
        service.create(new Star("george clooney", "https://images.unsplash.com/photo-1607990281513-2c110a25bd8c?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDMzMQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 3));
        service.create(new Star("michelle rodriguez", "https://images.unsplash.com/photo-1568604032475-10f1a56527c9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDQ0OQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 5));
        service.create(new Star("george clooney", "https://images.unsplash.com/photo-1589386417686-0d34b5903d23?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDQ3MA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 1));
        service.create(new Star("louise bouroin", "https://images.unsplash.com/photo-1499714608240-22fc6ad53fb2?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDQ4OA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 5));
        service.create(new Star("louise bouroin", "https://images.unsplash.com/photo-1558222218-b7b54eede3f3?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDUwMg&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 1));
        service.create(new Star("michelle rodriguez", "https://images.unsplash.com/photo-1568604032475-10f1a56527c9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=500&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY5NzQwMDQ0OQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=300", 5));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new
              SearchView.OnQueryTextListener() {
                  @Override
                  public boolean onQueryTextSubmit(String query) {
                      return true;
                  }
                  @Override
                  public boolean onQueryTextChange(String newText) {
                      Log.d(TAG, newText);
                      if (starAdapter != null){
                          starAdapter.getFilter().filter(newText);
                      }
                      return true;
                  }
              });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}