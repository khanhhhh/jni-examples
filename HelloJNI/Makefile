run: jclass clib
	@echo "RUNNING ..."
	@echo "---------------------"
	LD_LIBRARY_PATH=. java HelloJNI
	@echo "---------------------"
	java -Djava.library.path=. HelloJNI
	@echo "---------------------"
clean:
	rm -f *.h *.class *.so
clib:
	g++ -std=c++17 -fPIC -shared -o libHelloJNI.so HelloJNI.cc -I/usr/lib/jvm/java-1.8.0/include/ -I/usr/lib/jvm/java-1.8.0/include/linux
jclass:
	javac HelloJNI.java
	javah HelloJNI
