package mobile.handygestures.main;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import mobile.handygestures.Preview;
import mobile.handygestures.R;
import mobile.handygestures.auth.SignInActivity;

public class Menu extends AppCompatActivity {
    // The menu activity handles the start menu with buttons to other activities and the quit option. This is the starting activity.
    Button galleryButton;
    Button helpButton;
    Button quitButton;
    Button previewButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        firebaseAuth = FirebaseAuth.getInstance();


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


                new AlertDialog.Builder(Menu.this)
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Menu.this.finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                /*finish();
                System.exit(0);*/

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
        if (R.id.help == item.getItemId()) {
            Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (R.id.logout == item.getItemId()) {
            firebaseAuth.signOut();
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Menu.this, SignInActivity.class));
            finish();
            return true;
        }
        else if (R.id.share == item.getItemId()){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"My Doctor");
            startActivity(Intent.createChooser(intent,"Choose One!"));
        }
        else if (R.id.feedback == item.getItemId()){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/email");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pkibaki081@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            intent.putExtra(Intent.EXTRA_TEXT,"there is an issue");
            startActivity(Intent.createChooser(intent, "Send Feedback"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Menu.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
