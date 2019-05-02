run: jclass clib
	@echo "RUNNING ..."
	@echo "---------------------"
	java -Djava.library.path=. PointerApp
	@echo "---------------------"
clean:
	rm -f *.h *.class *.so
clib:
	g++ -std=c++17 -fPIC -shared -o libPointer.so Pointer.cc -I/usr/lib/jvm/java-1.8.0/include/ -I/usr/lib/jvm/java-1.8.0/include/linux
jclass:
	javac Pointer.java
	javah Pointer
	javac PointerApp.java
