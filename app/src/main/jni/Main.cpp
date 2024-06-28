#include <list>
#include <vector>
#include <string.h>
#include <pthread.h>
#include <thread>
#include <cstring>
#include <jni.h>
#include <unistd.h>
#include <fstream>
#include <iostream>
#include <dlfcn.h>
#include "Includes/Logger.h"
#include "Includes/obfuscate.h"
#include "Includes/Utils.h"
#include "KittyMemory/MemoryPatch.h"
#include "Menu/Setup.h"
#include "Includes/Chams.h"
#include "Includes/Vector3.hpp"
//Target lib here
#define targetLibName OBFUSCATE("libil2cpp.so")

#include "Includes/Macros.h"

// we will run our hacks in a new thread so our while loop doesn't block process main thread
void *hack_thread(void *) {
    LOGI(OBFUSCATE("pthread created"));
	
    //Check if target lib is loaded
    do {
        sleep(1);
    } while (!isLibraryLoaded(targetLibName));

    //Anti-lib rename
    
    do {
        sleep(1);
    } while (!isLibraryLoaded("libLxnnyer.so"));

    LOGI(OBFUSCATE("%s has been loaded"), (const char *) targetLibName);

#if defined(__aarch64__) //To compile this code for arm64 lib only. Do not worry about greyed out highlighting code, it still works
   
#else //To compile this code for armv7 lib only.

	LOGI(OBFUSCATE("Done"));
#endif

    //Anti-leech
    /*if (!iconValid || !initValid || !settingsValid) {
        //Bad function to make it crash
        sleep(5);
        int *p = 0;
        *p = 0;
    }*/

    return NULL;
}

// Do not change or translate the first text unless you know what you are doing
// Assigning feature numbers is optional. Without it, it will automatically count for you, starting from 0
// Assigned feature numbers can be like any numbers 1,3,200,10... instead in order 0,1,2,3,4,5...
// ButtonLink, Category, RichTextView and RichWebView is not counted. They can't have feature number assigned
// Toggle, ButtonOnOff and Checkbox can be switched on by default, if you add True_. Example: CheckBox_True_The Check Box
// To learn HTML, go to this page: https://www.w3schools.com/

