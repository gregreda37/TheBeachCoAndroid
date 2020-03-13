package com.gregreda.finalprojectfall2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup clothingRadioGroup;
    private Switch webSwitch;
    private Switch appSwitch;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText newScoreTxt;
    TextView highScoreTxt;
    int highScore = 0;
    int newScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clothingRadioGroup = findViewById(R.id.clothingRadioGroup);
        webSwitch = findViewById(R.id.webSwitch);
        appSwitch = findViewById(R.id.appSwitch);
        newScoreTxt = findViewById(R.id.newScoreTextView);
        highScoreTxt = findViewById(R.id.highScoreTextView);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        highScore = sharedPreferences.getInt("high_Score", 0);
        highScoreTxt.setText("Happiness Level: "+ highScore);


    }


    public void presentViewController(View view) {
        Boolean webState = webSwitch.isChecked();
        Boolean appState = appSwitch.isChecked();
        int clothingChoice = clothingRadioGroup.getCheckedRadioButtonId();

        if(webState == true && appState == true ){
            Toast.makeText(this, "Please Select One.", Toast.LENGTH_SHORT).show();

        } else if (webState == true) {
            if (clothingChoice == R.id.sweatshirts) {
                Uri webpage = Uri.parse("https://www.tbeachco.com/product-page/life-guard-red-hoodie-lightweight");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            } else if (clothingChoice == R.id.teeShirts) {
                Uri webpage = Uri.parse("https://www.tbeachco.com/product-page/chalky-mint-pocket-t-shirt");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            } else if (clothingChoice == R.id.longsleeves) {
                Uri webpage = Uri.parse("https://www.tbeachco.com/product-page/tropical-foam-crew-neck-sweater");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            } else {
                Uri webpage = Uri.parse("https://www.tbeachco.com/product-page/denim-blue-quarter-zipper-sweater");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        }
        if(appState == true && webState == false) {
                if (clothingChoice == R.id.sweatshirts) {
                    Intent intent = new Intent(this, Sweatshirt.class);
                    startActivity(intent);

                } else if (clothingChoice == R.id.teeShirts) {
                    Intent intent = new Intent(this, TeeShirt.class);
                    startActivity(intent);

                } else if (clothingChoice == R.id.longsleeves) {
                    Intent intent = new Intent(this, LongSleeve.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, QuarterZip.class);
                    startActivity(intent);
                }
            } else {


        }

        if(newScore > highScore) {
            sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            // sets a default value to avoid a null call
            highScore = sharedPreferences.getInt("high_Score", 0);
            newScore = Integer.parseInt(newScoreTxt.getText().toString());

            //save newScore as high score in shared Pref
            editor = sharedPreferences.edit();
            editor.putInt("high_Score", newScore);
            editor.apply();
            highScoreTxt.setText("Happiness Level: " + newScore);
        }

    }

}
