abstract class BaseResp {
  String? errCode;
  String? errString;
  String? transaction;
  String? openId;

  BaseResp.fromMap(Map<String, dynamic> map) {
    errCode = map["errCode"];
    errString = map["errString"];
    transaction = map["transactio"];
    openId = map["openId"];
  }

  Map<String, dynamic> toMap() => {
    "errCode": errCode,
    "errString": errString,
    "transaction": transaction,
    "openId": openId,
  };

  int get type;

}

class AuthReq extends BaseResp {
  String? scope;
  String? state;
  String? extData;

  AuthReq.fromMap(Map<String, dynamic> map) : super.fromMap(map) {
    scope = map["scope"];
    state = map["state"];
    extData = map["extData"];
  }

  Map<String, dynamic> toMap() => {
    "scope": scope,
    "state": state,
    "extData": extData
  };

  @override
  int get type => 1;

}

class AuthResponse extends BaseResp {
  String? code;
  String? state;
  bool authResult = false;
  String? url;
  String? lang;
  String? country;

  AuthResponse.fromMap(Map<String, dynamic> map) : super.fromMap(map) {
    code = map["code"];
    state = map["state"];
    authResult = map["authResult"];
    url = map["url"];
    lang = map["lang"];
    country = map["country"];
  }

  @override
  int get type => 1;
}