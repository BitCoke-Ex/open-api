
import websockets
import asyncio
import time
import json
import hashlib


def get_signature(api_secret, requestMethod, path, expires):
    origin_text = api_secret + requestMethod + path + str(expires)
    signature = hashlib.sha256(origin_text.encode('utf-8')).hexdigest()
    return signature

def login_str(uid, password, txId):
    pwdsha = hashlib.sha1(password.encode('utf-8')).hexdigest()
    param = {"op":"login", "user":uid, "password":pwdsha, "txId":txId}
    return json.dumps(param)

def heartbeat_str(uid, txId):
    param = {"op":"heartbeat", "user":uid, "txId":txId}
    return json.dumps(param)

def subscript_str(api_key, api_secret, channel):
    expires = int(time.time()) + 10
    param = {
        "apiKey": api_key,
        "signature": get_signature(api_secret, 'GET', channel, expires),
        "expires": expires,
        "op": "subscribe",
        "channel": channel,
    }
    return json.dumps(param)

def unsubscript_str(api_key, channel):
    param = {
        "apiKey": api_key,
        "op": "unsubscribe",
        "channel": channel,
    }
    return json.dumps(param)

async def subscribe_trade(url, api_key, api_secret, uid, password, txId):
    while True:
        try:
            async with websockets.connect(url) as ws:
                rec = await ws.recv()
                print("connect:", rec)

                #login
                loginStr = login_str(uid, password, txId)
                await ws.send(loginStr)
                rec = await ws.recv()
                print(rec)
                loginResult = json.loads(rec)
                if loginResult.get("errCode") != 0:
                    print("Login fail")
                    break

                #subscript examples
                #订阅接收账号的动态更新
                sub_str = subscript_str(api_key, api_secret, "/api/trade/accountUpdate")
                await ws.send(sub_str)

                #订阅订单数据更新
                sub_str = subscript_str(api_key, api_secret, "/api/trade/orderUpdate")
                await ws.send(sub_str)

                #订阅仓位数据更新
                sub_str = subscript_str(api_key, api_secret, "/api/trade/positionUpdate")
                await ws.send(sub_str)

                #订阅合约账号出入金数据更新
                sub_str = subscript_str(api_key, api_secret, "/api/trade/tradeLedgerUpdate")
                await ws.send(sub_str)

                while True:
                    try:
                        rec = await asyncio.wait_for(ws.recv(), timeout=30)
                    except (asyncio.TimeoutError, websockets.exceptions.ConnectionClosed) as e:
                        try:
                            heartbeatStr = heartbeat_str(uid, txId)
                            await ws.send(heartbeatStr)
                            rec = await ws.recv()
                            print(rec)
                            continue
                        except Exception as e:
                            print(e)
                            break
                    print(rec)

        except Exception as e:
            print(e)
            print("Try reconnect...")
            continue

def subscript_str_without_login(channel, **kwargs):
    param = {
        "op": "subscribe",
        "channel": channel,
    }
    param.update(kwargs)
    return json.dumps(param)

def unsubscript_str_without_login(channel):
    param = {
        "op": "unsubscribe",
        "channel": channel,
    }
    return json.dumps(param)

async def subscribe_market(url):
    while True:
        try:
            async with websockets.connect(url) as ws:
                rec = await ws.recv()
                print("connect:", rec)

                #subscript examples
                #标记价
                # sub_str = subscript_str_without_login("/api/index/price", key="BTCUSD")
                # await ws.send(sub_str)

                #深度
                # sub_str = subscript_str_without_login("/api/depth/depth", key="XBTCUSD")
                # await ws.send(sub_str)

                #资金费率
                # sub_str = subscript_str_without_login("/api/kLine/fundingRate", key="XBTCUSD")
                # await ws.send(sub_str)

                #持仓量
                # sub_str = subscript_str_without_login("/api/kLine/openInterest", key="BTCUSD")
                # await ws.send(sub_str)

                #24小时成交统计
                # sub_str = subscript_str_without_login("/api/kLine/tradeStatistics", key="BTCUSD")
                # await ws.send(sub_str)

                #K线更新
                sub_str = subscript_str_without_login("/api/kLine/kLine", key="XBTCUSD", type="1M")
                await ws.send(sub_str)

                while True:
                    rec = await ws.recv()
                    print(rec)

        except Exception as e:
            print(e)
            time.sleep(1)
            print("Try reconnect...")
            continue




url_trade = 'wss://api.bitcoke.com/ws/trade'
url_market = 'wss://api.bitcoke.com/ws/market'

#这里填写自己申请的api key和secret，申请流程参考以下文档
#https://www.bitcoke.com/api-doc/zh/#%E7%94%B3%E8%AF%B7-api-key
api_key = '***your api key***'
api_secret = '***your api secret***'

#这里填写自己的uid和密码
uid = 'your id'
password = 'your password'
txId = uid + "_" + str(int(time.time()))

loop = asyncio.get_event_loop()

#个人交易数据，需要登录，需要api key和secret
# loop.run_until_complete(subscribe_trade(url_trade, api_key, api_secret, uid, password, txId))

#行情数据，不需要登录
loop.run_until_complete(subscribe_market(url_market))

loop.close()

