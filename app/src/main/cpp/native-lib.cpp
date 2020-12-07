//
// Created by reyhan on 07/12/20.
//

#include <jni.h>

extern "C" JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_reyhan_labtktpl_views_MainActivity_incrementNumber(
        JNIEnv *env, jobject obj, jint num) {
    return num + 1;
}