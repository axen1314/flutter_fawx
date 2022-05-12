import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:fawx/fawx.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String isWechatAppInstalled = '未安装微信';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    bool isWechatAppInstalled = await fawx.isWechatAppInstalled;
    if (!mounted) return;

    setState(() {
      this.isWechatAppInstalled = isWechatAppInstalled ? "已安装微信" : "未安装微信";
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text(isWechatAppInstalled),
        ),
      ),
    );
  }
}
