package com.android.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.view.View;
import android.net.Uri;

import android.widget.RelativeLayout.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.*;

public class MainActivity extends Activity {

    //Only if you have changed MainActivity to yours and you wanna call game's activity.
    public String GameActivity = "com.unity3d.player.UnityPlayerActivity";
    public boolean hasLaunched = false;
    public String iv = new String(new byte[]{97,110,100,114,111,105,100,46,105,110,116,101,110,116,46,97,99,116,105,111,110,46,86,73,69,87,}); // android.intent.action.VIEW
    String thbctc = new String(new byte[]{84,101,120,116,32,104,97,115,32,98,101,101,110,32,99,111,112,105,101,100,32,116,111,32,99,108,105,112,98,111,97,114,100,});  // Text has been copied to clipboard
    //To call onCreate, please refer to README.md
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView FW = findViewById(R.id.floatingWindow);
        FW.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View p1) {
                    ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", FW.getText()));
                    Toast.makeText(getApplication(), thbctc, Toast.LENGTH_SHORT).show();
                }
            });
		final TextView PM = findViewById(R.id.permission);
        PM.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View p1) {
                    ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", PM.getText()));
                    Toast.makeText(getApplication(), thbctc, Toast.LENGTH_SHORT).show();
                }
            });
		final TextView SV = findViewById(R.id.service);
		SV.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View p1) {
                    ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", SV.getText()));
                    Toast.makeText(getApplication(), thbctc, Toast.LENGTH_SHORT).show();
                }
            });
		
        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread thread, Throwable e) {
                        Log.e("AppCrash", "Error just lunched ");
                    }
                });


        //To launch game activity
        if (!hasLaunched) {
            try {
                //Start service
                hasLaunched = true;
                //Launch mod menu.
                MainActivity.this.startActivity(new Intent(MainActivity.this, Class.forName(MainActivity.this.GameActivity)));
                Main.Start(this);
                return;
            } catch (ClassNotFoundException e) {
                Log.e("Mod_menu", "Error. Game's main activity does not exist");
                //Uncomment this if you are following METHOD 2 to launch menu
                //Toast.makeText(MainActivity.this, "Error. Game's main activity does not exist", Toast.LENGTH_LONG).show();
            }
        }

        //Launch mod menu.
       // Main.StartWithoutPermission(this);
        Main.Start(this);
    }
}
