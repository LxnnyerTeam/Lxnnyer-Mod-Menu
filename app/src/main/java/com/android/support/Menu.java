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
import android.widget.HorizontalScrollView;
import android.view.View.OnClickListener;
import android.widget.TableLayout.LayoutParams;
import android.view.View.OnTouchListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import java.util.Objects;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.graphics.Typeface;
import android.content.res.AssetManager;

public class Menu {
    //********** Here you can easly change the menu appearance **********//

    //region Variable
    public static final String TAG = "Mod_Menu"; //Tag for logcat

    int TEXT_COLOR = Color.parseColor("#FFFFFF");
    int TEXT_COLOR_2 = Color.parseColor("#FFFFFF");
    int BTN_COLOR = Color.parseColor("#FFFF0000");
    int MENU_BG_COLOR = Color.parseColor("#EE1C2A35"); //#AARRGGBB
    int MENU_FEATURE_BG_COLOR = Color.parseColor("#00141C22"); //#AARRGGBB
    int MENU_WIDTH = 250;
    int MENU_HEIGHT = 160;
    int POS_X = 0;
    int POS_Y = 100;

    float MENU_CORNER = 4f;
    int ICON_SIZE = 65; //Change both width and height of image
    float ICON_ALPHA = 0.7f; //Transparent
    int ToggleON = Color.GREEN;
    int ToggleOFF = Color.RED;
    int BtnON = Color.parseColor("#1b5e20");
    int BtnOFF = Color.parseColor("#7f0000");
    int CategoryBG = Color.parseColor("#ABFF0000");
    int SeekBarColor = Color.parseColor("#FFFF0000");
    int SeekBarProgressColor = Color.parseColor("#FFFF0000");
    int CheckBoxColor = Color.parseColor("#ABFF0000");
    int RadioColor = Color.parseColor("#ABFF0000");
    String NumberTxtColor = "#FFFFFF";
	
	int LST_MAB = Color.parseColor(new String(Base64.decode("Izk5RkYwMDAw", 0)));
	int TAB_STROKE_COLOR = Color.parseColor("#FFFFFF");
	int TAB_TEXT_COLOR = Color.parseColor("#FFFFFF");
    float TAB_TEXT_SIZE = 12.5f;
	HorizontalScrollView Scroll;
    ScrollView H;
	LinearLayout l, tl;
	int CI = 1;
	int yen = 4;
	int vxy = 3;
	LinearLayout h, h2, v1, v2, v3, v4, v5;
    TextView t1, t2, t3, t4;
    //********************************************************************//

    RelativeLayout mCollapsed, mRootContainer;
    LinearLayout mExpanded, mods, mSettings, mCollapse,mods2,mods3,mods4;
    LinearLayout.LayoutParams scrlLLExpanded, scrlLL;
    WindowManager mWindowManager;
    WindowManager.LayoutParams vmParams;
    ImageView startimage;
    FrameLayout rootFrame;
    ScrollView scrollView;
    boolean stopChecking, overlayRequired;
    Context getContext;
	LinearLayout subLayout;
	Button hideBtn,closeBtn;
	BitmapDrawable LxnnyerModMenu;

    //initialize methods from the native library
    native void Init(Context context, TextView title, TextView subTitle);

    native String Icon();
	
	native String Background();

    native String IconWebViewData();
	
	native String[] GetFeatureList();

	native String[] GetFeatureList2();

	native String[] GetFeatureList3();

	native String[] GetFeatureList4();

    native String[] SettingsList();

    native boolean IsGameLibLoaded();
	
	private void AddColor(View view, int color, float corner, int strokeWidth, int strokeColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(corner);
        gd.setStroke(strokeWidth, strokeColor);
        view.setBackgroundDrawable(gd);
    }

