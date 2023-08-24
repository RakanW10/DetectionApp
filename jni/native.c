//
// Created by Rakan Alotaibi on 23/08/2023 AD.
//

#include "native.h"
#include "jni.h"
#include <stdio.h>
#include "string.h"
#include <android/log.h>

JNIEXPORT void JNICALL Java_live_rakan_detector_MainActivity_checkFrida(JNIEnv* env, jobject obj) {
       FILE *fp;
       char line[1024];

       fp = fopen("/proc/self/maps", "r");
       if (fp == NULL) {
           perror("Error opening maps file");
           return;
       }

       while (fgets(line, sizeof(line), fp)) {
           if (strstr(line, "frida-agent") != NULL) {
               __android_log_print(ANDROID_LOG_INFO, "found", "%s", line);
               fp = fopen("/proc/self/smaps", "r");
               fgets(line, sizeof(line), fp);
           }
       }

       fclose(fp);

}
