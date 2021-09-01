package mobile.handygestures;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    // The menu activity handles the start menu with buttons to other activities and the quit option. This is the starting activity.
    Button galleryButton;
    Button helpButton;
    Button quitButton;
    Button previewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        galleryButton = (Button) findViewById(R.id.button_gallery);
        galleryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Gallery.class));

            }
        });
        // make help button
        /*helpButton = (Button) findViewById(R.id.button_help);
        helpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Help.class));

            }
        });*/
        //make quit button
        quitButton = (Button) findViewById(R.id.button_quit);
        quitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });

        // make preview button
        previewButton = (Button) findViewById(R.id.button_preview);
        previewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Preview.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.item1 == item.getItemId()) {
            Toast.makeText(this, "Item I Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
