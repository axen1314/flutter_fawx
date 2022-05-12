
import 'dart:async';

import 'package:fawx/fawx_entities.dart';
import 'package:flutter/services.dart';

import 'fawx_exceptions.dart';

const MethodChannel _channel = MethodChannel('org.axen.flutter/fawx');

class _FAWXAPI {

  const _FAWXAPI();

  Future<bool> setup(String wechatAppId) async {
    bool? success = await _channel.invokeMethod("setup", {
      "wechatAppId": wechatAppId
    });
    return success??false;
  }

  Future<bool> get isWechatAppInstalled async {
    bool? isWechatAppInstalled = await _channel.invokeMethod("isWechatAppInstalled");
    return isWechatAppInstalled??false;
  }

  Future<AuthResponse> sendAuthReq(AuthReq req) async {
    Map<String, dynamic>? response = await _channel.invokeMapMethod("sendAuthReq", req.toMap());
    if (response != null) {
      return AuthResponse.fromMap(response);
    }
    throw FAWXNullPointerException("sendAuthReq: response == null");
  }

  Future<bool> openWechatApp() async {
    bool? isWechatAppOpened = await _channel.invokeMethod("openWechatApp");
    return isWechatAppOpened??false;
  }
}

const fawx = _FAWXAPI();
