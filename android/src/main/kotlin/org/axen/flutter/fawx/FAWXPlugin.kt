package org.axen.flutter.fawx

import com.tencent.mm.opensdk.modelmsg.SendAuth
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.axen.fawx.FAWXAPI
import org.axen.fawx.IFAWXOnReqInterceptor


class FAWXPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var interceptor: IFAWXOnReqInterceptor

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "org.axen.flutter/fawx")
    channel.setMethodCallHandler(this)
    interceptor = FlutterFAWXAPIInterceptor(channel)
    FAWXAPI.interceptors.add(interceptor)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when(call.method) {
      "isWechatInstalled" -> isWechatInstalled(result)
      "sendAuthReq" -> sendAuthReq(call, result)
      "openWechatApp" -> openWechatApp(result)
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    FAWXAPI.interceptors.remove(interceptor)
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
    val req = SendAuth.Req()
    req.state = call.argument<String>("state")
    req.scope = call.argument<String>("scope")
    val openId = call.argument<String>("openId")
    if (!openId.isNullOrBlank()) {
      req.openId = openId
    }
    FAWXAPI.sendReq(req) { result.success(it.toMap()) }
  }

  /**
   * 打开微信APP
   */
  private fun openWechatApp(result: Result) {

  }
}
