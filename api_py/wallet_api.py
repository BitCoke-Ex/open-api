
from clientbase import ClientBase


class WalletApi(ClientBase):
    def __init__(self, api_key, api_secret):
        super(WalletApi, self).__init__(api_key, api_secret)

    def list(self):
        params = {}
        respone = self._get_trade("/api/wallet/list", params)
        return respone

    def depositAddress(self):
        params = {}
        respone = self._get_trade("/api/wallet/depositAddress", params)
        return respone

    def ledger(self):
        params = {}
        respone = self._get_trade("/api/wallet/ledger", params)
        return respone

