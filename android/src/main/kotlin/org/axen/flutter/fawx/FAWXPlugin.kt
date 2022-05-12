package org.axen.flutter.fawx

import android.content.Context
import com.tencent.mm.opensdk.modelmsg.SendAuth
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.axen.fawx.FAWXAPI
import org.axen.fawx.IFAWXOnReqInterceptor


class FAWXPlugin: FlutterPlugin, MethodCallHandler {
  private var context: Context? = null
  private lateinit var channel : MethodChannel
  private lateinit var interceptor: IFAWXOnReqInterceptor

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "org.axen.flutter/fawx")
    channel.setMethodCallHandler(this)
    interceptor = FAWXOnReqInterceptor(channel)
    FAWXAPI.interceptors.add(interceptor)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when(call.method) {
      "setup" -> setup(call, result)
      "isWechatInstalled" -> isWechatInstalled(result)
      "sendAuthReq" -> sendAuthReq(call, result)
      "openWechatApp" -> openWechatApp(result)
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    context = null
    channel.setMethodCallHandler(null)
    FAWXAPI.interceptors.remove(interceptor)
  }

  /**
   * 初始化微信SDK
   */
  private fun setup(call: MethodCall, result: Result) {
    context?.let {
      val wechatAppId = call.argument<String>("wechatAppId")
      if (wechatAppId.isNullOrBlank()) {
        result.error("-1", "wechatAppId is null or empty!", null)
      } else {
        result.success(FAWXAPI.setup(it, wechatAppId))
      }
    }
  }

  /**
   * 判断微信是否已安装
   */
  private fun isWechatInstalled(result: Result) {
    result.success(FAWXAPI.isWechatAppInstalled())
  }

  /**
   * 微信登录授权
   */
  private fun sendAuthReq(call: MethodCall, result: Result) {
    val req = SendAuth.Req().apply {
      FAWXUtils.authReqFromMap(this, call.arguments as Map<String, Any?>)
    }
    FAWXAPI.sendReq(req) { result.success(it.toMap()) }
  }

  /**
   * 打开微信APP
   */
  private fun openWechatApp(result: Result) {
    result.success(FAWXAPI.openWechatApp())
  }
}
