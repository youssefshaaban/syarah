-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class javax.inject.** { *; }
-keep class androidx.hilt.lifecycle.ViewModelInject { *; }
-dontwarn dagger.hilt.**
-dontwarn dagger.hilt.android.**
-dontwarn androidx.hilt.lifecycle.ViewModelInject