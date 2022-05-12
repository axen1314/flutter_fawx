package org.axen.flutter.fawx

import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth

private fun toBaseMap(resp: BaseResp): MutableMap<String, Any> = mutableMapOf(
    "errCode" to resp.errCode,
    "errString" to resp.errStr,
    "openId" to resp.openId,
    "transaction" to resp.transaction,
    "type" to resp.type
)

fun BaseResp.toMap(): Map<String, Any> = toBaseMap(this).toMap()

fun SendAuth.Resp.toMap(): Map<String, Any> = toBaseMap(this).apply {
    "type" to type
    "code" to code
    "state" to state
    "country" to country
    "lang" to lang
    "url" to url
    "authResult" to authResult
}.toMap()