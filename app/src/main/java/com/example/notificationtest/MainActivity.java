package com.example.notificationtest;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private NotificationManager mNotificationManager;

    private String groupId = "groupId";
    private CharSequence groupName = "Group1";

    private String groupId2 = "groupId2";
    private CharSequence groupName2 = "Group2";
    private String chatChannelId2 = "cha0tChannelId2";
    private String adChannelId2 = "adChannelId2";

    private String chatChannelId = "chatChannelId";
    private String chatChannelName = "聊天通知";
    private String chatChannelDesc = "这是一个聊天通知，建议您置于开启状态，这样才不会漏掉女朋友的消息哦";
    private int chatChannelImportance = NotificationManager.IMPORTANCE_MAX;

    private String adChannelId = "adChannelId";
    private String adChannelName = "广告通知";
    private String adChannelDesc = "这是一个广告通知，可以关闭的，但是如果您希望我们做出更好的软件服务于你，请打开广告支持一下吧";
    private int adChannelImportance = NotificationManager.IMPORTANCE_LOW;

    //测试权限一
    private CharSequence name="功能权限";
    private int CharImportance= NotificationManager.IMPORTANCE_MAX;

    //测试权限二
    private CharSequence name2="代码权限";
    private int CharImportance2= NotificationManager.IMPORTANCE_LOW;

    //权限二


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第一步   创建构造器
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Group();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Group(){
        getChannel();
        getChannelTwo();
    }

    //第二步 创建一个通知渠道
    //修改后，要先卸载软件重新安装，渠道才会重新生成
    //全局申明的变量不用传递，传递会无法弹出通知，找不到原因
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    //id  唯一，对这个这个权限组的后续操作需要它 ,不知道为什么不能调用全局变量
    //name 用户可见权限名
    //importance 渠道优先级
    //desc 对这个通知权限的描述
    //groupId 设置此权限组输入哪个群组
    public void getChannel(){
        //判断渠道是否存在
        if (mNotificationManager.getNotificationChannel("1") != null) return;
        Log.e("aaaaa","8.0创建"+name+"渠道");
        NotificationChannel channel=new NotificationChannel("1",name,CharImportance);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.RED); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        channel.setVibrationPattern(new long[]{100, 200, 300, 400});//设置震动频率
        channel.enableVibration(true);//开启震动
        channel.setShowBadge(true);//设置角标
        //channel.setDescription(desc);
        //使用这个需要特别申明
        //channel.setGroup(CharID);
        mNotificationManager.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    public void getChannelTwo(){
        Log.e("aaaaa","8.0创建"+name+"渠道2");
        NotificationChannel channel=new NotificationChannel("2",name2,CharImportance2);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.RED); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        channel.setVibrationPattern(new long[]{100, 200, 300, 400});//设置震动频率
        channel.enableVibration(true);//开启震动
        channel.setShowBadge(true);//设置角标
        //channel.setDescription(desc);
        //使用这个需要特别申明
        //channel.setGroup(CharID);
        mNotificationManager.createNotificationChannel(channel);
    }

    @SuppressLint("NewApi")
    //8.0已经不支持 NotificationCompat 这种写法了
    public void getTZ(View view){
        Log.e("aaaaa","8.0推送三步走");
        //getChannel(Id,name,CharImportance,CharDesc,CharID);
        //第三部 创建通知并发送
        NotificationCompat.Builder notification=new NotificationCompat.Builder(this,"1");
        notification.setContentTitle("通知")
                .setContentText("这是一个通知")
                .setWhen(System.currentTimeMillis())//通知生成时间
                .setSmallIcon(R.drawable.apple_pic)//小图
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.banana_pic))//大图
                .setNumber(3) //久按桌面图标时允许的此条通知的数量
                .build();
        mNotificationManager.notify(1,notification.build());

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getTZTwo(View view){
        Log.e("aaaaa","bbbbbbbbbbbbbb");
        //setUpNotifiction();
        Notification.Builder vvvv = new Notification.Builder(this, "1");
        vvvv.setSmallIcon(R.drawable.banana_pic)
                .setContentText("屹农金服11")
                .setNumber(3)
                .setContentTitle("屹农金服11");

        mNotificationManager.notify(2, vvvv.build());// 发送通知

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    //channdell 就是渠道的ID,过没有这个渠道，是没有消息的
    public void getTZThree(View view){
        Log.e("aaaaa","getTZThree");
        //mNotificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        Notification.Builder mNotification = new Notification.Builder(this, "2");
        mNotification.setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentText("屹农金服11")
                .setNumber(3)
                .setContentTitle("屹农金服11");

        mNotificationManager.notify((int) SystemClock.uptimeMillis(), mNotification.build());// 发送通知
    }

    @SuppressLint("NewApi")
    public void createNotificationChannel(String id, String name, int importance, String desc, String groupId) {
        if (mNotificationManager.getNotificationChannel(id) != null) return;

        NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);

        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationChannel.setShowBadge(true);
        notificationChannel.setBypassDnd(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400});
        notificationChannel.setDescription(desc);
        notificationChannel.setGroup(groupId);
//        notificationChannel.setSound();

        mNotificationManager.createNotificationChannel(notificationChannel);
    }

    @SuppressLint("NewApi")
    public void createGroup() {
        mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(groupId, groupName));
        mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(groupId2, groupName2));

        createNotificationChannel(chatChannelId2, chatChannelName, chatChannelImportance, chatChannelDesc, groupId);
        createNotificationChannel(adChannelId2, adChannelName, adChannelImportance, adChannelDesc, groupId);
    }

}
