# b1rd_ch4in
An utility to reboot Android phones in differents modes with a click

# can i use that without root?
No. There isn't an API which allow to manage Android boot modes. To use this app Is required to root Android and grant su permissions (superuser)

# developer note
If you want to explore and test the app without a rooted phone, you can modify the project yourself. First, you need a text editor like VS Code. I recomend to use Android Studio. Than, you have to open **MainActivity.kt** file and modifying the line 38 and then, you can modify the string from *if (!shouldOpenApp != true)* to *if (!shouldOpenApp == true)*. The modication on line 38 is only != -> == . I created that double negation so if you're a developer you can bypass that security method

# thanks to 
85cs/Itelcan3 (aka. MasterSharp2210). Special thanks to @franciplay58
