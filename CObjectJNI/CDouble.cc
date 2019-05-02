#include"CDouble.h"
#include<string>
#include<iostream>
JNIEXPORT jlong JNICALL Java_CDouble_newDOUBLE(JNIEnv *env, jclass JThisObj, jdouble Jvalue) {
	double *Obj = new double();
	*Obj = static_cast<double>(Jvalue);
	jlong JObj = reinterpret_cast<jlong>(Obj);
	return JObj;
}
JNIEXPORT void JNICALL Java_CDouble_delDOUBLE(JNIEnv *env, jclass JThisObj, jlong JObj) {
	double *Obj = reinterpret_cast<double*>(JObj);
	delete Obj;
	Obj = nullptr;
}
JNIEXPORT jstring JNICALL Java_CDouble_toStringDOUBLE(JNIEnv *env, jclass JThisObj, jlong JObj) {
	double *Obj = reinterpret_cast<double*>(JObj);
	std::string s = std::to_string(*Obj);
	auto JString = env->NewStringUTF(s.c_str());
	return JString;
}
JNIEXPORT void JNICALL Java_CDouble_addDOUBLE(JNIEnv *env, jclass JThisObj, jlong JObj1, jlong JObj2, jlong JObj3) {
	double *Obj1 = reinterpret_cast<double*>(JObj1);
	double *Obj2 = reinterpret_cast<double*>(JObj2);
	double *Obj3 = reinterpret_cast<double*>(JObj3);
	*Obj3 = *Obj1 + *Obj2;
}
