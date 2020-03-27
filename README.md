# ShapeDrawableLED

菱形渐变动画，像素采集，颜色采集，自定义矩形展示，LED项目

这个项目主要来源于网上，我东拼西凑弄出来的，性能应该还能在优化，比如将部分UI替换成sofaceview来渲染

为了兼容更多的动画效果，我采用view投影的方式实现，就是你用view实现一个动画效果，我会自动分成矩形点状进行投影

具体的看源码吧，适合LED控制项目，菱形UI市面上没有发现，自己实现了思路，最后是GcsSloop大佬帮忙弄出来的，可以给大家做一个学习参考
https://github.com/GcsSloop
大佬文章深入浅出，大家可以star看看


关于动画如果不符合自己的需求怎么办，可以用lottie-android-master项目进行组合，目前没做，后期有空维护的话在弄上去
https://github.com/airbnb/lottie-android

让美工用AE实现出来，然后转成JSON格式，需要一个插件，具体步骤自己查，然后集成该框架进行绘制，最后在投影上去就可以了

演示视频


![image](https://github.com/TsaiYongChuan/ShapeDrawableApplication/blob/master/ezgif.com-optimize.gif)

可以自动适应矩阵形状


![image](https://github.com/TsaiYongChuan/ShapeDrawableApplication/blob/master/ezgif.com-video-to-gif.gif)
