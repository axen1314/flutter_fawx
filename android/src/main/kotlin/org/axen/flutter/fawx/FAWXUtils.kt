package org.axen.flutter.fawx

import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth

object FAWXUtils {
    fun baseRespToMap(resp: BaseResp, map: MutableMap<String, Any?>) {
        map.apply {
            "errCode" to resp.errCode
            "errStr" to resp.errStr
            "openId" to resp.openId
            "transaction" to resp.transaction
        }
    }

    fun authRespToMap(resp: SendAuth.Resp, map: MutableMap<String, Any?>){
        baseRespToMap(resp, map)
        map.apply {
            "code" to resp.code
            "state" to resp.state
            "country" to resp.country
            "lang" to resp.lang
            "url" to resp.url
            "authResult" to resp.authResult
        }
    }

    fun baseReqFromMap(req: BaseReq, map: Map<String, Any?>) {
        req.apply {
            openId = map["openId"] as String?
            transaction = map["transaction"] as String?
        }
    }

    fun authReqFromMap(req: SendAuth.Req, map: Map<String, Any?>) {
        baseReqFromMap(req, map)
        req.apply {
            scope = map["scope"] as String?
            state = map["state"] as String?
            extData = map["extData"] as String?
        }
    }

}