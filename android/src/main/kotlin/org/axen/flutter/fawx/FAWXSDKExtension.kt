package org.axen.flutter.fawx

import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth

fun BaseResp.toMap(): Map<String, Any?> = mutableMapOf<String, Any?>().also{
    FAWXUtils.baseRespToMap(this, it)
}.toMap()

fun SendAuth.Resp.toMap(): Map<String, Any?> = mutableMapOf<String, Any?>().also{
    FAWXUtils.authRespToMap(this, it)
}

