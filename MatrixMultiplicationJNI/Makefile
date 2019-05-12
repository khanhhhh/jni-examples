all: clib
	javac *.java
	java -Djava.library.path=. main
	rm -f *.class
clib:
	g++ -O3 -std=c++17 -fPIC -shared -o libMatrix.so Matrix.cc -I/usr/lib/jvm/java-1.8.0/include/ -I/usr/lib/jvm/java-1.8.0/include/linux
clean:
	rm -f *.class
