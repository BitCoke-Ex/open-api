
import json
import sys
import requests
import hashlib
import time
import urllib
import const


class ClientBase(object):
    def __init__(self, api_key, api_secret):
        super(ClientBase, self).__init__()
        self._api_key = api_key
        self._api_secret = api_secret

    def _get_params(self, data):
        if len(data) > 0:
            return '?' + urllib.parse.urlencode(data)
        else:
            return ''

    def _get_common_headers(self, requestMethod, path):
        expires = int(time.time()) + 1000
        signature = self._get_signature(requestMethod, path, expires)
        headers = {
            'Content-Type': 'application/json;charset=UTF-8',
            'apiKey': self._api_key,
            'expires': str(expires),
            'signature': signature
        }
        # print(headers)
        return headers

    def _get_signature(self, requestMethod, path, expires):
        if self._api_key == None or self._api_secret == None:
            return ''
        origin_text = self._api_secret + requestMethod + path + str(expires)
        signature = hashlib.sha256(origin_text.encode('utf-8')).hexdigest()
        return signature

    def _get(self, path, params):
        url = const.API_URL + path + self._get_params(params)
        # print(url)
        response = requests.get(url, headers=self._get_common_headers('GET', path))
        return response

    def _post(self, path, params):
        url = const.API_URL + path + self._get_params(params)
        # print(url)
        response = requests.post(url, headers=self._get_common_headers('POST', path))
        return response

    def _get_trade(self, path, params):
        url = const.TRADE_API_URL + path + self._get_params(params)
        # print(url)
        response = requests.get(url, headers=self._get_common_headers('GET', path))
        return response

    def _post_trade(self, path, params):
        url = const.TRADE_API_URL + path + self._get_params(params)
        # print(url)
        response = requests.post(url, headers=self._get_common_headers('POST', path))
        return response

