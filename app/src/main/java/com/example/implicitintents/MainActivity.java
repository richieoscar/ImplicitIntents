package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_editText);
        mLocationEditText = findViewById(R.id.location_editText);
        mShareMessageText = findViewById(R.id.share_message);
    }

    public void openWebsite(View view) {
        //Get the URL text
        String url = mWebsiteEditText.getText().toString();

        //parse the the Url to Uri
        Uri webPage = Uri.parse(url);

        //put the Uri into intent
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

        //find an activity that can handle this intent and start the activity
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "cant handle this");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri  addressUri = Uri.parse("geo:0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntent", "cant handle this intent!");
        }
    }

    public void shareMessage(View view) {
        String text = mShareMessageText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.chooser_tittle_text)
                .setText(text)
                .startChooser();
    }
}