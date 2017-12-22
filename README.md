# mapr-debug

Run this code with the command (if you are using linux, please change -macosx prefix for the maprfs jar in the classpath to -linux): 

```
$ java -Dshimloader.debuglog=yes -classpath out/production/mapr-jar/:maprfs-5.2.1-mapr-macosx.jar io.wicknicks.Main
```

Response:
--------

```
/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
com.mapr.fs.jni.MapRClient
2017-12-21 20:24:34.247 [1] Load in root Classloader: true.
2017-12-21 20:24:34.249 [1] Injecting Native Loader
2017-12-21 20:24:34.250 [1] getRootClassLoader: thread classLoader is 'sun.misc.Launcher.AppClassLoader'
2017-12-21 20:24:34.251 [1] getRootClassLoader: root classLoader is 'sun.misc.Launcher.ExtClassLoader'
2017-12-21 20:24:34.277 [1] injectNativeLoader: Loading MapR native classes
2017-12-21 20:24:34.286 [1] Searching for native library '/com/mapr/fs/native/Linux/x86_64/libMapRClient.so'.
2017-12-21 20:24:34.287 [1] Extracting native library to '/tmp'.
2017-12-21 20:24:34.287 [1] Native library for this platform is 'mapr-arjun-libMapRClient.unknown.so'.
2017-12-21 20:24:34.288 [1] Target file '/tmp/mapr-arjun-libMapRClient.unknown.so' already exists, verifying checksum.
2017-12-21 20:24:35.226 [1] Checksum matches, will not extract from the JAR.
2017-12-21 20:24:35.245 [1] Native library loaded.
2017-12-21 20:24:35.246 [1] Native Loader injected
Exception in thread "main" java.lang.UnsatisfiedLinkError: com.mapr.fs.jni.MapRClient.initSpoofedUser(Ljava/lang/String;ILjava/lang/String;I)I
	at com.mapr.fs.jni.MapRClient.initSpoofedUser(Native Method)
	at io.wicknicks.Main.main(Main.java:15)
```

The exception is the same as what we saw with the connect setup. Now, let's run the same command with the additional flag: 

```
java -Dshimloader.debuglog=yes -Dmapr.library.flatclass=true -classpath out/production/mapr-jar/:maprfs-5.2.1-mapr-macosx.jar io.wicknicks.Main
```

Response
--------

```
/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
com.mapr.fs.jni.MapRClient
2017-12-21 20:24:24.553 [1] Load in root Classloader: false.
2017-12-21 20:24:24.557 [1] Searching for native library '/com/mapr/fs/native/Linux/x86_64/libMapRClient.so'.
2017-12-21 20:24:24.558 [1] Extracting native library to '/tmp'.
2017-12-21 20:24:24.559 [1] Native library for this platform is 'mapr-arjun-libMapRClient.unknown.so'.
2017-12-21 20:24:24.559 [1] Target file '/tmp/mapr-arjun-libMapRClient.unknown.so' already exists, verifying checksum.
2017-12-21 20:24:25.504 [1] Checksum matches, will not extract from the JAR.
2017-12-21 20:24:25.523 [1] Native library loaded.
```

No exception, and the code runs this call to [MapRClient.initSpoofedUser](https://github.com/wicknicks/mapr-debug/blob/master/src/io/wicknicks/Main.java#L15) correctly.
