//
// Created by Rakan Alotaibi on 23/08/2023 AD.
//

#include "native.h"
#include "jni.h"
#include "string.h"


jstring Java_live_rakan_detector_MainActivity_helloWorld(JNIEnv* env,jobject obj){
    return (*env)->NewStringUTF(env,"Hello World");
}
