package org.axen.flutter.fawx

import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth

object FAWXUtils {
    fun baseRespToMap(resp: BaseResp, map: MutableMap<String, Any?>) {
        map.apply {
            this["errCode"] = resp.errCode
            this["errStr"] =  resp.errStr
            this["openId"] = resp.openId
            this["transaction"] = resp.transaction
        }
    }

    fun authRespToMap(resp: SendAuth.Resp, map: MutableMap<String, Any?>){
        baseRespToMap(resp, map)
        map.apply {
            this["code"] = resp.code
            this["state"] = resp.state
            this["country"] = resp.country
            this["lang"] = resp.lang
            this["url"] = resp.url
            this["authResult"] = resp.authResult
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