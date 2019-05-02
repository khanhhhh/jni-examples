run: jclass clib
	@echo "RUNNING ..."
	@echo "---------------------"
	LD_LIBRARY_PATH=. java CObjectJNI
	@echo "---------------------"
	java -Djava.library.path=. CObjectJNI
	@echo "---------------------"
clean:
	rm -f *.h *.class *.so
clib:
	g++ -std=c++17 -fPIC -shared -o libCDouble.so CDouble.cc -I/usr/lib/jvm/java-1.8.0/include/ -I/usr/lib/jvm/java-1.8.0/include/linux
jclass:
	javac CObjectJNI.java
	javah CObjectJNI
