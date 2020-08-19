
import json
import time
from trading_api import TradingApi
from wallet_api import WalletApi
from market_api import MarketApi
import const

#这里请填写自己申请的api key和secret
#申请流程可以参考以下文档
#https://www.bitcoke.com/api-doc/zh/rest.html#%E7%94%B3%E8%AF%B7-api-key
api_key = '***your api key***'
api_secret = '***your api secret***'

## Trading Api Example
# 交易只读 API

tradingApi = TradingApi(api_key, api_secret)

## get

#查询用户的交易账号信息。（60次/分/Key）
# response = tradingApi.queryAccounts()

#查询用户尚未完成的订单列表（包括委托单和条件单）。（60次/分/Key）
# response = tradingApi.queryActiveOrders()
# response = tradingApi.queryActiveOrders(const.SYMBOL_XBTCUSD)

#查询用户订单历史，订单按时间倒叙排列。（30次/分/Key）
# response = tradingApi.queryOrders()
# response = tradingApi.queryOrders("O101-20200708-092521-611-1647")

#根据订单 Id 查询订单。（5次/秒/Key）
# response = tradingApi.queryOrderById("O101-20200708-092521-611-1647")

#查询用户的当前持仓。（60次/分/Key）
# response = tradingApi.queryPosition()

#按币种分页查询用户交易账号的账本记录。（10次/分/Key）
# response = tradingApi.ledger(const.CURRENCY_EOS, '', 1, 10)


## post
# 交易 API 

#下单接口。（10次/秒/Key）
# orderData = {
#     "currency" : const.CURRENCY_EOS,
#     "openPosition" : True,
#     "orderType" : const.ORDER_TYPE_MARKET,
#     "qty" : 20,
#     "side" : const.SIDE_BUY,
#     "symbol" : const.SYMBOL_XBTCUSD,
# }
# response = tradingApi.enterOrder(orderData)

# orderData = {
#     "currency" : const.CURRENCY_EOS,
#     "openPosition" : True,
#     "orderType" : const.ORDER_TYPE_LIMIT,
#     "price" : 9200,
#     "qty" : 20,
#     "side" : const.SIDE_BUY,
#     "symbol" : const.SYMBOL_XBTCUSD,
# }
# response = tradingApi.enterOrder(orderData)

#根据订单Id进行撤单。（20次/秒/Key）
# response = tradingApi.cancelOrder("O101-20200708-082736-885-0361")

#修改订单信息。（5次/秒/Key）
# orderData = {
#     "orderId" : "O101-20200709-025019-115-0954",
#     "price" : 9201,
# }
# response = tradingApi.amendOrder(orderData)

#市价全部平仓。（4次/秒/Key）
# response = tradingApi.closePosition(const.CURRENCY_EOS, const.SYMBOL_XBTCUSD, const.SIDE_LONG)

#修改仓位杠杆设定。（4次/秒/Key）
# response = tradingApi.changePosLeverage(const.CURRENCY_EOS, const.SYMBOL_XBTCUSD, 20)

#修改仓位风控设定。（5次/秒/Key）
# settingData = {
#     "currency" : const.CURRENCY_EOS,
#     "side" : const.SIDE_LONG,
#     "symbol" : const.SYMBOL_XBTCUSD,
#     "addDeposit" : 0.001,
# }
# response = tradingApi.riskSetting(settingData)

#切换账号单双向持仓模式。（4次/秒/Key）
# response = tradingApi.switchPosSide(const.CURRENCY_EOS, True)

#将账号从逐仓模式切换为全仓模式。（4次/秒/Key）
# response = tradingApi.switchToCross(const.CURRENCY_EOS)


## Wallet Api Example

walletApi = WalletApi(api_key, api_secret)

#获取用户钱包列表。
# response = walletApi.list()

#获取用户充币地址。
# response = walletApi.depositAddress()

#获取用户钱包账簿。
# response = walletApi.ledger()


## Market Api Example
#行情及报价接口可直接使用，不需要申请 API KEY；请求频率根据发出请求服务器的 IP 限制。

marketApi = MarketApi()

#获取合约信息。（1次/秒/IP）
# response = marketApi.refData()

#获取合约最新成交价。（1次/秒/IP）
# response = marketApi.lastPrice()
# response = marketApi.lastPrice(const.SYMBOL_XBTCUSD, const.SYMBOL_XETHUSD)

#获取标记价。（5次/秒/IP）
# response = marketApi.price()
# response = marketApi.price(const.SYMBOL_BTCUSD, const.SYMBOL_ETHUSD)

#获取市场深度。（5次/秒/IP）
# response = marketApi.depth(const.SYMBOL_XBTCUSD)

#获取最新成交记录。（5次/秒/IP）
# response = marketApi.trades(const.SYMBOL_XBTCUSD)

#获取市场深度和最近成交记录。（5次/秒/IP）
# response = marketApi.quotes(const.SYMBOL_XBTCUSD)
# response = marketApi.quotes(const.SYMBOL_XBTCUSD, ['1594621313287000012', '1594621313287000015'])

#获取历史 K 线以及成交统计相关数据。（15次/分/IP）
# response = marketApi.kLine_byIndex(const.SYMBOL_XBTCUSD, const.K_LINE_TYPE_1M, 10, 0)

#通过时间索引获取历史 K 线。（15次/分/IP）
# response = marketApi.kLine_byTime(const.SYMBOL_XBTCUSD, const.K_LINE_TYPE_1M, 10)

#获取最新一根 K 线数据。（75次/分/IP）
# response = marketApi.kLine_latest(const.SYMBOL_XBTCUSD, const.K_LINE_TYPE_1M)

#获取上次拉取 K 线后的更新数据。（75次/分/IP）
# response = marketApi.kLine_latestPeriod(const.SYMBOL_XBTCUSD, const.K_LINE_TYPE_1M, int(time.time()))

#获取资金费率。（5次/秒/IP）
# response = marketApi.kLine_fundingRate(const.SYMBOL_XBTCUSD, const.SYMBOL_XETHUSD)

#获取最近24小时的成交统计数据。（5次/秒/IP）
# response = marketApi.kLine_tradeStatistics(const.SYMBOL_XBTCUSD, const.SYMBOL_XETHUSD)

#获取系统合约持仓量。（5次/秒/IP）
# response = marketApi.kLine_openInterest(const.SYMBOL_BTCUSD)


if response.status_code == 200:
    response_data = json.loads(response.text)
    print(response_data)
    # result = (json.dumps(response_data['result']) if response_data['code'] == 0 else response_data['message'])
    # print(result)
else:
	print(response.text)