jobjectArray GetFeatureList(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Player mods"), //Not counted
            OBFUSCATE("1_Toggle_God Mod"),
			OBFUSCATE("2_Toggle_Run Backward"),
			OBFUSCATE("3_Toggle_Fly Hack 1"),
			OBFUSCATE("53_Toggle_MultiJump"),
            OBFUSCATE("54_Toggle_Istant Lane Change"),
			OBFUSCATE("55_Toggle_No Boundary Impact"),
			OBFUSCATE("4_Toggle_Fly Hack"),
			OBFUSCATE("5_SeekBar_Character Size_0_10"),
            OBFUSCATE("6_SeekBar_Time scale_0_1000"),
			OBFUSCATE("7_Button_Teleport+500m"),
			OBFUSCATE("8_Toggle_Auto Revive"),
			OBFUSCATE("9_InputValue_Score Multiplayer"),
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

jobjectArray GetFeatureList2(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Player Abilities"), //Not counted
            OBFUSCATE("10_Toggle_Unlimited PowerUp Time"),
            OBFUSCATE("11_Toggle_Unlimited JetPack Time"),
			OBFUSCATE("56_Toggle_Max PowerUp Level"),
			OBFUSCATE("12_SeekBar_Characters Speed_0_10000"),
            OBFUSCATE("13_SeekBar_Fly_0_100"),
			OBFUSCATE("14_SeekBar_Jump Limit_0_4"),
            OBFUSCATE("15_SeekBar_Jump Hight_0_1000"),
			OBFUSCATE("16_SeekBar_Air Jump Hight_0_1000"),
			OBFUSCATE("17_SeekBar_Gravity_0_10"),
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

jobjectArray GetFeatureList3(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Camera Mods"), //Not counted
            OBFUSCATE("18_Toggle_Follow Camera"),
            OBFUSCATE("19_Toggle_Stop Camera"),
			OBFUSCATE("20_SeekBar_Field of View (FOV)_0_30"),
			OBFUSCATE("21_SeekBar_Near Clip_0_30"),
			OBFUSCATE("22_SeekBar_Far Clip_0_30"),
			OBFUSCATE("23_Toggle_Enbale Orthographic"),
			OBFUSCATE("24_SeekBar_Orthographic Size_0_30"),
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

jobjectArray GetFeatureList4(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Chams Mods"),
            OBFUSCATE("25_SeekBar_Chams Mode_0_4"),
            OBFUSCATE("26_CheckBox_Default Chams"),
            OBFUSCATE("27_CheckBox_Shading Chams"),
            OBFUSCATE("28_CheckBox_Wireframe Chams"),
            OBFUSCATE("29_CheckBox_Glow Chams"),
            OBFUSCATE("30_CheckBox_Outline Chams"),
            OBFUSCATE("31_CheckBox_Rainbow Chams"), 
            OBFUSCATE("32_SeekBar_Line Width_0_12"),
            OBFUSCATE("33_SeekBar_Color R_0_255"),
            OBFUSCATE("34_SeekBar_Color G_0_255"),
            OBFUSCATE("35_SeekBar_Color B_0_255"),
			OBFUSCATE("Category_Other Map Mods"),
			OBFUSCATE("36_Toggle_No Clip"),
            OBFUSCATE("47_Toggle_No Coins PicUp"),
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

jobjectArray GetFeatureList5(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Shoping Mods"), //Not counted
            OBFUSCATE("48_Toggle_Free Shoping"),
            OBFUSCATE("49_Toggle_Unlimited Everythig"),
            OBFUSCATE("50_Toggle_Unlook All Characters"),
            OBFUSCATE("51_Toggle_Unlook All Skin"),
            OBFUSCATE("52_Toggle_Coins Doubler"),
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

jobjectArray GetFeatureList6(JNIEnv *env, jobject context) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("Category_Key Login"),
            OBFUSCATE("InputText2_Enter key"),
			OBFUSCATE("Category_Extra Details"),
            OBFUSCATE("ButtonLink_Get Key_https://1shortlink.com/note/Eo5fj30oph"),
			OBFUSCATE("ButtonLink_Follow GitHub_https://github.com/LxnnyerTeam"),
			OBFUSCATE("RichTextView_<font>click the button below to see a video that explanis how to get key.</font>"),
			OBFUSCATE("ButtonLink_Watch Video_https://www.youtube.com/"),
            
    };

    //Now you dont have to manually update the number everytime;
    int Total_Feature = (sizeof features / sizeof features[0]);
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));

    for (int i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}

void Changes(JNIEnv *env, jclass clazz, jobject obj,
                                        jint featNum, jstring featName, jint value,
                                        jboolean boolean, jstring str) {

    LOGD(OBFUSCATE("Feature name: %d - %s | Value: = %d | Bool: = %d | Text: = %s"), featNum,
         env->GetStringUTFChars(featName, 0), value,
         boolean, str != NULL ? env->GetStringUTFChars(str, 0) : "");

    //BE CAREFUL NOT TO ACCIDENTLY REMOVE break;

    switch (featNum) {
        
    }
}

__attribute__((constructor))
void lib_main() {
    // Create a new thread so it does not block the main thread, means the game would not freeze
    pthread_t ptid;
    pthread_create(&ptid, NULL, hack_thread, NULL);
}

int RegisterMenu(JNIEnv *env) {
    JNINativeMethod methods[] = {
            {OBFUSCATE("Icon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Icon)},
			{OBFUSCATE("Sittingsicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Sittingsicon)},
			{OBFUSCATE("Shoopmodsicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Shoopmodsicon)},
			{OBFUSCATE("Mapmodsicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Mapmodsicon)},
			{OBFUSCATE("Cameraicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Cameraicon)},
			{OBFUSCATE("Playerabilitiesicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Playerabilitiesicon)},
			{OBFUSCATE("Playermodsicon"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Playermodsicon)},
			{OBFUSCATE("Back"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(Back)},
			{OBFUSCATE("closebt"), OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(closebt)},
            {OBFUSCATE("IconWebViewData"),  OBFUSCATE("()Ljava/lang/String;"), reinterpret_cast<void *>(IconWebViewData)},
            {OBFUSCATE("IsGameLibLoaded"),  OBFUSCATE("()Z"), reinterpret_cast<void *>(isGameLibLoaded)},
            {OBFUSCATE("Init"),  OBFUSCATE("(Landroid/content/Context;Landroid/widget/TextView;Landroid/widget/TextView;)V"), reinterpret_cast<void *>(Init)},
            {OBFUSCATE("SettingsList"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(SettingsList)},
            {OBFUSCATE("GetFeatureList"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList)},
			{OBFUSCATE("GetFeatureList2"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList2)},
			{OBFUSCATE("GetFeatureList3"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList3)},
			{OBFUSCATE("GetFeatureList4"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList4)},
		    {OBFUSCATE("GetFeatureList5"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList5)},
			{OBFUSCATE("GetFeatureList6"),  OBFUSCATE("()[Ljava/lang/String;"), reinterpret_cast<void *>(GetFeatureList6)},
    };

    jclass clazz = env->FindClass(OBFUSCATE("com/android/support/Menu"));
    if (!clazz)
        return JNI_ERR;
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) != 0)
        return JNI_ERR;
    return JNI_OK;
}

int RegisterPreferences(JNIEnv *env) {
    JNINativeMethod methods[] = {
            {OBFUSCATE("Changes"), OBFUSCATE("(Landroid/content/Context;ILjava/lang/String;IZLjava/lang/String;)V"), reinterpret_cast<void *>(Changes)},
    };
    jclass clazz = env->FindClass(OBFUSCATE("com/android/support/Preferences"));
    if (!clazz)
        return JNI_ERR;
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) != 0)
        return JNI_ERR;
    return JNI_OK;
}

int RegisterMain(JNIEnv *env) {
    JNINativeMethod methods[] = {
            {OBFUSCATE("CheckOverlayPermission"), OBFUSCATE("(Landroid/content/Context;)V"), reinterpret_cast<void *>(CheckOverlayPermission)},
    };
    jclass clazz = env->FindClass(OBFUSCATE("com/android/support/Main"));
    if (!clazz)
        return JNI_ERR;
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) != 0)
        return JNI_ERR;

    return JNI_OK;
}

extern "C"
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    vm->GetEnv((void **) &env, JNI_VERSION_1_6);
    if (RegisterMenu(env) != 0)
        return JNI_ERR;
    if (RegisterPreferences(env) != 0)
        return JNI_ERR;
    if (RegisterMain(env) != 0)
        return JNI_ERR;
    return JNI_VERSION_1_6;
}
