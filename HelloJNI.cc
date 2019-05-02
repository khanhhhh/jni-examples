#include<jni.h>
#include<iostream>
#include"HelloJNI.h"

extern "C" JNIEXPORT void JNICALL Java_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
	std::cout<<"Hello World from C++!"<<std::endl;
	return;
}
