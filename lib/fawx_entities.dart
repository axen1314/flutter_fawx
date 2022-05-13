abstract class BaseResp {
  int? errCode;
  String? errString;
  String? transaction;
  String? openId;

  BaseResp();

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

  bool get isSuccessful => errCode == 0;

  int get type;

}

class AuthReq extends BaseResp {
  String? scope;
  String? state;
  String? extData;

  AuthReq();

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

class AuthResp extends BaseResp {
  String? code;
  String? state;
  bool authResult = false;
  String? url;
  String? lang;
  String? country;

  AuthResp();

  AuthResp.fromMap(Map<String, dynamic> map) : super.fromMap(map) {
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