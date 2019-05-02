#include"Pointer.h"
#include<cstdint>
#include<cstdlib>

uint8_t *JPointer2Pointer(jlong JPointer) {
	return reinterpret_cast<uint8_t*>(JPointer);
}
jlong Pointer2JPointer(uint8_t *pointer) {
	return reinterpret_cast<jlong>(pointer);
}

JNIEXPORT jlong JNICALL Java_Pointer_Cmalloc(JNIEnv *env, jclass JThis, jlong JSize) {
	uint64_t size = static_cast<uint64_t>(JSize);
	uint8_t *pointer = reinterpret_cast<uint8_t*>(std::malloc(size));
	jlong JPointer = Pointer2JPointer(pointer);
	return JPointer;
}
JNIEXPORT void JNICALL Java_Pointer_Cfree(JNIEnv *env, jclass JThis, jlong JPointer) {
	uint8_t *pointer = JPointer2Pointer(JPointer);
	std::free(pointer);
}
JNIEXPORT jint JNICALL Java_Pointer_Cget(JNIEnv *env, jclass JThis, jlong JPointer) {
	uint8_t *pointer = JPointer2Pointer(JPointer);
	uint8_t data = *pointer;
	jint JData = static_cast<jint>(data);
	return JData;
}
JNIEXPORT void JNICALL Java_Pointer_Cset(JNIEnv *env, jclass JThis, jlong JPointer, jint JData) {
	uint8_t *pointer = JPointer2Pointer(JPointer);
	uint8_t data = static_cast<uint8_t>(JData);
	*pointer = data;
}