    //Here we write the code for our Menu
    // Reference: https://www.androidhive.info/2016/11/android-floating-widget-like-facebook-chat-head/
    public Menu(Context context) {
		Typeface blackfont = Typeface.createFromAsset(context.getAssets(), "Lxnnyer.ModMenu"); 

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
        gdMenuBody.setStroke(1, Color.parseColor("#32cb00")); //Set border
        //mExpanded.setBackground(gdMenuBody); //Apply GradientDrawable to it
		byte[] bgdecode = Base64.decode(Background(), 0);
		Bitmap backgroundbitmap = BitmapFactory.decodeByteArray(bgdecode,0,bgdecode.length);
        LxnnyerModMenu = new BitmapDrawable(backgroundbitmap);
		mExpanded.setBackground(LxnnyerModMenu);

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
		
		//********** Sub Layout **********
		subLayout = new LinearLayout(context);
		subLayout.setOrientation(1);
		subLayout.setPadding(5, 5, 5, 5);

        //********** Settings icon **********
		H = new ScrollView(context);
        H.setVisibility(8);
        H.setLayoutParams(new android.widget.LinearLayout.LayoutParams(MATCH_PARENT, dp(220)));
		
        TextView settings = new TextView(context); //Android 5 can't show ⚙, instead show other icon instead
        settings.setText(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M ? "🐱" : "🐱");
        settings.setTextColor(TEXT_COLOR);
        settings.setTypeface(Typeface.DEFAULT_BOLD);
        settings.setTextSize(25.0f);
        RelativeLayout.LayoutParams rlsettings = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rlsettings.addRule(ALIGN_PARENT_RIGHT);
        settings.setLayoutParams(rlsettings);
        final Context finalContext;
        finalContext = context;
        settings.setOnClickListener(new View.OnClickListener() {
                boolean settingsOpen;

                @Override
                public void onClick(View v) {
                    Intent var2 = new Intent("android.intent.action.VIEW");
                    var2.setFlags(268435456);
                    var2.setData(Uri.parse("https://github.com/LxnnyerTeam"));
                    finalContext.startActivity(var2);
                }
            });

        //********** Settings **********
        mSettings = new LinearLayout(context);
        mSettings.setOrientation(LinearLayout.VERTICAL);
        featureList(SettingsList(), mSettings);

        //********** Title **********
        RelativeLayout titleText = new RelativeLayout(context);
        titleText.setPadding(10, 5, 10, 5);
        titleText.setVerticalGravity(16);

        TextView title = new TextView(context);
        title.setTextColor(TEXT_COLOR);
        title.setTextSize(20.0f);
		title.setTypeface(blackfont);
        title.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		rl.addRule(RelativeLayout.CENTER_VERTICAL);
		rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
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

        LayoutParams layoutParams2 = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
		layoutParams2.setMargins(5, 2, 5, 2);

		//********** HORIZONTAL SPLITS **********
		h = new LinearLayout(context);
		h.setBackgroundColor(TAB_STROKE_COLOR);
		h.setPadding(-1, 2, -1, 1);

		h2 = new LinearLayout(context);
		h2.setBackgroundColor(TAB_STROKE_COLOR);
		h2.setPadding(-1, 2, -1, 1);

		//********** VERTICAL SPLITS **********
		v1 = new LinearLayout(context);
	    v1.setBackgroundColor(TAB_STROKE_COLOR);
		v1.setPadding(2, 5, 1, 5);
		v1.setLayoutParams(layoutParams2);

		v2 = new LinearLayout(context);
        v2.setBackgroundColor(TAB_STROKE_COLOR);
		v2.setPadding(2, 5, 1, 5);
		v2.setLayoutParams(layoutParams2);

		v3 = new LinearLayout(context);
        v3.setBackgroundColor(TAB_STROKE_COLOR);
		v3.setPadding(2, 5, 1, 5);
		v3.setLayoutParams(layoutParams2);

		v4 = new LinearLayout(context);
        v4.setBackgroundColor(TAB_STROKE_COLOR);
		v4.setPadding(2, 5, 1, 5);
		v4.setLayoutParams(layoutParams2);

		v5 = new LinearLayout(context);
        v5.setBackgroundColor(-1);
		v5.setPadding(2, 5, 1, 5);
		v5.setLayoutParams(layoutParams2);



		//********** MAIN LAYOUT OF TABS **********
		l = new LinearLayout(context);
		l.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        l.setOrientation(1);
        l.setPadding(3, 0, 3, 0);

		//********** HorizontalScrollView for Menu Buttons **********
        Scroll = new HorizontalScrollView(context); 
        Scroll.setEnabled(true);
        Scroll.setHorizontalScrollBarEnabled(false);

		//********** Layout **********
        tl = new LinearLayout(context);
        tl.setOrientation(0);

		LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
		layoutParams.setMargins(-2, 5, -2, 5);

        //****************************** TAB 1 *****************************\\
        t1 = new TextView(context);
		t1.setLayoutParams(layoutParams);
        t1.setText("Menu\n1");
		t1.setGravity(17);
        t1.setTextColor(TAB_TEXT_COLOR);
        t1.setTextSize(TAB_TEXT_SIZE);
		t1.setPadding(30, 15, 30, 15);
		t1.setShadowLayer(7.0f, 0.0f, 0.0f, Color.BLACK);
        AddColor(t1, LST_MAB, 0, 3, TAB_STROKE_COLOR);
        t1.setTypeface(null, 1);
        t1.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    scrollView.removeAllViews();
					scrollView.addView(mods);
					AddColor(t1, LST_MAB, 0, 3, TAB_STROKE_COLOR);
					AddColor(t2, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t3, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t4, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);

                }
            });

        //****************************** TAB 2 *****************************\\
        t2 = new TextView(context);
		t2.setLayoutParams(layoutParams);
        t2.setText("Menu\n2");
		t2.setGravity(17);
        t2.setTextColor(TAB_TEXT_COLOR);
        t2.setTextSize(TAB_TEXT_SIZE);
		t2.setPadding(30, 15, 30, 15);
		t2.setShadowLayer(7.0f, 0.0f, 0.0f, Color.BLACK);
        AddColor(t2, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
        t2.setTypeface(null, 1);
        t2.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
					scrollView.removeAllViews();
					scrollView.addView(mods2);
					AddColor(t1, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t2, LST_MAB, 0, 3, TAB_STROKE_COLOR);
					AddColor(t3, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t4, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);

                }
            });

		//****************************** TAB 3 *****************************\\
        t3 = new TextView(context);
		t3.setLayoutParams(layoutParams);
        t3.setText("Menu\n3");
		t3.setGravity(17);
        t3.setTextColor(TAB_TEXT_COLOR);
        t3.setTextSize(TAB_TEXT_SIZE);
		t3.setPadding(30, 15, 30, 15);
		t3.setShadowLayer(7.0f, 0.0f, 0.0f, Color.BLACK);
        AddColor(t3, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
        t3.setTypeface(null, 1);
        t3.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
					scrollView.removeAllViews();
					scrollView.addView(mods3);
					AddColor(t1, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t2, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t3, LST_MAB, 0, 3, TAB_STROKE_COLOR);
					AddColor(t4, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);

                }
            });

		//****************************** TAB 4 *****************************\\
        t4 = new TextView(context);
		t4.setLayoutParams(layoutParams);
        t4.setText("Menu\n4");
		t4.setGravity(17);
        t4.setTextColor(TAB_TEXT_COLOR);
        t4.setTextSize(TAB_TEXT_SIZE);
		t4.setPadding(30, 15, 30, 15);
		t4.setShadowLayer(7.0f, 0.0f, 0.0f, Color.BLACK);
        AddColor(t4, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
        t4.setTypeface(null, 1);
        t4.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
					scrollView.removeAllViews();
					scrollView.addView(mods4);
					AddColor(t1, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t2, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t3, Color.TRANSPARENT, 0, 0, TAB_STROKE_COLOR);
					AddColor(t4, LST_MAB, 0, 3, TAB_STROKE_COLOR);

                }
            });


        //********** Mod menu feature list **********
		scrollView = new ScrollView(context);
        scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT, dp(MENU_HEIGHT));
        scrlLLExpanded = new LinearLayout.LayoutParams(mExpanded.getLayoutParams());
        scrlLLExpanded.weight = 1.0f;
        scrollView.setLayoutParams(Preferences.isExpanded ? scrlLLExpanded : scrlLL);
        scrollView.setBackgroundColor(MENU_FEATURE_BG_COLOR);
        mods = new LinearLayout(context);
        mods.setOrientation(LinearLayout.VERTICAL);
		mods.setPadding(3, 0, 3, 0);

		mods2 = new LinearLayout(context);
        mods2.setOrientation(LinearLayout.VERTICAL);
		mods2.setPadding(3, 0, 3, 0);
		featureList(GetFeatureList2(), mods2);

		mods3 = new LinearLayout(context);
        mods3.setOrientation(LinearLayout.VERTICAL);
		mods3.setPadding(3, 0, 3, 0);
		featureList(GetFeatureList3(), mods3);

		mods4 = new LinearLayout(context);
        mods4.setOrientation(LinearLayout.VERTICAL);
		mods4.setPadding(3, 0, 3, 0);
		featureList(GetFeatureList4(), mods4);

        //********** RelativeLayout for buttons **********
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setPadding(0, 0, 0, 0);
        relativeLayout.setVerticalGravity(Gravity.CENTER);

        //**********  Hide/Kill button **********
        RelativeLayout.LayoutParams lParamsHideBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsHideBtn.addRule(ALIGN_PARENT_LEFT);

        hideBtn = new Button(context);
        hideBtn.setLayoutParams(lParamsHideBtn);
        GradientDrawable hidegd = new GradientDrawable();
		hidegd.setColor(BTN_COLOR);
		hidegd.setStroke(3,Color.WHITE);
		hidegd.setCornerRadii(new float[]{0,0,30,30,0,0,0,0});
		hideBtn.setBackground(hidegd);
        hideBtn.setText("SETTINGS");
        hideBtn.setTextColor(TEXT_COLOR);
        hideBtn.setOnClickListener(new View.OnClickListener() {
				boolean settingsOpen;

                public void onClick(View view) {
                    try {
                        settingsOpen = !settingsOpen;
                        if (settingsOpen) {
                            hideBtn.setText("CLOSE");
                            H.setVisibility(0);
                            l.setVisibility(8);
                            scrollView.setVisibility(8);
                        } else {
                            hideBtn.setText("SETTINGS");
                            H.setVisibility(8);
                            l.setVisibility(0);
                            scrollView.setVisibility(0);
                        }
                    } catch (IllegalStateException e) {
                    }
                }
			});

        //********** Close button **********
        RelativeLayout.LayoutParams lParamsCloseBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsCloseBtn.addRule(ALIGN_PARENT_RIGHT);

        closeBtn = new Button(context);
        closeBtn.setLayoutParams(lParamsCloseBtn);
        GradientDrawable closegd = new GradientDrawable();
		closegd.setColor(BTN_COLOR);
		closegd.setStroke(3,Color.WHITE);
		closegd.setCornerRadii(new float[]{30,30,0,0,0,0,0,0});
		closeBtn.setBackground(closegd);
        closeBtn.setText("MINIMIZE");
        closeBtn.setTextColor(TEXT_COLOR);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.VISIBLE);
                mCollapsed.setAlpha(ICON_ALPHA);
                mExpanded.setVisibility(View.GONE);
            }
        });

        //********** Adding view components **********
        mRootContainer.addView(mCollapsed);
        mRootContainer.addView(mExpanded);
        mCollapsed.addView(startimage);
        titleText.addView(title);
        titleText.addView(settings);
        mExpanded.addView(subLayout);
        subLayout.addView(titleText);
        subLayout.addView(subTitle);
        subLayout.addView(l);
        l.addView(h);
        l.addView(Scroll);
        Scroll.addView(tl);
        tl.addView(v1);
        tl.addView(t1);
        tl.addView(v2);
        tl.addView(t2);
        tl.addView(v3);
        tl.addView(t3);
        tl.addView(v4);
        tl.addView(t4);
        tl.addView(v5);
        l.addView(h2);
        H.addView(mSettings);
        mExpanded.addView(H);
        scrollView.addView(mods);
        mExpanded.addView(scrollView);
        relativeLayout.addView(hideBtn);
        relativeLayout.addView(closeBtn);
        mExpanded.addView(relativeLayout);

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
        

        final GradientDrawable GD_TRACK = new  GradientDrawable();
        GD_TRACK.setSize(20, 20);
        GD_TRACK.setCornerRadius(100);

        final GradientDrawable GD_THUMB = new  GradientDrawable();
        GD_THUMB.setSize(40, 40);
        GD_THUMB.setShape(1);
		boolean isOn = Preferences.loadPrefBool(featName, featNum, swiOn);

        if (isOn) {
            GD_TRACK.setStroke(2, Color.WHITE);
			GD_TRACK.setColor(LST_MAB);

			GD_THUMB.setStroke(2, Color.WHITE);
			GD_THUMB.setColor(Color.RED);
		} else {
			GD_TRACK.setStroke(2, Color.RED);
			GD_TRACK.setColor(Color.argb(200, 255, 255, 255));

			GD_THUMB.setStroke(2, Color.RED);
			GD_THUMB.setColor(Color.WHITE);
        }
        switchR.setText(featName);
        switchR.setTextColor(TEXT_COLOR_2);
        switchR.setShadowLayer(7.0f, 0.0f, 0.0f, Color.BLACK);
        switchR.setPadding(10, 5, 10, 5);
		switchR.setThumbDrawable(GD_THUMB);
        switchR.setTrackDrawable(GD_TRACK);
        switchR.setChecked(Preferences.loadPrefBool(featName, featNum, swiOn));
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
                Preferences.changeFeatureBool(featName, featNum, bool);
				if (bool) {
					GD_TRACK.setStroke(2, Color.WHITE);
					GD_TRACK.setColor(LST_MAB);

					GD_THUMB.setStroke(2, Color.WHITE);
					GD_THUMB.setColor(Color.RED);
				} else {
					GD_TRACK.setStroke(2, Color.RED);
					GD_TRACK.setColor(Color.argb(200, 255, 255, 255));

					GD_THUMB.setStroke(2, Color.RED);
					GD_THUMB.setColor(Color.WHITE);
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
					case -6:
						if(bool){
                            mExpanded.setBackground(null);
                            GradientDrawable gdMenuBody = new GradientDrawable();

                            gdMenuBody.setColor(LST_MAB); //Set background color
                            gdMenuBody.setStroke(4, Color.WHITE); //Set border
                            mExpanded.setBackground(gdMenuBody); //Apply GradientDrawable to it

                        }
                        else{
                            mExpanded.setBackground(LxnnyerModMenu);

                        }
                        break;

					case -7:
                        if(bool){
                            mExpanded.setBackground(null);
                            GradientDrawable gdMenuBody = new GradientDrawable();

                            gdMenuBody.setColor(Color.parseColor("#AB000000")); //Set background color
                            gdMenuBody.setStroke(4, Color.WHITE); //Set border
                            mExpanded.setBackground(gdMenuBody); //Apply GradientDrawable to it

                        }
                        else{
                            mExpanded.setBackground(LxnnyerModMenu);

                        }
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
		if (Preferences.loadPref) {
            textView.setText(Html.fromHtml(featName + " -> "+"<font color='" + Color.WHITE + "'>" + loadedProg +" (SAVED VALUE)"));
		} else{
			textView.setText(Html.fromHtml(featName + " -> " + "<font color='" + Color.WHITE + "'>" +" [DEFAULT]"));
		}
        textView.setTextColor(TEXT_COLOR_2);

        SeekBar seekBar = new SeekBar(getContext);
        seekBar.setPadding(25, 10, 35, 10);
        seekBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            seekBar.setMin(min); //setMin for Oreo and above
        seekBar.setProgress((loadedProg == 0) ? min : loadedProg);
        GradientDrawable seekbarCircle = new GradientDrawable();
        seekbarCircle.setShape(1);
        seekbarCircle.setColor(SeekBarColor);
        seekbarCircle.setStroke(dp(2), Color.WHITE);
        seekbarCircle.setCornerRadius(15.0f);
        seekbarCircle.setSize(dp(18), dp(18));
        seekBar.setThumb(seekbarCircle);
        seekBar.getProgressDrawable().setColorFilter(SeekBarProgressColor, PorterDuff.Mode.SRC_ATOP);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                //if progress is greater than minimum, don't go below. Else, set progress
				if (i > min) {
					seekBar.setProgress(i < min ? min : i);
					Preferences.changeFeatureInt(featName, featNum, i < min ? min : i);
					textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + (i < min ? min : i)));
				} else {                   

					seekBar.setProgress(i < min ? min : i);
					Preferences.changeFeatureInt(featName, featNum, i < min ? min : i);
					textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + " [DEFAULT]"));		

				}
				switch (featNum){
					case -8:
						textView.setText(Html.fromHtml(featName + " -> " + (i - 0) + "X"));
						ICON_ALPHA = (i + 1) / 10.0F;
						if (i == 10)
							textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[DEFAULT]"));
						if (i == min)
							ICON_ALPHA = 1.0F;
						switch(i){
							case 1:
								textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + " [DEFAULT]"));
								break;
							case 9:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[INFINITY]"));

								break;

						}
						break; 
					case -9:
						textView.setText(Html.fromHtml(featName + " -> " + (i - 0) + "X"));
						startimage.getLayoutParams().height = (i + 13) * 5;
						startimage.getLayoutParams().width = (i + 13) * 5;
						if (i == 13)
							textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[DEFAULT]"));
						switch(i){
							case 1:
								textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + " [DEFAULT]"));
								break;
							case 99:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[INFINITY]"));

								break;

						}
						break;

					case -10:
						textView.setText(Html.fromHtml(featName + " -> " + (i - 0) + "X"));
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
						switch(i){
							case 0:
								textView.setText(Html.fromHtml(featName + " -> <font color='" + NumberTxtColor + "'>" + " [DEFAULT]"));
								break;
							case 20:
								textView.setText(Html.fromHtml(featName + "-> <font color='" + NumberTxtColor + "'>" + "[INFINITY]"));

								break;

						}
						break;
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
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false); //Disable caps to support html
        button.setText(Html.fromHtml(featName));
        GradientDrawable buttongd = new GradientDrawable();
		buttongd.setColor(BTN_COLOR);
		buttongd.setStroke(3,Color.WHITE);
		buttongd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
        button.setBackground(buttongd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (featNum) {

                    case -12:

						Toast.makeText(getContext, "Menu killed", Toast.LENGTH_LONG).show();
						rootFrame.removeView(mRootContainer);
						mWindowManager.removeView(rootFrame);
                        break;
					case -11:
						mCollapsed.setVisibility(View.VISIBLE);
						mCollapsed.setAlpha(0);
						mExpanded.setVisibility(View.GONE);
						Toast.makeText(getContext, "Icon hidden. Remember the hidden icon position", Toast.LENGTH_LONG).show();

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
        button.setTextColor(TEXT_COLOR_2);
        button.setText(Html.fromHtml(featName));
		GradientDrawable buttonlinkgd = new GradientDrawable();
		buttonlinkgd.setColor(BTN_COLOR);
		buttonlinkgd.setStroke(3,Color.WHITE);
		buttonlinkgd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
        button.setBackground(buttonlinkgd);
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
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false); //Disable caps to support html

        final String finalfeatName = featName.replace("OnOff_", "");
        boolean isOn = Preferences.loadPrefBool(featName, featNum, switchedOn);
        if (isOn) {
            button.setText(Html.fromHtml(finalfeatName + ": ON"));
            GradientDrawable buttonongd = new GradientDrawable();
			buttonongd.setColor(LST_MAB);
			buttonongd.setStroke(3,Color.WHITE);
			buttonongd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
			button.setBackground(buttonongd);
            isOn = false;
        } else {
            button.setText(Html.fromHtml(finalfeatName + ": OFF"));
            GradientDrawable buttonoffgd = new GradientDrawable();
			buttonoffgd.setColor(BTN_COLOR);
			buttonoffgd.setStroke(3,Color.WHITE,4,4);
			buttonoffgd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
			button.setBackground(buttonoffgd);
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
                    GradientDrawable buttonongd = new GradientDrawable();
					buttonongd.setColor(LST_MAB);
					buttonongd.setStroke(3,Color.WHITE);
					buttonongd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
					button.setBackground(buttonongd);
                    isOn = false;
                } else {
                    button.setText(Html.fromHtml(finalfeatName + ": OFF"));
                    GradientDrawable buttonoffgd = new GradientDrawable();
					buttonoffgd.setColor(BTN_COLOR);
					buttonoffgd.setStroke(3,Color.WHITE,4,4);
					buttonoffgd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
					button.setBackground(buttonoffgd);
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
        GradientDrawable spinnergd = new GradientDrawable();
		spinnergd.setColor(LST_MAB);
		spinnergd.setStroke(3,Color.WHITE);
		spinnergd.setCornerRadii(new float[]{30,30,0,0,30,30,0,0});
        linearLayout2.setBackground(spinnergd);
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

    private void InputNum(LinearLayout linLayout, final int featNum, final String featName, final int maxValue) {
		final EditTextNum edittextnum = new EditTextNum();

        LinearLayout linearLayout = new LinearLayout(getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(15, 10, 15, 10);
		linearLayout.setOrientation(0);
		linearLayout.setGravity(16);
		linearLayout.setPadding(10, 8, 8, 8);

		GradientDrawable InputNumgd = new GradientDrawable();
		InputNumgd.setStroke(3,Color.WHITE);
		InputNumgd.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});
		linearLayout.setBackground(InputNumgd);
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
		textView.setPadding(5, 0, 0, 0);

		final TextView textView2 = new TextView(getContext);
		int num = Preferences.loadPrefInt(featName, featNum);
		edittextnum.setNum((num == 0) ? 1 : num);
		textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + ((num == 0) ? 1 : num) + "</font>"));
        textView2.setTextColor(Color.WHITE);
		textView2.setTextSize(14.0F);
		textView2.setGravity(3);
		textView2.setPadding(5, 0, 0, 0);

        final Button button = new Button(getContext);
        button.setText("ENTER");
        GradientDrawable enter1 = new GradientDrawable();
		enter1.setColor(BTN_COLOR);
		enter1.setStroke(3,Color.WHITE);
		enter1.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});

		button.setBackground(enter1);
        button.setTextColor(TEXT_COLOR_2);

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
					GradientDrawable box2 = new GradientDrawable();
					box2.setColor(LST_MAB);
					box2.setStroke(4,Color.WHITE,4,4);
					linearLayout1.setBackground(box2);
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
					GradientDrawable setvalue2 = new GradientDrawable();
					setvalue2.setColor(BTN_COLOR);

					setvalue2.setCornerRadii(new float[]{30, 30, 0, 0, 30, 30, 0, 0});
					setvalue2.setStroke(3,Color.WHITE);
					btndialog.setBackground(setvalue2);
					btndialog.setTextColor(TEXT_COLOR_2);
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
		linearLayout.setOrientation(0);
		linearLayout.setGravity(16);
		linearLayout.setPadding(10, 8, 8, 8);

		GradientDrawable Inputtextgd = new GradientDrawable();
		Inputtextgd.setStroke(3,Color.WHITE);
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
        textView2.setText(Html.fromHtml("-> " + "<font color='" + NumberTxtColor + "'>" + string + "</font>"));

		textView2.setTextColor(Color.WHITE);
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
        button.setTextColor(TEXT_COLOR_2);

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
					box.setColor(LST_MAB);
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
					edittext.setHint("Write Text");
					edittext.setWidth(convertDipToPixels(300));
					edittext.setTextColor(TEXT_COLOR_2);
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
					btndialog.setTextColor(TEXT_COLOR_2);
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
            Radioo.setTextColor(Color.LTGRAY);
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
        collapseSub.setBackgroundColor(CategoryBG);
        collapseSub.setVisibility(View.GONE);
        mCollapse = collapseSub;

        final TextView textView = new TextView(getContext);
        textView.setBackgroundColor(CategoryBG);
        textView.setText("▽ " + text + " ▽");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR_2);
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
        TextView textView = new TextView(getContext);
        GradientDrawable categorygd = new GradientDrawable();
		categorygd.setColor(CategoryBG);
		categorygd.setCornerRadii(new float[]{30,30,30,30,30,30,30,30});
		categorygd.setStroke(3,Color.WHITE);
        textView.setBackground(categorygd);
        textView.setText(Html.fromHtml(text));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(0, 5, 0, 5);
        linLayout.addView(textView);
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
