/**
 * 处理来自微信的跳转请求
 */
package org.axen.flutter.fawx

import android.content.Context
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX
import io.flutter.plugin.common.MethodChannel
import org.axen.fawx.FAWXOnReqInterceptorProcessor
import org.axen.fawx.IFAWXOnReqInterceptor

class FlutterFAWXAPIInterceptor(private val channel: MethodChannel): IFAWXOnReqInterceptor {

    override fun intercept(
        context: Context,
        req: BaseReq,
        processor: FAWXOnReqInterceptorProcessor
    ) {
       when (req) {
           is ShowMessageFromWX.Req -> handleShowMessageFromWXReq(req)
           else -> processor.proceed(context, req) // 交给其他拦截器处理
       }
    }

    private fun handleShowMessageFromWXReq(req: ShowMessageFromWX.Req) {

    }
}