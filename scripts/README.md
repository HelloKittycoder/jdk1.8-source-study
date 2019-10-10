### 脚本说明
#### compileSourceCode.bat  
在给源码加完注释后，调试时进入断点时bytecode和代码能够同步  
（编译src目录源码，输出为rt_debug.jar，然后复制到endorsed目录下）  

详细说明：（jdk源码调试时无法显示变量信息）以上脚本的手动操作步骤  
把源码解压放到f盘（放到根目录，这样路径不用写太长），在f盘创建jdk_debug目录  

进到f盘根目录，打开cmd命令窗口  
1. 生成需要编译的文件列表  
dir /B /S /X src\*.java > filelist.txt  

2. 执行编译操作（rt.jar是在JDK_HOME\jre\lib下找的，tools.jar是在JDK_HOME\lib下找的）  
javac -J-Xms16m -J-Xmx1024m -sourcepath f:\src -cp f:\rt.jar;f:\tools.jar -d f:\jdk_debug -g @filelist.txt >> log.txt 2>&1  
（如果只是编译一小部分，只用rt.jar没问题；要编译完整的，就还得加上tools.jar）  

3. 生成rt_debug.jar  
进入jdk_debug目录执行命令jar cf0 rt_debug.jar *  

4. 把新生成的jar包放到JDK_HOME\jre\lib\endorsed中（如果没有endorsed文件夹，则手动创建）  

参考链接：https://www.jb51.net/article/115931.htm  
https://blog.csdn.net/wl23301/article/details/35792235  
https://stackoverflow.com/questions/18255474/debug-jdk-source-cant-watch-variable-what-it-is  

这种写法如果在需要给jdk源码加注释的情况下就不适用了，需要边加注释，边进行修改