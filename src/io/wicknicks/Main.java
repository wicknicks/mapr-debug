package io.wicknicks;

import com.mapr.fs.ShimLoader;
import com.mapr.fs.jni.MapRClient;

public class Main {

    public static void main(String[] args) {
        Runtime.getRuntime().traceMethodCalls(true);
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(MapRClient.class.getName());
        ShimLoader.load();
//        System.load("/home/arjun/Sandbox/clones/mapr-jar/libMapRClient.so");
//        System.loadLibrary("MapRClient");
        MapRClient.initSpoofedUser("arjun", 5, "pass", 4);
    }
}
