# jni-examples

1. Write JAVA files

2. Compile JAVA files with javac

3. Generate header files with `javah <classname>`

4. Write c source files based on generated headers.

    jint  = int
    
    jlong = long
    
    ...

5. Specify `-Djava.library.path` or `LD_LIBRARY_PATH` in runtime.
