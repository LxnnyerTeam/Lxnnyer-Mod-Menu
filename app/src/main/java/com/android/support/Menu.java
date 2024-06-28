//Please don't replace listeners with lambda!

package com.android.support;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;

import org.xml.sax.ErrorHandler;
import android.widget.TableLayout.LayoutParams;
import android.widget.SearchView.OnCloseListener;
import android.view.View.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View.OnTouchListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import java.util.Objects;
import android.view.animation.RotateAnimation;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.transition.Visibility;
public class Menu {
    //********** Here you can easly change the menu appearance **********//

    //region Variable
    public static final String TAG = "Mod_Menu"; //Tag for logcat

    int TEXT_COLOR = Color.parseColor("#FFFFFF");
    int TEXT_COLOR_2 = Color.parseColor("#143568");
    int BTN_COLOR = Color.parseColor("#143568");
    int MENU_BG_COLOR = Color.parseColor("#143568"); //#AARRGGBB
    int MENU_FEATURE_BG_COLOR = Color.parseColor("#FFFFFF"); //#AARRGGBB
    int MENU_WIDTH = 260;
    int MENU_HEIGHT = 280;
    int POS_X = 0;
    int POS_Y = 100;

    float MENU_CORNER = 30f;
    int ICON_SIZE = 60; //Change both width and height of image
    float ICON_ALPHA = 1f; //Transparent
    int ToggleON = Color.GREEN;
    int ToggleOFF = Color.RED;
    int BtnON = Color.parseColor("#1b5e20");
    int BtnOFF = Color.parseColor("#7f0000");
    int CategoryBG = Color.parseColor("#143568");
    int SeekBarColor = Color.parseColor("#143568");
    int SeekBarProgressColor = Color.parseColor("#143568");
    int CheckBoxColor = Color.parseColor("#143568");
    int RadioColor = Color.parseColor("#143568");
    String NumberTxtColor = "#143568";
	int TAB_LAYOUT_COLOR = Color.parseColor("#EBF1FD");
    //********************************************************************//

    RelativeLayout mCollapsed, mRootContainer;
    LinearLayout mExpanded, mods,mods2,mods3,mods4,mods5,mods6, mSettings, mCollapse;
    LinearLayout.LayoutParams scrlLLExpanded, scrlLL;
    WindowManager mWindowManager, mWindowManager2;
    WindowManager.LayoutParams vmParams, vmParams2;
    ImageView startimage;
    FrameLayout rootFrame;
    ScrollView scrollView;
    boolean stopChecking, overlayRequired;
    Context getContext;
	int currenttabindex = 1;
	LinearLayout MainGridly,gridbuttonhold,gridbuttonhold2,gridbuttonhold3;
    Button button,button2,button3,button4,button5,button6;
	TextView back;
	private boolean view1 = false;
	final TextView title;
	

    //initialize methods from the native library
    native void Init(Context context, TextView title, TextView subTitle);

    native String Icon();
	
	native String closebt();
	
	native String Back();
	
	native String Playermodsicon();
	
	native String Playerabilitiesicon();
	
	native String Shoopmodsicon();

	native String Mapmodsicon();
	
	native String Sittingsicon();
	
	native String Cameraicon();

    native String IconWebViewData();

    native String[] GetFeatureList();
	
	native String[] GetFeatureList2();
	
	native String[] GetFeatureList3();
	
	native String[] GetFeatureList4();
	
	native String[] GetFeatureList5();
	
	native String[] GetFeatureList6();

    native String[] SettingsList();

    native boolean IsGameLibLoaded();
	
	
	private void AddColor(View view, int color, int strokeWidth, int dashWidth, int dashGap, int strokeColor, int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadii(new float[]{r1, r2, r3, r4, r5, r6, r7, r8});
        gd.setStroke(strokeWidth, strokeColor, dashWidth, dashGap);
        view.setBackgroundDrawable(gd);
    }
	
	private void MyToast(Context context, String text) {
		TextView textView = new TextView(context);
		textView.setText(text);
		textView.setTextSize(15f);
		textView.setTextColor(TEXT_COLOR);
		textView.setPadding(20,10,20,10);
		AddColor(textView, BTN_COLOR, 3, 0, 0, Color.WHITE, 30, 30, 0, 0, 30, 30, 0, 0);
		Toast toast = Toast.makeText(context,null, Toast.LENGTH_SHORT);
		toast.setView(textView);
		toast.show();
	}
	
	private void Gridlayout(int i, View v) {
		switch(i) {
			case 1:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mods);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
			case 2:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mods2);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
				
