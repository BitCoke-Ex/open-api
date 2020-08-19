
from clientbase import ClientBase


class TradingApi(ClientBase):
    def __init__(self, api_key, api_secret):
        super(TradingApi, self).__init__(api_key, api_secret)

    def queryAccounts(self):
        params = {}
        respone = self._get_trade("/api/trade/queryAccounts", params)
        return respone

    def queryActiveOrders(self, symbol=''):
        params = {"symbol" : symbol}
        respone = self._get_trade("/api/trade/queryActiveOrders", params)
        return respone

    def queryOrders(self, prevId=''):
        params = {"prevId" : prevId}
        respone = self._get_trade("/api/trade/queryOrders", params)
        return respone

    def queryOrderById(self, orderId):
        params = {"orderId" : orderId}
        respone = self._get_trade("/api/trade/queryOrderById", params)
        return respone

    def queryPosition(self):
        params = {}
        respone = self._get_trade("/api/trade/queryPosition", params)
        return respone

    def ledger(self, currency, fromId, pageNo, pageSize):
        params = {
            "currency" : currency,
            "from" : fromId,
            "pageNo" : pageNo,
            "pageSize" : pageSize,
        }
        respone = self._get_trade("/api/trade/ledger", params)
        return respone


    def enterOrder(self, data):
        params = {
            "currency" : data.get("currency"),
            "symbol" : data.get("symbol"),
            "openPosition" : data.get("openPosition"),
            "side" : data.get("side"),
            "qty" : data.get("qty"),
            "orderType" : data.get("orderType"),
            "price" : data.get("price") or '',
            "hidden" : data.get("hidden") or False,
            "showQty" : data.get("showQty") or '',
            "stopLossPrice" : data.get("stopLossPrice") or '',
            "stopWinPrice" : data.get("stopWinPrice") or '',
            "stopWinType" : data.get("stopWinType") or '',
            "tif" : data.get("tif") or '',
            "trailingStop" : data.get("trailingStop") or '',
            "triggerPrice" : data.get("triggerPrice") or '',
            "triggerType" : data.get("triggerType") or '',
        }
        respone = self._post_trade("/api/trade/enterOrder", params)
        return respone

    def cancelOrder(self, orderId):
        params = { "orderId" : orderId }
        respone = self._post_trade("/api/trade/cancelOrder", params)
        return respone

    def amendOrder(self, data):
        params = {
            "orderId" : data.get("orderId"),
            "price" : data.get("price") or '',
            "qty" : data.get("qty") or '',
            "stopLossPrice" : data.get("stopLossPrice") or '',
            "stopWinPrice" : data.get("stopWinPrice") or '',
            "trailingStop" : data.get("trailingStop") or '',
            "triggerPrice" : data.get("triggerPrice") or '',
        }
        respone = self._post_trade("/api/trade/amendOrder", params)
        return respone

    def closePosition(self, currency, symbol, side):
        params = {
            "currency" : currency,
            "symbol" : symbol,
            "side" : side,
        }
        respone = self._post_trade("/api/trade/closePosition", params)
        return respone

    def changePosLeverage(self, currency, symbol, leverage):
        params = {
            "currency" : currency,
            "symbol" : symbol,
            "leverage" : leverage,
        }
        respone = self._post_trade("/api/trade/changePosLeverage", params)
        return respone

    def riskSetting(self, data):
        params = {
            "currency" : data.get("currency"),
            "side" : data.get("side"),
            "symbol" : data.get("symbol"),
            "addDeposit" : data.get("addDeposit") or '',
            "stopLossPrice" : data.get("stopLossPrice") or '',
            "stopWinPrice" : data.get("stopWinPrice") or '',
            "stopWinType" : data.get("stopWinType") or '',
            "trailingStop" : data.get("trailingStop") or '',
        }
        respone = self._post_trade("/api/trade/riskSetting", params)
        return respone

    def switchPosSide(self, currency, twoSidePosition):
        params = {
            "currency" : currency,
            "twoSidePosition" : twoSidePosition,
        }
        respone = self._post_trade("/api/trade/switchPosSide", params)
        return respone

    def switchToCross(self, currency):
        params = { "currency" : currency }
        respone = self._post_trade("/api/trade/switchToCross", params)
        return respone

