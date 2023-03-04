
-keepattributes Signature

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retrofit does reflection on the OkHttpClient class.
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Retrofit uses OkHttp for network access.
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**

# Gson
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-dontwarn com.google.gson.**

## Room
-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Dao { *; }
-keep class * extends androidx.room.Entity { *; }
-keep class * extends androidx.room.DatabaseView { *; }
-keep class * extends androidx.room.RawQuery { *; }
-keep class * extends androidx.room.Transaction { *; }
-keep class * extends androidx.room.OnConflictStrategy { *; }
-keep class * extends androidx.room.Insert { *; }
-keep class * extends androidx.room.Update { *; }
-keep class * extends androidx.room.Delete { *; }
-dontwarn androidx.room.**