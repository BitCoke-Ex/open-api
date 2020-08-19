
from clientbase import ClientBase

class MarketApi(ClientBase):
    def __init__(self):
        super(MarketApi, self).__init__(None, None)

    def refData(self):
        params = {}
        respone = self._get("/api/basic/refData", params)
        return respone

    def lastPrice(self, *symbols):
        params = { "symbols" : ','.join(symbols) } if len(symbols) > 0 else {}
        respone = self._get("/api/basic/lastPrice", params)
        return respone

    def price(self, *symbols):
        params = { "symbols" : ','.join(symbols) } if len(symbols) > 0 else {}
        respone = self._get("/api/index/price", params)
        return respone

    def depth(self, symbol):
        params = { "symbol" : symbol }
        respone = self._get("/api/depth/depth", params)
        return respone

    def trades(self, symbol):
        params = { "symbol" : symbol }
        respone = self._get("/api/depth/trades", params)
        return respone

    def quotes(self, symbol, sequence=''):
        params = {
            "symbol" : symbol,
            "sequence" : sequence,
        }
        respone = self._get("/api/depth/quotes", params)
        return respone

    def kLine_byIndex(self, symbol, sType, step, iFrom=0):
        params = {
            "from" : iFrom,
            "step" : step,
            "symbol" : symbol,
            "type" : sType,
        }
        respone = self._get("/api/kLine/byIndex", params)
        return respone

    def kLine_byTime(self, symbol, sType, step, iFrom=''):
        params = {
            "from" : iFrom,
            "step" : step,
            "symbol" : symbol,
            "type" : sType,
        }
        respone = self._get("/api/kLine/byTime", params)
        return respone

    def kLine_latest(self, symbol, sType):
        params = {
            "symbol" : symbol,
            "type" : sType,
        }
        respone = self._get("/api/kLine/latest", params)
        return respone

    def kLine_latestPeriod(self, symbol, sType, lastKTime=''):
        params = {
            "symbol" : symbol,
            "type" : sType,
            "lastKTime" : lastKTime,
        }
        respone = self._get("/api/kLine/latestPeriod", params)
        return respone

    def kLine_fundingRate(self, *symbols):
        params = { "symbols" : ','.join(symbols) } if len(symbols) > 0 else {}
        respone = self._get("/api/kLine/fundingRate", params)
        return respone

    def kLine_tradeStatistics(self, *symbols):
        params = { "symbols" : ','.join(symbols) } if len(symbols) > 0 else {}
        respone = self._get("/api/kLine/tradeStatistics", params)
        return respone

    def kLine_openInterest(self, symbol):
        params = { "symbol" : symbol }
        respone = self._get("/api/kLine/openInterest", params)
        return respone
