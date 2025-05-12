Summary of Android language

1) asDemo01Core :
    Look files: MainActivity.java > Demo011SecondActivity.java > Demo012FourthActivity.java > Demo012ThirdActivity.java > Demo013FourthActivity.java > Demo014FifthActivity.java.
                activity_main.xml > activity_demo011_second.xml > activity_demo012_fourth.xml > activity_demo012_third.xml > activity_demo013_fourth.xml > activity_demo014_fifth.xml > strings.xml
                AndroidManifest.xml
2) asDemo02ImageAnimation : 
     Look files: MainActivity.java > activity_main.xml
3) asDemo03Video :
   Look files: MainActivity.java > activity_main.xml > AndroidManifest.xml
3) asDemo04Audio :
   Look files: MainActivity.java > activity_main.xml > AndroidManifest.xml
 

How to rename project: 
    - rename folder
    - in file settings.gradle (or settings.gradle.kts) change line: rootProject.name = "asDemo02ImageAnimation"
    - also check files build.gradle (or build.gradle.kts : applicationId та namespace), themes, strings, AndroidManifest.xml
    - rename modules if need: on the <-- left tree, on `app` Right Button Mouse, chose Refactor>Rename>Rename module.
    - if need, rename also modules down: on "demo" RBM Refactor>Rename
    - check settings.gradle (or settings.gradle.kts) if line: include(":appimg") became correct.
    - File → Sync Project with Gradle Files
    - Build → Clean Project
    - Build → Rebuild Project