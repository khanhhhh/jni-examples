run: clib jclass
	LD_LIBRARY_PATH=. java HelloJNI
clib:
	g++ -std=c++17 -fPIC -shared -o libhello.so HelloJNI.cc -I/usr/lib/jvm/java-1.8.0/include/ -I/usr/lib/jvm/java-1.8.0/include/linux
jclass:
	javac HelloJNI.java