			case 3:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mods3);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
			case 4:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mods4);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
				
			case 5:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mods5);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
				
			case 6:
				MainGridly.setVisibility(View.GONE);
				back.setVisibility(View.VISIBLE);
				scrollView.removeAllViews();
				scrollView.addView(mSettings);
				scrollView.scrollTo(0, 0);
				view1 = true;
				break;
				}
	}
	

    //Here we write the code for our Menu
    // Reference: https://www.androidhive.info/2016/11/android-floating-widget-like-facebook-chat-head/
    public Menu(Context context) {
		
		Typeface y = Typeface.createFromAsset(context.getAssets(), "Lxnnyer.ModMenu"); 
		
        getContext = context;
        Preferences.context = context;
        rootFrame = new FrameLayout(context); // Global markup
        rootFrame.setOnTouchListener(onTouchListener());
        mRootContainer = new RelativeLayout(context); // Markup on which two markups of the icon and the menu itself will be placed
        mCollapsed = new RelativeLayout(context); // Markup of the icon (when the menu is minimized)
        mCollapsed.setVisibility(View.VISIBLE);
        mCollapsed.setAlpha(ICON_ALPHA);

        //********** The box of the mod menu **********
        mExpanded = new LinearLayout(context); // Menu markup (when the menu is expanded)
        mExpanded.setVisibility(View.GONE);
        mExpanded.setBackgroundColor(MENU_BG_COLOR);
        mExpanded.setOrientation(LinearLayout.VERTICAL);
        // mExpanded.setPadding(1, 1, 1, 1); //So borders would be visible
        mExpanded.setLayoutParams(new LinearLayout.LayoutParams(dp(MENU_WIDTH), WRAP_CONTENT));
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setCornerRadius(MENU_CORNER); //Set corner
        gdMenuBody.setColor(MENU_BG_COLOR); //Set background color
        //gdMenuBody.setStroke(1, Color.parseColor("#32cb00")); //Set border
        mExpanded.setBackground(gdMenuBody); //Apply GradientDrawable to it

        //********** The icon to open mod menu **********
        startimage = new ImageView(context);
        startimage.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension = (int) TypedValue.applyDimension(1, ICON_SIZE, context.getResources().getDisplayMetrics()); //Icon size
        startimage.getLayoutParams().height = applyDimension;
        startimage.getLayoutParams().width = applyDimension;
        //startimage.requestLayout();
        startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        byte[] decode = Base64.decode(Icon(), 0);
        startimage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
        ((ViewGroup.MarginLayoutParams) startimage.getLayoutParams()).topMargin = convertDipToPixels(10);
        //Initialize event handlers for buttons, etc.
        startimage.setOnTouchListener(onTouchListener());
        startimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.GONE);
                mExpanded.setVisibility(View.VISIBLE);
            }
        });

        //********** The icon in Webview to open mod menu **********
        WebView wView = new WebView(context); //Icon size width=\"50\" height=\"50\"
        wView.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension2 = (int) TypedValue.applyDimension(1, ICON_SIZE, context.getResources().getDisplayMetrics()); //Icon size
        wView.getLayoutParams().height = applyDimension2;
        wView.getLayoutParams().width = applyDimension2;
        wView.loadData("<html>" +
                "<head></head>" +
                "<body style=\"margin: 0; padding: 0\">" +
                "<img src=\"" + IconWebViewData() + "\" width=\"" + ICON_SIZE + "\" height=\"" + ICON_SIZE + "\" >" +
                "</body>" +
                "</html>", "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setAlpha(ICON_ALPHA);
        wView.getSettings().setAppCacheEnabled(true);
        wView.setOnTouchListener(onTouchListener());
		

        //********** Settings icon **********
		back = new TextView(context); //Android 5 can't show ⚙, instead show other icon instead
        byte[] bgdecode1 = Base64.decode(Back(), 0);
		Bitmap backbitmap = BitmapFactory.decodeByteArray(bgdecode1,0,bgdecode1.length);
        BitmapDrawable backbt = new BitmapDrawable(backbitmap);
		back.setBackground(backbt);
		backbt.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        back.setTextColor(TEXT_COLOR);
        back.setTypeface(Typeface.DEFAULT_BOLD);
        back.setTextSize(20.0f);
		back.setVisibility(View.GONE);
        RelativeLayout.LayoutParams rlback = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rlback.addRule(ALIGN_PARENT_LEFT);
        back.setLayoutParams(rlback);
		back.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				title.setText(Html.fromHtml("<b>"+"Lxnnyer Mod Menu"+"<b>"));
				title.setTypeface(Typeface.createFromAsset(getContext.getAssets(), "Lxnnyer.ModMenu"));
				title.setTextSize(25.0f);
				if (view1 == true) {
					MainGridly.setVisibility(View.VISIBLE);
					back.setVisibility(View.GONE);
					scrollView.removeAllViews();
					scrollView.addView(MainGridly);
					scrollView.scrollTo(0, 0);
					view1 = true;
				}
			}

			
		});
		
		
        TextView settings = new TextView(context); //Android 5 can't show ⚙, instead show other icon instead
        byte[] bgdecode = Base64.decode(closebt(), 0);
		Bitmap backgroundbitmap = BitmapFactory.decodeByteArray(bgdecode,0,bgdecode.length);
        BitmapDrawable blackgamer = new BitmapDrawable(backgroundbitmap);
		settings.setBackground(blackgamer);
		blackgamer.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        settings.setTextColor(TEXT_COLOR);
        settings.setTypeface(Typeface.DEFAULT_BOLD);
        settings.setTextSize(20.0f);
		settings.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT));
        RelativeLayout.LayoutParams rlsettings = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rlsettings.addRule(ALIGN_PARENT_RIGHT);
        settings.setLayoutParams(rlsettings);
        settings.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					mCollapsed.setVisibility(View.VISIBLE);
					mCollapsed.setAlpha(ICON_ALPHA);
					mExpanded.setVisibility(View.GONE);
				}
			});

        //********** Settings **********
        mSettings = new LinearLayout(context);
        mSettings.setOrientation(LinearLayout.VERTICAL);
		mSettings.setPadding(5,0,5,0);
        featureList(SettingsList(), mSettings);

        //********** Title **********
        RelativeLayout titleText = new RelativeLayout(context);
        titleText.setPadding(10, 5, 10, 5);
        titleText.setVerticalGravity(16);

        title = new TextView(context);
        title.setTextColor(TEXT_COLOR);
        title.setTextSize(25.0f);
		title.setTypeface(y);
        title.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        title.setLayoutParams(rl);

        //********** Sub title **********
        TextView subTitle = new TextView(context);
        subTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTitle.setMarqueeRepeatLimit(-1);
        subTitle.setSingleLine(true);
        subTitle.setSelected(true);
        subTitle.setTextColor(TEXT_COLOR);
        subTitle.setTextSize(10.0f);
        subTitle.setGravity(Gravity.CENTER);
        subTitle.setPadding(0, 0, 0, 5);
		
		LinearLayout relative = new LinearLayout(context);
		relative.setPadding(30, 5, 30, 5);
		relative.setVerticalGravity(Gravity.CENTER);
		TextView textinfo = new TextView(context);
		textinfo.setText("All Mods");
		textinfo.setTextColor(TEXT_COLOR);
		textinfo.setTextSize(16f);
		textinfo.setTypeface(null,1);
		textinfo.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
		
		
		textinfo.setGravity(Gravity.CENTER);
		GradientDrawable gdtext = new GradientDrawable();
		gdtext.setColor(BTN_COLOR);
		gdtext.setStroke(3, TEXT_COLOR_2);
		gdtext.setCornerRadius(30);
		textinfo.setBackgroundDrawable(gdtext);

        //********** Mod menu feature list **********
        scrollView = new ScrollView(context);
        //Auto size. To set size manually, change the width and height example 500, 500
        scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT, dp(MENU_HEIGHT));
        scrlLLExpanded = new LinearLayout.LayoutParams(mExpanded.getLayoutParams());
        scrlLLExpanded.weight = 1.0f;
        scrollView.setLayoutParams(Preferences.isExpanded ? scrlLLExpanded : scrlLL);
        scrollView.setBackgroundColor(MENU_FEATURE_BG_COLOR);
        mods = new LinearLayout(context);
        mods.setOrientation(LinearLayout.VERTICAL);
		mods.setPadding(5,0,5,0);
		
		mods2 = new LinearLayout(context);
        mods2.setOrientation(LinearLayout.VERTICAL);
		mods2.setPadding(5,0,5,0);
		featureList(GetFeatureList2(), mods2);
		
		mods3 = new LinearLayout(context);
        mods3.setOrientation(LinearLayout.VERTICAL);
		mods3.setPadding(5,0,5,0);
		featureList(GetFeatureList3(), mods3);
		
		mods4 = new LinearLayout(context);
        mods4.setOrientation(LinearLayout.VERTICAL);
		mods4.setPadding(5,0,5,0);
		featureList(GetFeatureList4(), mods4);
		
		mods5 = new LinearLayout(context);
        mods5.setOrientation(LinearLayout.VERTICAL);
		mods5.setPadding(5,0,5,0);
		featureList(GetFeatureList5(), mods5);
		
		mods6 = new LinearLayout(context);
        mods6.setOrientation(LinearLayout.VERTICAL);
		mods6.setPadding(5,0,5,0);
		featureList(GetFeatureList6(), mods6);
		
		MainGridly = new LinearLayout(context);
		MainGridly.setOrientation(LinearLayout.VERTICAL);
		MainGridly.setBackgroundColor(MENU_FEATURE_BG_COLOR);
		MainGridly.setLayoutParams(new LayoutParams(MATCH_PARENT,MATCH_PARENT));
		
		gridbuttonhold = new LinearLayout(context);
		gridbuttonhold.setOrientation(LinearLayout.HORIZONTAL);
		gridbuttonhold.setGravity(Gravity.CENTER);
		gridbuttonhold.setLayoutParams(new LayoutParams(MATCH_PARENT,WRAP_CONTENT));
		gridbuttonhold.setPadding(0,0,0,0);
		
		RelativeLayout relativeLayout1 = new RelativeLayout(context);
		relativeLayout1.setPadding(10, 5, 10, 5);
        relativeLayout1.setVerticalGravity(ALIGN_PARENT_LEFT);
		
		LinearLayout TabLayout1 = new LinearLayout(context);
		TabLayout1.setOrientation(LinearLayout.VERTICAL);
		
		AddColor(TabLayout1, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
		
		LinearLayout TabLayout11 = new LinearLayout(context);
		TabLayout11.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout11.setPadding(35,30,35,30);

		TextView Iconbt = new TextView(context);
		Iconbt.setTextColor(TEXT_COLOR_2);
		byte[] pmdecode = Base64.decode(Playermodsicon(), 0);
		Bitmap icon1bitmap = BitmapFactory.decodeByteArray(pmdecode,0,pmdecode.length);
        BitmapDrawable icon1bitmap1 = new BitmapDrawable(icon1bitmap);
		Iconbt.setBackground(icon1bitmap1);
		icon1bitmap1.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);
		
		TextView LayoutText1 = new TextView(context);
		LayoutText1.setGravity(Gravity.CENTER);
		LayoutText1.setTextColor(TEXT_COLOR_2);
		LayoutText1.setText("Player\nMods");
		LayoutText1.setPadding(5,0,0,0);
		//LayoutText1.setTextSize(15f);
		LayoutText1.setTypeface(null,1);
		
		button = new Button(context);
		button.setText("View");
		button.setTextColor(TEXT_COLOR);
		AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
		button.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Player Mods");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 1;
					Gridlayout(1, v);
                }
            });
		
		
		relativeLayout1.addView(TabLayout1);
		TabLayout1.addView(TabLayout11);
		TabLayout11.addView(Iconbt);
		TabLayout11.addView(LayoutText1);
		TabLayout1.addView(button);
		
		
		RelativeLayout relativeLayout2 = new RelativeLayout(context);
		relativeLayout2.setPadding(10, 5, 10, 5);
        relativeLayout2.setVerticalGravity(ALIGN_PARENT_RIGHT);
		
		LinearLayout TabLayout2 = new LinearLayout(context);
		TabLayout2.setOrientation(LinearLayout.VERTICAL);
		
		AddColor(TabLayout2, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
		
		LinearLayout TabLayout22 = new LinearLayout(context);
		TabLayout22.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout22.setPadding(30,30,30,30);

		TextView Iconbt2 = new TextView(context);
		Iconbt2.setTextColor(TEXT_COLOR_2);
		byte[] pmdecode2 = Base64.decode(Playerabilitiesicon(), 0);
		Bitmap icon1bitmap2 = BitmapFactory.decodeByteArray(pmdecode2,0,pmdecode2.length);
        BitmapDrawable icon1bitmap3 = new BitmapDrawable(icon1bitmap2);
		Iconbt2.setBackground(icon1bitmap3);
		icon1bitmap3.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);

		TextView LayoutText2 = new TextView(context);
		LayoutText2.setGravity(Gravity.CENTER);
		LayoutText2.setTextColor(TEXT_COLOR_2);
		LayoutText2.setText("Player\nAbilities");
		LayoutText2.setPadding(5,0,0,0);
		//LayoutText2.setTextSize(15f);
		LayoutText2.setTypeface(null,1);

		button2 = new Button(context);
		button2.setText("View");
		button2.setTextColor(TEXT_COLOR);
		AddColor(button2, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
		button2.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Player Abilities");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 2;
					Gridlayout(2, v);
                }
            });
		
		relativeLayout2.addView(TabLayout2);
		TabLayout2.addView(TabLayout22);
		TabLayout22.addView(Iconbt2);
		TabLayout22.addView(LayoutText2);
		TabLayout2.addView(button2);
		
		gridbuttonhold.addView(relativeLayout1);
		gridbuttonhold.addView(relativeLayout2);
		
		
		gridbuttonhold2 = new LinearLayout(context);
		gridbuttonhold2.setOrientation(LinearLayout.HORIZONTAL);
		gridbuttonhold2.setGravity(Gravity.CENTER);
		gridbuttonhold2.setLayoutParams(new LayoutParams(MATCH_PARENT,WRAP_CONTENT));
		gridbuttonhold2.setPadding(0,0,0,0);

		RelativeLayout relativeLayout3 = new RelativeLayout(context);
        relativeLayout3.setPadding(10, 5, 10, 5);
        relativeLayout3.setVerticalGravity(ALIGN_PARENT_LEFT);
		
		LinearLayout TabLayout3 = new LinearLayout(context);
		TabLayout3.setOrientation(LinearLayout.VERTICAL);
		AddColor(TabLayout3, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);

		LinearLayout TabLayout33 = new LinearLayout(context);
		TabLayout33.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout33.setPadding(30,30,30,30);

		TextView Iconbt3 = new TextView(context);
		Iconbt3.setTextColor(TEXT_COLOR_2);
		byte[] pmdecode3 = Base64.decode(Cameraicon(), 0);
		Bitmap icon1bitmap4 = BitmapFactory.decodeByteArray(pmdecode3,0,pmdecode3.length);
        BitmapDrawable icon1bitmap5 = new BitmapDrawable(icon1bitmap4);
		Iconbt3.setBackground(icon1bitmap5);
		icon1bitmap5.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);
		

		TextView LayoutText3 = new TextView(context);
		LayoutText3.setGravity(Gravity.CENTER);
		LayoutText3.setTextColor(TEXT_COLOR_2);
		LayoutText3.setText("Camera\nMods");
		LayoutText3.setPadding(5,0,0,0);
		//LayoutText3.setTextSize(15f);
		LayoutText3.setTypeface(null,1);

		button3 = new Button(getContext);
		button3.setText("View");
		button3.setTextColor(TEXT_COLOR);
		AddColor(button3, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
		button3.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Camera Mods");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 3;
					Gridlayout(3, v);
                }
            });
		
		relativeLayout3.addView(TabLayout3);
		TabLayout3.addView(TabLayout33);
		TabLayout33.addView(Iconbt3);
		TabLayout33.addView(LayoutText3);
		TabLayout3.addView(button3);
		
		RelativeLayout relativeLayout4 = new RelativeLayout(context);
        relativeLayout4.setPadding(10, 5, 10, 5);
        relativeLayout4.setVerticalGravity(ALIGN_PARENT_RIGHT);

		LinearLayout TabLayout4 = new LinearLayout(context);
		TabLayout4.setOrientation(LinearLayout.VERTICAL);
		AddColor(TabLayout4, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);

		LinearLayout TabLayout44 = new LinearLayout(context);
		TabLayout44.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout44.setPadding(45,30,45,30);

		TextView Iconbt4 = new TextView(context);
		Iconbt4.setTextColor(TEXT_COLOR_2);
		
		byte[] pmdecode4 = Base64.decode(Mapmodsicon(), 0);
		Bitmap icon1bitmap6 = BitmapFactory.decodeByteArray(pmdecode4,0,pmdecode4.length);
        BitmapDrawable icon1bitmap7 = new BitmapDrawable(icon1bitmap6);
		Iconbt4.setBackground(icon1bitmap7);
		icon1bitmap7.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);
		

		TextView LayoutText4 = new TextView(context);
		LayoutText4.setGravity(Gravity.CENTER);
		LayoutText4.setTextColor(TEXT_COLOR_2);
		LayoutText4.setText("Map\nMods");
		LayoutText4.setPadding(5,0,0,0);
		//LayoutText4.setTextSize(15f);
		LayoutText4.setTypeface(null,1);

		button4 = new Button(getContext);
		button4.setText("View");
		button4.setTextColor(TEXT_COLOR);
		AddColor(button4, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
        button4.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Map Mods");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 4;
					Gridlayout(4, v);
                }
            });
		
		relativeLayout4.addView(TabLayout4);
		TabLayout4.addView(TabLayout44);
		TabLayout44.addView(Iconbt4);
		TabLayout44.addView(LayoutText4);
		TabLayout4.addView(button4);
		
		
		gridbuttonhold2.addView(relativeLayout3);
        gridbuttonhold2.addView(relativeLayout4);
		
		
		gridbuttonhold3 = new LinearLayout(context);
		gridbuttonhold3.setOrientation(LinearLayout.HORIZONTAL);
		gridbuttonhold3.setGravity(Gravity.CENTER);
		gridbuttonhold3.setLayoutParams(new LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        gridbuttonhold3.setPadding(0,0,0,0);

		RelativeLayout relativeLayout5 = new RelativeLayout(context);
        relativeLayout5.setPadding(10, 5, 10, 5);
        relativeLayout5.setVerticalGravity(ALIGN_PARENT_LEFT);

		LinearLayout TabLayout5 = new LinearLayout(context);
		TabLayout5.setOrientation(LinearLayout.VERTICAL);
		AddColor(TabLayout5, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);

		LinearLayout TabLayout55 = new LinearLayout(context);
		TabLayout55.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout55.setPadding(30,30,30,30);

		TextView Iconbt5 = new TextView(context);
		Iconbt5.setTextColor(TEXT_COLOR_2);
		byte[] pmdecode5 = Base64.decode(Shoopmodsicon(), 0);
		Bitmap icon1bitmap8 = BitmapFactory.decodeByteArray(pmdecode5,0,pmdecode5.length);
        BitmapDrawable icon1bitmap9 = new BitmapDrawable(icon1bitmap8);
		Iconbt5.setBackground(icon1bitmap9);
		icon1bitmap9.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);

		

		TextView LayoutText5 = new TextView(context);
		LayoutText5.setGravity(Gravity.CENTER);
		LayoutText5.setTextColor(TEXT_COLOR_2);
		LayoutText5.setText("Shoping\nmods");
		LayoutText5.setPadding(5,0,0,0);
		//LayoutText5.setTextSize(15f);
		LayoutText5.setTypeface(null,1);

		button5 = new Button(getContext);
		button5.setText("View");
		button5.setTextColor(TEXT_COLOR);
		AddColor(button5, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
        button5.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Shoping Mods");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 5;
					Gridlayout(5, v);
                }
            });
		
		relativeLayout5.addView(TabLayout5);
		TabLayout5.addView(TabLayout55);
		TabLayout55.addView(Iconbt5);
		TabLayout55.addView(LayoutText5);
		TabLayout5.addView(button5);

		RelativeLayout relativeLayout6 = new RelativeLayout(context);
        relativeLayout6.setPadding(10, 5, 10, 5);
        relativeLayout6.setVerticalGravity(ALIGN_PARENT_RIGHT);

		LinearLayout TabLayout6 = new LinearLayout(context);
		TabLayout6.setOrientation(LinearLayout.VERTICAL);
		AddColor(TabLayout6, TAB_LAYOUT_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);

		LinearLayout TabLayout66 = new LinearLayout(context);
		TabLayout66.setOrientation(LinearLayout.HORIZONTAL);
		TabLayout66.setPadding(30,30,30,30);

		TextView Iconbt6 = new TextView(context);
		Iconbt6.setTextColor(TEXT_COLOR_2);
		byte[] pmdecode6 = Base64.decode(Sittingsicon(), 0);
		Bitmap icon1bitmap10 = BitmapFactory.decodeByteArray(pmdecode6,0,pmdecode6.length);
        BitmapDrawable icon1bitmap11 = new BitmapDrawable(icon1bitmap10);
		Iconbt6.setBackground(icon1bitmap11);
		icon1bitmap11.setColorFilter(TEXT_COLOR_2, PorterDuff.Mode.SRC_ATOP);
		

		TextView LayoutText6 = new TextView(context);
		LayoutText6.setGravity(Gravity.CENTER);
		LayoutText6.setTextColor(TEXT_COLOR_2);
		LayoutText6.setText("Settings\nView");
		LayoutText6.setPadding(5,0,0,0);
		//LayoutText6.setTextSize(15f);
		LayoutText6.setTypeface(null,1);

		button6 = new Button(getContext);
		button6.setText("View");
		button6.setTextColor(TEXT_COLOR);
		AddColor(button6, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
        button6.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
					title.setText("Settings");
					title.setTypeface(null,1);
					title.setTextSize(20.0f);
					currenttabindex = 6;
					Gridlayout(6, v);
                }
            });
		
		relativeLayout6.addView(TabLayout6);
		TabLayout6.addView(TabLayout66);
		TabLayout66.addView(Iconbt6);
		TabLayout66.addView(LayoutText6);
		TabLayout6.addView(button6);


		gridbuttonhold3.addView(relativeLayout5);
        gridbuttonhold3.addView(relativeLayout6);
		
		
        //********** RelativeLayout for buttons **********
       
		
		TextView textdec = new TextView(getContext);
		textdec.setText("Lxnnyer Mod Menu || Version 1.2");
		textdec.setGravity(Gravity.CENTER);
		textdec.setTextSize(14f);
		textdec.setTextColor(TEXT_COLOR);

        //********** Adding view components **********
        mRootContainer.addView(mCollapsed);
        mRootContainer.addView(mExpanded);
        if (IconWebViewData() != null) {
            mCollapsed.addView(wView);
        } else {
            mCollapsed.addView(startimage);
        }
		titleText.addView(back);
        titleText.addView(title);
        titleText.addView(settings);
        mExpanded.addView(titleText);
		scrollView.addView(mods6);
		MainGridly.addView(relative);
		relative.addView(textinfo);
        MainGridly.addView(gridbuttonhold);
		MainGridly.addView(gridbuttonhold2);
		MainGridly.addView(gridbuttonhold3);
        mExpanded.addView(scrollView);
        mExpanded.addView(textdec);

        Init(context, title, subTitle);
    }

    public void ShowMenu() {
        rootFrame.addView(mRootContainer);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            boolean viewLoaded = false;

            @Override
            public void run() {
                //If the save preferences is enabled, it will check if game lib is loaded before starting menu
                //Comment the if-else code out except startService if you want to run the app and test preferences
                if (Preferences.loadPref && !IsGameLibLoaded() && !stopChecking) {
                    if (!viewLoaded) {
                        Category(mods, "Save preferences was been enabled. Waiting for game lib to be loaded...\n\nForce load menu may not apply mods instantly. You would need to reactivate them again");
                        Button(mods, -100, "Force load menu");
                        viewLoaded = true;
                    }
                    handler.postDelayed(this, 600);
                } else {
                    mods.removeAllViews();
                    featureList(GetFeatureList(), mods);
                }
            }
        }, 500);
    }

    @SuppressLint("WrongConstant")
    public void SetWindowManagerWindowService() {
        //Variable to check later if the phone supports Draw over other apps permission
        int iparams = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? 2038 : 2002;
        vmParams = new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, iparams, 8, -3);
        //params = new WindowManager.LayoutParams(WindowManager.LayoutParams.LAST_APPLICATION_WINDOW, 8, -3);
        vmParams.gravity = 51;
        vmParams.x = POS_X;
        vmParams.y = POS_Y;

        mWindowManager = (WindowManager) getContext.getSystemService(getContext.WINDOW_SERVICE);
        mWindowManager.addView(rootFrame, vmParams);

        overlayRequired = true;
    }

    @SuppressLint("WrongConstant")
    public void SetWindowManagerActivity() {
        vmParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			POS_X,//initialX
			POS_Y,//initialy
			WindowManager.LayoutParams.TYPE_APPLICATION,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
			WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
			PixelFormat.TRANSPARENT
        );
        vmParams.gravity = 51;
        vmParams.x = POS_X;
        vmParams.y = POS_Y;

        mWindowManager = ((Activity) getContext).getWindowManager();
        mWindowManager.addView(rootFrame, vmParams);
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            final View collapsedView = mCollapsed;
            final View expandedView = mExpanded;
            private float initialTouchX, initialTouchY;
            private int initialX, initialY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = vmParams.x;
                        initialY = vmParams.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int rawX = (int) (motionEvent.getRawX() - initialTouchX);
                        int rawY = (int) (motionEvent.getRawY() - initialTouchY);
                        mExpanded.setAlpha(1f);
                        mCollapsed.setAlpha(1f);
                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (rawX < 10 && rawY < 10 && isViewCollapsed()) {
                            //When user clicks on the image view of the collapsed layout,
                            //visibility of the collapsed layout will be changed to "View.GONE"
                            //and expanded view will become visible.
                            try {
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            } catch (NullPointerException e) {

                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        mExpanded.setAlpha(0.5f);
                        mCollapsed.setAlpha(0.5f);
                        //Calculate the X and Y coordinates of the view.
                        vmParams.x = initialX + ((int) (motionEvent.getRawX() - initialTouchX));
                        vmParams.y = initialY + ((int) (motionEvent.getRawY() - initialTouchY));
                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(rootFrame, vmParams);
                        return true;
                    default:
                        return false;
                }
            }
        };
    }
	

    private void featureList(String[] listFT, LinearLayout linearLayout) {
        //Currently looks messy right now. Let me know if you have improvements
        int featNum, subFeat = 0;
        LinearLayout llBak = linearLayout;

        for (int i = 0; i < listFT.length; i++) {
            boolean switchedOn = false;
            //Log.i("featureList", listFT[i]);
            String feature = listFT[i];
            if (feature.contains("_True")) {
                switchedOn = true;
                feature = feature.replaceFirst("_True", "");
            }

            linearLayout = llBak;
            if (feature.contains("CollapseAdd_")) {
                //if (collapse != null)
                linearLayout = mCollapse;
                feature = feature.replaceFirst("CollapseAdd_", "");
            }
            String[] str = feature.split("_");

            //Assign feature number
            if (TextUtils.isDigitsOnly(str[0]) || str[0].matches("-[0-9]*")) {
                featNum = Integer.parseInt(str[0]);
                feature = feature.replaceFirst(str[0] + "_", "");
                subFeat++;
            } else {
                //Subtract feature number. We don't want to count ButtonLink, Category, RichTextView and RichWebView
                featNum = i - subFeat;
            }
            String[] strSplit = feature.split("_");
            switch (strSplit[0]) {
                case "Toggle":
                    Switch(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "SeekBar":
                    SeekBar(linearLayout, featNum, strSplit[1], Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[3]));
                    break;
                case "Button":
                    Button(linearLayout, featNum, strSplit[1]);
                    break;
                case "ButtonOnOff":
                    ButtonOnOff(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "Spinner":
                    TextView(linearLayout, strSplit[1]);
                    Spinner(linearLayout, featNum, strSplit[1], strSplit[2]);
                    break;
                case "InputText":
                    InputText(linearLayout, featNum, strSplit[1]);
                    break;
				case "InputText2":
                    InputText2(linearLayout, featNum, strSplit[1]);
                    break;
                case "InputValue":
                    if (strSplit.length == 3)
                        InputNum(linearLayout, featNum, strSplit[2], Integer.parseInt(strSplit[1]));
                    if (strSplit.length == 2)
                        InputNum(linearLayout, featNum, strSplit[1], 0);
                    break;
                case "CheckBox":
                    CheckBox(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "RadioButton":
                    RadioButton(linearLayout, featNum, strSplit[1], strSplit[2]);
                    break;
                case "Collapse":
                    Collapse(linearLayout, strSplit[1], switchedOn);
                    subFeat++;
                    break;
                case "ButtonLink":
                    subFeat++;
                    ButtonLink(linearLayout, strSplit[1], strSplit[2]);
                    break;
                case "Category":
                    subFeat++;
                    Category(linearLayout, strSplit[1]);
                    break;
                case "RichTextView":
                    subFeat++;
                    TextView(linearLayout, strSplit[1]);
                    break;
                case "RichWebView":
                    subFeat++;
                    WebTextView(linearLayout, strSplit[1]);
                    break;
            }
        }
    }

    private void Switch(LinearLayout linLayout, final int featNum, final String featName, boolean swiOn) {
        final Switch switchR = new Switch(getContext);
       final GradientDrawable THUMB = new GradientDrawable();
		THUMB.setShape(1);
		THUMB.setSize(40,40);
		
		final GradientDrawable TRACK = new GradientDrawable();
		TRACK.setCornerRadius(100);
		TRACK.setSize(20,20);
		
		boolean isOn = Preferences.loadPrefBool(featName, featNum, swiOn);
		if(isOn){
			THUMB.setColor(BTN_COLOR);
			THUMB.setStroke(2,BTN_COLOR);
			
			TRACK.setColor(Color.parseColor("#AB143568"));
			TRACK.setStroke(2, BTN_COLOR);
		}
		else{
			THUMB.setColor(Color.WHITE);
			THUMB.setStroke(2,BTN_COLOR);

			TRACK.setColor(Color.argb(200,255,255,255));
			TRACK.setStroke(2, BTN_COLOR);
		}
		
        switchR.setText(featName);
        switchR.setTextColor(TEXT_COLOR_2);
		switchR.setThumbDrawable(THUMB);
		switchR.setTrackDrawable(TRACK);
        switchR.setPadding(10, 5, 0, 5);
        switchR.setChecked(Preferences.loadPrefBool(featName, featNum, swiOn));
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
                Preferences.changeFeatureBool(featName, featNum, bool);
                if(bool){
					THUMB.setColor(BTN_COLOR);
					THUMB.setStroke(2,BTN_COLOR);

					TRACK.setColor(Color.parseColor("#AB143568"));
					TRACK.setStroke(2, BTN_COLOR);
				}
				else{
					THUMB.setColor(Color.WHITE);
					THUMB.setStroke(2,BTN_COLOR);

					TRACK.setColor(Color.argb(200,255,255,255));
					TRACK.setStroke(2, BTN_COLOR);
				}
				switch (featNum) {
                    case -1: //Save perferences
                        Preferences.with(switchR.getContext()).writeBoolean(-1, bool);
                        if (bool == false)
                            Preferences.with(switchR.getContext()).clear(); //Clear perferences if switched off
                        break;
                    case -3:
                        Preferences.isExpanded = bool;
                        scrollView.setLayoutParams(bool ? scrlLLExpanded : scrlLL);
                        break;
					
                }
            }
        });

        linLayout.addView(switchR);
    }

    private void SeekBar(LinearLayout linLayout, final int featNum, final String featName, final int min, int max) {
        int loadedProg = Preferences.loadPrefInt(featName, featNum);
        LinearLayout linearLayout = new LinearLayout(getContext);
        linearLayout.setPadding(10, 5, 0, 5);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        final TextView textView = new TextView(getContext);
		if(Preferences.loadPref){
			textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + loadedProg + "[SAVED VALUE]"));
		}
		else{
			textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>"  + "[DEFAULT]"));
		}
        textView.setTextColor(TEXT_COLOR_2);
		textView.setPadding(5,0,0,0);

        SeekBar seekBar = new SeekBar(getContext);
        seekBar.setPadding(25, 10, 35, 10);
        seekBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            seekBar.setMin(min); //setMin for Oreo and above
        seekBar.setProgress((loadedProg == 0) ? min : loadedProg);
		seekBar.getThumb().setColorFilter(SeekBarColor, PorterDuff.Mode.SRC_ATOP);
        seekBar.getProgressDrawable().setColorFilter(SeekBarProgressColor, PorterDuff.Mode.SRC_ATOP);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if(i > min){
					seekBar.setProgress(i < min ? min : i);
					Preferences.changeFeatureInt(featName, featNum, i < min ? min : i);
					textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + (i < min ? min : i) + "x"));
				}
				else{
					seekBar.setProgress(i < min ? min : i);
					Preferences.changeFeatureInt(featName, featNum, i < min ? min : i);
					textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + "[DEFAULT]"));
				}
				
				switch(featNum){
					case -3:
						ICON_ALPHA = (i + 1) / 10.0F;
						if (i == 10)
							textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[DEFAULT]"));
						if (i == min)
							ICON_ALPHA = 1.0F;
						break;               
					case -4:
						startimage.getLayoutParams().height = (i + 13) * 5;
						startimage.getLayoutParams().width = (i + 13) * 5;
						if (i == 13)
							textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[DEFAULT]"));
						break;

					case -5:
						if(i >= 1){
							RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
							int speed = i > 10  ? ((i - 10) * 1000) : i * 100; 
							rotateAnimation.setDuration(360 * 1000 / speed);
							rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
							rotateAnimation.setRepeatCount(Animation.INFINITE);
							startimage.startAnimation(rotateAnimation);
						}else{
							startimage.clearAnimation();
						}
						break;
						
					case 25:
						switch (i){
							case 0:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "OFF"));
								MyToast(getContext, "OFF");
								break;
							case 1:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "All Textcher"));
								MyToast(getContext, "All Textcher");
								break;

							case 2:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "Characters Only"));
								MyToast(getContext, "Characters Only");
								break;

							case 3:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "Train Only"));
								MyToast(getContext, "Train Only");
								break;
							case 4:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "Lane Only"));
								MyToast(getContext, "Lane Only");
								break;
						}
						break;
						case 32:
							switch(i){
								case 12:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "MAX"));
								MyToast(getContext, "MAX");
									break;
							}
							break;
						/*case 5:
						//if (i >= 1 && i <= 9) {
							textView.setText(Html.fromHtml(featName + " -> " + String.format("%f", i * 0.123456) + "x"));
						} else if (i >= 10) {
							textView.setText(Html.fromHtml(featName + " -> " + (i - 9) + "x"));
						}
						break;*/
						
					
				}
            }
        });
        linearLayout.addView(textView);
        linearLayout.addView(seekBar);

        linLayout.addView(linearLayout);
    }

    private void Button(LinearLayout linLayout, final int featNum, final String featName) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR);
        button.setAllCaps(false); //Disable caps to support html
        button.setText(Html.fromHtml(featName));
        AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (featNum) {

                    case -6:
						mCollapsed.setVisibility(View.VISIBLE);
						mCollapsed.setAlpha(0);
						mExpanded.setVisibility(View.GONE);
						MyToast(getContext, "[Icon hidden. Remember the hidden icon position]");
						break;
						
					case -7:
						rootFrame.removeView(mRootContainer);
						mWindowManager.removeView(rootFrame);
						MyToast(getContext, "Mod Menu is Killed");
						break;
				
                    case -100:
                        stopChecking = true;
                        break;
                }
                Preferences.changeFeatureInt(featName, featNum, 0);
            }

			
        });

        linLayout.addView(button);
    }

    private void ButtonLink(LinearLayout linLayout, final String featName, final String url) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setAllCaps(false); //Disable caps to support html
        button.setTextColor(TEXT_COLOR);
        button.setText(Html.fromHtml(featName));
        AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                getContext.startActivity(intent);
            }
        });
        linLayout.addView(button);
    }

    private void ButtonOnOff(LinearLayout linLayout, final int featNum, String featName, boolean switchedOn) {
        final Button button = new Button(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR);
        button.setAllCaps(false); //Disable caps to support html

        final String finalfeatName = featName.replace("OnOff_", "");
        boolean isOn = Preferences.loadPrefBool(featName, featNum, switchedOn);
        if (isOn) {
            button.setText(Html.fromHtml(finalfeatName + ": ON"));
            AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
            isOn = false;
        } else {
            button.setText(Html.fromHtml(finalfeatName + ": OFF"));
            AddColor(button, BTN_COLOR, 3, 3, 3, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
            isOn = true;
        }
        final boolean finalIsOn = isOn;
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOn = finalIsOn;

            public void onClick(View v) {
                Preferences.changeFeatureBool(finalfeatName, featNum, isOn);
                //Log.d(TAG, finalfeatName + " " + featNum + " " + isActive2);
                if (isOn) {
                    button.setText(Html.fromHtml(finalfeatName + ": ON"));
                    AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
                    isOn = false;
                } else {
                    button.setText(Html.fromHtml(finalfeatName + ": OFF"));
                    AddColor(button, BTN_COLOR, 3, 3 , 3, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
                    isOn = true;
                }
            }
        });
        linLayout.addView(button);
    }

    private void Spinner(LinearLayout linLayout, final int featNum, final String featName, final String list) {
        Log.d(TAG, "spinner " + featNum + " " + featName + " " + list);
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));

        // Create another LinearLayout as a workaround to use it as a background
        // to keep the down arrow symbol. No arrow symbol if setBackgroundColor set
        LinearLayout linearLayout2 = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        layoutParams2.setMargins(7, 2, 7, 2);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
		
        AddColor(linearLayout2, Color.TRANSPARENT, 3, 0, 0,BTN_COLOR , 30, 30, 0, 0, 30, 30, 0, 0);
        linearLayout2.setLayoutParams(layoutParams2);

        final Spinner spinner = new Spinner(getContext, Spinner.MODE_DROPDOWN);
        spinner.setLayoutParams(layoutParams2);
        spinner.getBackground().setColorFilter(1, PorterDuff.Mode.SRC_ATOP); //trick to show white down arrow color
        //Creating the ArrayAdapter instance having the list
        ArrayAdapter aa = new ArrayAdapter(getContext, android.R.layout.simple_spinner_dropdown_item, lists);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner'
        spinner.setAdapter(aa);
        spinner.setSelection(Preferences.loadPrefInt(featName, featNum));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Preferences.changeFeatureInt(spinner.getSelectedItem().toString(), featNum, position);
                ((TextView) parentView.getChildAt(0)).setTextColor(TEXT_COLOR_2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        linearLayout2.addView(spinner);
        linLayout.addView(linearLayout2);
    }
	
	
	private void InputText2(LinearLayout linLayout, final int featNum, final String featName) {
		final EditTextString edittextstring = new EditTextString();
        LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(15, 10, 15, 10);
		linearLayout.setOrientation(0);
		linearLayout.setGravity(16);
		linearLayout.setPadding(10, 8, 8, 8);

		GradientDrawable Inputtextgd = new GradientDrawable();
		Inputtextgd.setStroke(3,BTN_COLOR);
		Inputtextgd.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});
		linearLayout.setBackground(Inputtextgd);
		linearLayout.setLayoutParams(layoutParams);

		LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.weight = 1.0f;

		LinearLayout linearLayout2 = new LinearLayout(getContext);
		linearLayout2.setLayoutParams(lp);
		linearLayout2.setOrientation(1);
		linearLayout2.setGravity(Gravity.CENTER);

		TextView textView = new TextView(getContext);
		textView.setTextColor(TEXT_COLOR_2);
		textView.setText(featName);
		textView.setTextSize(14.0F);
		textView.setGravity(3);
		textView.setSingleLine(true);
		textView.setPadding(5, 0, 0, 0);

		final TextView textView2 = new TextView(getContext);
        String string = Preferences.loadPrefString(featName, featNum);
        edittextstring.setString((string == "") ? "" : string);
        textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + "[DEFAULT]" + "</font>"));

		textView2.setTextColor(TEXT_COLOR_2);
		textView2.setTextSize(14.0F);
		textView2.setGravity(3);
		textView2.setPadding(5, 0, 0, 0);

        final Button button = new Button(getContext);
        button.setText("ENTER");
        GradientDrawable enter = new GradientDrawable();
		enter.setColor(BTN_COLOR);
		enter.setStroke(3,Color.WHITE);
		enter.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});

		button.setBackground(enter);
        button.setTextColor(TEXT_COLOR);

        button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					final AlertDialog alert = new AlertDialog.Builder(getContext, 2).create();
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
						Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
					}
					alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
							}
						});

					//LinearLayout
					LinearLayout linearLayout1 = new LinearLayout(getContext);
					linearLayout1.setPadding(5, 5, 5, 5);
					linearLayout1.setOrientation(LinearLayout.VERTICAL);

					GradientDrawable box = new GradientDrawable();
					box.setColor(BTN_COLOR);
					box.setStroke(4,Color.WHITE,4,4);
					linearLayout1.setBackground(box);
					linearLayout1.setElevation(5.0F);

					//TextView
					final TextView titleText = new TextView(getContext);
					titleText.setText(Html.fromHtml(new StringBuffer().append(new StringBuffer().append("<u>").append(featName).toString()).append("</u>").toString()));		
					titleText.setGravity(17);
					titleText.setTypeface(Typeface.DEFAULT_BOLD);
					titleText.setTextColor(TEXT_COLOR);
					titleText.setTextSize(22f);

					//TextView
					final TextView TextViewNote = new TextView(getContext);
					TextViewNote.setGravity(17);
					TextViewNote.setTextSize(14.0F);
					TextViewNote.setText("Click \"Set Value\" button to apply changes || outside to cancel");
					TextViewNote.setPadding(10, 15, 10, 10);
					TextViewNote.setTextColor(TEXT_COLOR);

					LayoutParams lpl = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					lpl.weight = 1;

					//Edit text
					final EditText edittext = new EditText(getContext);
					edittext.setLayoutParams(lpl);
					edittext.setMaxLines(1);
					edittext.setHint("Enter key");
					edittext.setWidth(convertDipToPixels(300));
					edittext.setTextColor(TEXT_COLOR);
					edittext.setText(edittextstring.getString());
					edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								if (hasFocus) {
									imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
								} else {
									imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
								}
							}
						});
					edittext.requestFocus();

					//Button
					LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					layoutParams.setMargins(20, 10, 20, 15);

					Button btndialog = new Button(getContext);
					btndialog.setLayoutParams(layoutParams);
					GradientDrawable setvalue = new GradientDrawable();
					setvalue.setColor(BTN_COLOR);

					setvalue.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});
					setvalue.setStroke(3,Color.WHITE);
					btndialog.setBackground(setvalue);
					btndialog.setTextColor(TEXT_COLOR);
					btndialog.setPadding(15,10,15,10);
					btndialog.setText("SET VALUE");
					btndialog.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {

								final String enteredPassword = edittext.getText().toString();

                                //if (enteredPassword.equals (new String(Base64.decode("MjAyMw", 0)))) {                         
                                if (enteredPassword.equals (new String(Base64.decode("ejQzSENSZGM0OTU3bnJlcjg4MW9pams=", 0)))) {                         
                                    scrollView.removeView(mods6);
                                    scrollView.addView(MainGridly);


                                    MyToast(getContext, "Login");

                                } else {

                                    MyToast(getContext, "Error");

                                }



								String str = edittext.getText().toString();
								edittextstring.setString(edittext.getText().toString());
								textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + str + "</font>"));
								alert.dismiss();
								Preferences.changeFeatureString(featName, featNum, str);
								edittext.setFocusable(false);
							}
						});
					linearLayout1.addView(titleText);
					linearLayout1.addView(TextViewNote);
					linearLayout1.addView(edittext);
					linearLayout1.addView(btndialog);
					alert.setView(linearLayout1);
					alert.show();
				}
			});

		linearLayout.addView(linearLayout2);
		linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(button);

		linLayout.addView(linearLayout);
    }

    private void InputNum(LinearLayout linLayout, final int featNum, final String featName, final int maxValue) {
        final EditTextNum edittextnum = new EditTextNum();
		LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(15, 10, 15, 10);
		linearLayout.setPadding(10,8,8,8);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.setLayoutParams(layoutParams);
		AddColor(linearLayout, Color.TRANSPARENT, 3, 0, 0, BTN_COLOR, 30, 30, 0, 0, 30, 30, 0, 0);
		
		LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.weight = 1.0f;

		LinearLayout linearLayout2 = new LinearLayout(getContext);
		linearLayout2.setLayoutParams(lp);
		linearLayout2.setOrientation(1);
		linearLayout2.setGravity(Gravity.CENTER);

		TextView textView = new TextView(getContext);
		textView.setTextColor(TEXT_COLOR_2);
		textView.setText(featName);
		textView.setTextSize(14.0F);
		textView.setGravity(3);
		textView.setPadding(5, 0, 0, 0);

		final TextView textView2 = new TextView(getContext);
		int num = Preferences.loadPrefInt(featName, featNum);
		edittextnum.setNum((num == 0) ? 1 : num);
		textView2.setText(Html.fromHtml(" -> <font color='" + NumberTxtColor + "'>" + "DEFAULT" + "</font>"));;
        textView2.setTextColor(TEXT_COLOR_2);
		textView2.setTextSize(14.0F);
		textView2.setGravity(3);
		textView2.setPadding(5, 0, 0, 0);

        final Button button = new Button(getContext);
        button.setAllCaps(false);
        button.setText("ENTER");
        AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
        button.setTextColor(TEXT_COLOR);
        button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					final AlertDialog alert = new AlertDialog.Builder(getContext, 2).create();
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
						Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
					}
					alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
							}
						});

					//LinearLayout
					LinearLayout linearLayout1 = new LinearLayout(getContext);
					linearLayout1.setPadding(5, 5, 5, 5);
					linearLayout1.setOrientation(LinearLayout.VERTICAL);
					AddColor(linearLayout1, BTN_COLOR, 3, 0, 0, Color.WHITE, 0, 0, 0, 0, 0, 0, 0, 0);
					
					linearLayout1.setElevation(5.0F);

					//TextView
					final TextView titleText = new TextView(getContext);
					titleText.setText(Html.fromHtml(new StringBuffer().append(new StringBuffer().append("<u>").append(featName).toString()).append("</u>").toString()));		
					titleText.setGravity(17);
					titleText.setTypeface(Typeface.DEFAULT_BOLD);
					titleText.setTextColor(TEXT_COLOR);
					titleText.setTextSize(22f);

					//TextView
					final TextView TextViewNote = new TextView(getContext);
					TextViewNote.setGravity(17);
					TextViewNote.setTextSize(14.0F);
					TextViewNote.setText("Click \"Set Value\" button to apply changes || outside to cancel");
					TextViewNote.setPadding(10, 5, 10, 5);
					TextViewNote.setTextColor(TEXT_COLOR);

					LayoutParams lpl = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					lpl.weight = 1;

					//Edit text
					final EditText edittext = new EditText(getContext);
					edittext.setLayoutParams(lpl);
					edittext.setMaxLines(1);
					edittext.setHint("Write Value");
					edittext.setWidth(convertDipToPixels(300));
					edittext.setTextColor(TEXT_COLOR);
					edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
					edittext.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
					InputFilter[] FilterArray = new InputFilter[1];
					FilterArray[0] = new InputFilter.LengthFilter(10);
					edittext.setFilters(FilterArray);
					edittext.setOnTouchListener(new OnTouchListener(){

							@Override
							public boolean onTouch(View p1, MotionEvent p2) {
								switch (p2.getAction()) {
									case MotionEvent.ACTION_DOWN:
										edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
										return true;
									case MotionEvent.ACTION_UP:
										edittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
										return true;
									default:
								}
								return false;
							}
						});
					edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								if (hasFocus) {
									imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
								} else {
									imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
								}
							}
						});
					edittext.requestFocus();		

					//Button
					LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					layoutParams.setMargins(20, 10, 20, 15);

					Button btndialog = new Button(getContext);
					btndialog.setLayoutParams(layoutParams);
					AddColor(btndialog, BTN_COLOR, 3, 0, 0, Color.WHITE, 30, 30, 0, 0, 30, 30, 0, 0);
					btndialog.setTextColor(TEXT_COLOR);
					btndialog.setPadding(15,10,15,10);
					btndialog.setText("SET VALUE");
					btndialog.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								int num;
								try {
									num = Integer.parseInt(TextUtils.isEmpty(edittext.getText().toString()) ? "0" : edittext.getText().toString());
									if (maxValue != 0 &&  num >= maxValue)
										num = maxValue;
								} catch (NumberFormatException ex) {
									num = 2147483640;
								}
								edittextnum.setNum(num);
								textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + num + "</font>"));
								alert.dismiss();
								Preferences.changeFeatureInt(featName, featNum, num);
								edittext.setFocusable(false);
							}
						});
					linearLayout1.addView(titleText);
					linearLayout1.addView(TextViewNote);
					linearLayout1.addView(edittext);
					linearLayout1.addView(btndialog);
					alert.setView(linearLayout1);
					alert.show();
				}
			});

        linearLayout.addView(linearLayout2);
		linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(button);

		linLayout.addView(linearLayout);
    }

    private void InputText(LinearLayout linLayout, final int featNum, final String featName) {
        final EditTextString edittextstring = new EditTextString();
		LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(15, 10, 15, 10);
		linearLayout.setPadding(10,8,8,8);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.setLayoutParams(layoutParams);
		AddColor(linearLayout, Color.TRANSPARENT, 3, 0, 0, BTN_COLOR, 30, 30, 0, 0, 30, 30, 0, 0);
		
		LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.weight = 1.0f;

		LinearLayout linearLayout2 = new LinearLayout(getContext);
		linearLayout2.setLayoutParams(lp);
		linearLayout2.setOrientation(1);
		linearLayout2.setGravity(Gravity.CENTER);

		TextView textView = new TextView(getContext);
		textView.setTextColor(TEXT_COLOR_2);
		textView.setText(featName);
		textView.setTextSize(14.0F);
		textView.setGravity(3);
		textView.setSingleLine(true);
		textView.setPadding(5, 0, 0, 0);

		final TextView textView2 = new TextView(getContext);
        String string = Preferences.loadPrefString(featName, featNum);
        edittextstring.setString((string == "") ? "" : string);
        textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + "DEFAULT" + "</font>"));

		textView2.setTextColor(TEXT_COLOR_2);
		textView2.setTextSize(14.0F);
		textView2.setGravity(3);
		textView2.setPadding(5, 0, 0, 0);

        final Button button = new Button(getContext);
        button.setAllCaps(false);
        button.setText("ENTER");
        AddColor(button, BTN_COLOR, 0, 0, 0, Color.TRANSPARENT, 30, 30, 0, 0, 30, 30, 0, 0);
        button.setTextColor(TEXT_COLOR);
        button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					final AlertDialog alert = new AlertDialog.Builder(getContext, 2).create();
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
						Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
					}
					alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
							}
						});

					//LinearLayout
					LinearLayout linearLayout1 = new LinearLayout(getContext);
					linearLayout1.setPadding(5, 5, 5, 5);
					linearLayout1.setOrientation(LinearLayout.VERTICAL);

					AddColor(linearLayout1, BTN_COLOR, 3, 0, 0, Color.WHITE, 0, 0, 0, 0, 0, 0, 0, 0);
					linearLayout1.setElevation(5.0F);

					//TextView
					final TextView titleText = new TextView(getContext);
					titleText.setText(Html.fromHtml(new StringBuffer().append(new StringBuffer().append("<u>").append(featName).toString()).append("</u>").toString()));		
					titleText.setGravity(17);
					titleText.setTypeface(Typeface.DEFAULT_BOLD);
					titleText.setTextColor(TEXT_COLOR);
					titleText.setTextSize(22f);

					//TextView
					final TextView TextViewNote = new TextView(getContext);
					TextViewNote.setGravity(17);
					TextViewNote.setTextSize(14.0F);
					TextViewNote.setText("Click \"Set Value\" button to apply changes || outside to cancel");
					TextViewNote.setPadding(10, 15, 10, 10);
					TextViewNote.setTextColor(TEXT_COLOR);

					LayoutParams lpl = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					lpl.weight = 1;

					//Edit text
					final EditText edittext = new EditText(getContext);
					edittext.setLayoutParams(lpl);
					edittext.setMaxLines(1);
					edittext.setHint("Write Text");
					edittext.setWidth(convertDipToPixels(300));
					edittext.setTextColor(TEXT_COLOR);
					edittext.setText(edittextstring.getString());
					edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								InputMethodManager imm = (InputMethodManager) getContext.getSystemService(getContext.INPUT_METHOD_SERVICE);
								if (hasFocus) {
									imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
								} else {
									imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
								}
							}
						});
					edittext.requestFocus();

					//Button
					LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
					layoutParams.setMargins(20, 10, 20, 15);

					Button btndialog = new Button(getContext);
					btndialog.setLayoutParams(layoutParams);
					AddColor(btndialog, BTN_COLOR, 3, 0, 0, Color.WHITE, 30, 30, 0, 0, 30, 30, 0, 0);
					btndialog.setTextColor(TEXT_COLOR);
					btndialog.setPadding(15,10,15,10);
					btndialog.setText("SET VALUE");
					btndialog.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								String str = edittext.getText().toString();
								edittextstring.setString(edittext.getText().toString());
								textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + str + "</font>"));
								alert.dismiss();
								Preferences.changeFeatureString(featName, featNum, str);
								edittext.setFocusable(false);
							}
						});
					linearLayout1.addView(titleText);
					linearLayout1.addView(TextViewNote);
					linearLayout1.addView(edittext);
					linearLayout1.addView(btndialog);
					alert.setView(linearLayout1);
					alert.show();
				}
			});

        linearLayout.addView(linearLayout2);
		linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(button);

		linLayout.addView(linearLayout);
    }

    private void CheckBox(LinearLayout linLayout, final int featNum, final String featName, boolean switchedOn) {
        final CheckBox checkBox = new CheckBox(getContext);
        checkBox.setText(featName);
        checkBox.setTextColor(TEXT_COLOR_2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            checkBox.setButtonTintList(ColorStateList.valueOf(CheckBoxColor));
        checkBox.setChecked(Preferences.loadPrefBool(featName, featNum, switchedOn));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    Preferences.changeFeatureBool(featName, featNum, isChecked);
                } else {
                    Preferences.changeFeatureBool(featName, featNum, isChecked);
                }
            }
        });
        linLayout.addView(checkBox);
    }

    private void RadioButton(LinearLayout linLayout, final int featNum, String featName, final String list) {
        //Credit: LoraZalora
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));

        final TextView textView = new TextView(getContext);
        textView.setText(featName + ":");
        textView.setTextColor(TEXT_COLOR_2);

        final RadioGroup radioGroup = new RadioGroup(getContext);
        radioGroup.setPadding(10, 5, 10, 5);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.addView(textView);

        for (int i = 0; i < lists.size(); i++) {
            final RadioButton Radioo = new RadioButton(getContext);
            final String finalfeatName = featName, radioName = lists.get(i);
            View.OnClickListener first_radio_listener = new View.OnClickListener() {
                public void onClick(View v) {
                    textView.setText(Html.fromHtml(finalfeatName + ": <font color='" + NumberTxtColor + "'>" + radioName));
                    Preferences.changeFeatureInt(finalfeatName, featNum, radioGroup.indexOfChild(Radioo));
                }
            };
            System.out.println(lists.get(i));
            Radioo.setText(lists.get(i));
            Radioo.setTextColor(TEXT_COLOR_2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                Radioo.setButtonTintList(ColorStateList.valueOf(RadioColor));
            Radioo.setOnClickListener(first_radio_listener);
            radioGroup.addView(Radioo);
        }

        int index = Preferences.loadPrefInt(featName, featNum);
        if (index > 0) { //Preventing it to get an index less than 1. below 1 = null = crash
            textView.setText(Html.fromHtml(featName + ": <font color='" + NumberTxtColor + "'>" + lists.get(index - 1)));
            ((RadioButton) radioGroup.getChildAt(index)).setChecked(true);
        }
        linLayout.addView(radioGroup);
    }

    private void Collapse(LinearLayout linLayout, final String text, final boolean expanded) {
        LinearLayout.LayoutParams layoutParamsLL = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParamsLL.setMargins(0, 5, 0, 0);

        LinearLayout collapse = new LinearLayout(getContext);
        collapse.setLayoutParams(layoutParamsLL);
        collapse.setVerticalGravity(16);
        collapse.setOrientation(LinearLayout.VERTICAL);

        final LinearLayout collapseSub = new LinearLayout(getContext);
        collapseSub.setVerticalGravity(16);
        collapseSub.setPadding(0, 5, 0, 5);
        collapseSub.setOrientation(LinearLayout.VERTICAL);
        collapseSub.setBackgroundColor(TEXT_COLOR);
        collapseSub.setVisibility(View.GONE);
        mCollapse = collapseSub;

        final TextView textView = new TextView(getContext);
        textView.setBackgroundColor(CategoryBG);
        textView.setText("▽ " + text + " ▽");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(0, 20, 0, 20);

        if (expanded) {
            collapseSub.setVisibility(View.VISIBLE);
            textView.setText("△ " + text + " △");
        }

        textView.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = expanded;

            @Override
            public void onClick(View v) {

                boolean z = !isChecked;
                isChecked = z;
                if (z) {
                    collapseSub.setVisibility(View.VISIBLE);
                    textView.setText("△ " + text + " △");
                    return;
                }
                collapseSub.setVisibility(View.GONE);
                textView.setText("▽ " + text + " ▽");
            }
        });
        collapse.addView(textView);
        collapse.addView(collapseSub);
        linLayout.addView(collapse);
    }

    private void Category(LinearLayout linLayout, String text) {
		LinearLayout linearLayout = new LinearLayout(getContext);
		linearLayout.setPadding(30,5,30,5);
        TextView textView = new TextView(getContext);
        AddColor(textView, CategoryBG, 0, 0, 0, Color.TRANSPARENT, 30, 30, 30, 30, 30, 30, 30, 30);
        textView.setText(Html.fromHtml(text));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(0, 5, 0, 5);
		textView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
		linearLayout.addView(textView);
        linLayout.addView(linearLayout);
		
    }

    private void TextView(LinearLayout linLayout, String text) {
        TextView textView = new TextView(getContext);
        textView.setText(Html.fromHtml(text));
        textView.setTextColor(TEXT_COLOR_2);
        textView.setPadding(10, 5, 10, 5);
        linLayout.addView(textView);
    }

    private void WebTextView(LinearLayout linLayout, String text) {
        WebView wView = new WebView(getContext);
        wView.loadData(text, "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setPadding(0, 5, 0, 5);
        wView.getSettings().setAppCacheEnabled(false);
        linLayout.addView(wView);
    }
	
	private class EditTextString {
        private String text;

        public void setString(String s) {
            text = s;
        }

        public String getString() {
            return text;
        }
    }

    private class EditTextNum {
        private int val;

        public void setNum(int i) {
            val = i;
        }

        public int getNum() {
            return val;
        }
    }

    private boolean isViewCollapsed() {
        return rootFrame == null || mCollapsed.getVisibility() == View.VISIBLE;
    }

    //For our image a little converter
    private int convertDipToPixels(int i) {
        return (int) ((((float) i) * getContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i, getContext.getResources().getDisplayMetrics());
    }

    public void setVisibility(int view) {
        if (rootFrame != null) {
            rootFrame.setVisibility(view);
        }
    }

    public void onDestroy() {
        if (rootFrame != null) {
            mWindowManager.removeView(rootFrame);
			
        }
    }
}
