# CoverFlowView

  基于[ImageCoverFlow](https://github.com/dolphinwang/ImageCoverFlow)，但是有较大改动，在ImageCoverFlow的基础上增加了
  对自定义布局的支持，可以支持任意布局。同时增加了一些其他支持，并修复了一些bug，提升绘制速度。

  Based on [ImageCoverFlow](https://github.com/dolphinwang/ImageCoverFlow), add support to custom
  layout in CoverFlow, fix some bugs, add other function.

---
  GIF预览：

  ![gif](https://raw.githubusercontent.com/missmess/CoverFlowView/master/raw/sample.gif)

---

  * [主要功能介绍](#主要功能介绍)
  * [如何添加到项目中](#如何添加到项目中)
  * [如何使用](#如何使用)
  * [其它API](#其它API)
  * [截图](#截图)
  * [关于作者](#关于作者)

---

### 主要功能介绍

* 支持自定义布局。
* 支持多种view切换方式。
* 支持view on top， top click， long click监听。
* 支持notify刷新数据源和重设适配器。

---

### 如何添加到项目中

本library已经支持Gradle直接添加远程依赖。Android Studio用户，只需要在项目的build.gradle中添加该dependencies：

  `
    compile "com.missmess.coverflowview:coverflowview:1.2.4"
  `

---

###如何使用

使用非常简单。仅需几句代码。用法如下：
###### 1、在xml中定义布局。
```xml
<com.missmess.coverflowview.CoverFlowView
    android:id="@+id/coverflow_view"
    android:layout_width="match_parent"
    android:layout_height="180dp"
    app:loopMode="true"
	app:visibleViews="3"/>
```
###### 2、创建adapter。继承于ACoverFlowAdapter。adapter的实现与RecyclerView.Adapter完全相同。
```java
public class MyCoverFlowAdapter extends ACoverFlowAdapter<MyCoverFlowAdapter.ViewHolder> {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
    }

    class ViewHolder extends ACoverFlowAdapter.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
```

###### 3、为CoverFlowView设置adapter。
```java
CoverFlowView coverflow_view = (CoverFlowView) findViewById(R.id.coverflow_view);
MyCoverFlowAdapter adapter = new MyCoverFlowAdapter();
coverflow_view.setAdapter(adapter);
```
---

### 其它API
###### 1、切换CoverFlow
* 你可以通过滑动切换，也可以通过点击CoverFlow的左右两侧实现快速切换。但点击切换需要确保clickSwitchEnable为
true。

  通过coverFlowView.setClick2SwitchEnabled(boolean enable)方法设置是否启用点击左右侧切换。

* 如果你想在在代码中切换view。可以有如下的几种方法：

  1. gotoPrevious()：当前位置往前切换一个。

  2. gotoForward()：当前位置往后切换一个。

  3. setSelection(int selection, boolean smooth)：切换到指定的位置，smooth为true则显示过渡动画，为false
则快速切换。

###### 2、更新数据
* 你可以直接使用adapter.notifyDataSetChanged()来刷新你的适配器数据。
* 也可以使用新的adapter：
```java
coverflow_view.setAdapter(new NewCoverFlowAdapter());
```

###### 3、设置监听
CoverFlowView提供三种lister：

* setOnViewOnTopListener：当任意一个新的view停止滑动后，位于顶部。就会调用。这个是最常用的。
* setOnTopViewClickListener：当位于顶部的view被点击后调用。
* setOnTopViewLongClickListener：当位于顶部的view接收了long click事件时调用。


###### 4、循环模式
使用setLoopMode(boolean)方法或者在xml中定义loopMode来启用和关闭循环模式。
在循环模式下，将可以无限向左和向右滑动，item将会循环显示。关闭后，滑动到第一个或者最后一个item，将
不能继续向左或向右滑动。


---

### 截图

  ![image1](https://raw.githubusercontent.com/missmess/CoverFlowView/master/raw/screenshot_1.jpg)
  ![image2](https://raw.githubusercontent.com/missmess/CoverFlowView/master/raw/screenshot_2.jpg)
  ![image3](https://raw.githubusercontent.com/missmess/CoverFlowView/master/raw/screenshot_3.jpg)

---

### 未完成工作

  目前所有工作在onLayout中完成。应该把addView和transform children的工作给剥离出onLayout中。这样可以
  提高性能。

### 关于作者
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流：

* 邮箱：<tarcy3620@126.com>
* GitHub: [@missmess](https://github.com/missmess)

---
###### CopyRight：`missmess`
