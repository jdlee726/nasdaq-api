curl 'https://api.nasdaq.com/api/market-info?lang=de' \
--compressed \
-H 'User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0' \
-H 'Accept: application/json, text/plain, */*' \
-H 'Accept-Language: en-US,en;q=0.5' \
-H 'Accept-Encoding: gzip, deflate, br, zstd' \
-H 'Referer: https://www.nasdaq.com/' \
-H 'Origin: https://www.nasdaq.com' \
-H 'Connection: keep-alive' \
-H 'Sec-Fetch-Dest: empty' \
-H 'Sec-Fetch-Mode: cors' \
-H 'Sec-Fetch-Site: same-site' \
-H 'TE: trailers'

curl -vvvv 'https://api.nasdaq.com/api/market-info' \
--compressed \
-H 'User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0' \
-H 'Accept-Language: en-US,en;q=0.5'

curl 'https://api.nasdaq.com/api/market-info' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7' \
  -H 'origin: https://www.nasdaq.com' \
  -H 'priority: u=1, i' \
  -H 'referer: https://www.nasdaq.com/' \
  -H 'sec-ch-ua: "Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"' \
  -H 'sec-fetch-dest: empty' \
  -H 'sec-fetch-mode: cors' \
  -H 'sec-fetch-site: same-site' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
curl -sS -vvvv 'https://api.nasdaq.com/api/market-info' \
  --compressed \
  -H 'Accept-Language: en-US,en;q=0.5' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36' \
  | jq > market-info.json

curl 'https://api.nasdaq.com/api/quote/TSLA/info?assetclass=stocks' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7' \
  -H 'origin: https://www.nasdaq.com' \
  -H 'priority: u=1, i' \
  -H 'referer: https://www.nasdaq.com/' \
  -H 'sec-ch-ua: "Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"' \
  -H 'sec-fetch-dest: empty' \
  -H 'sec-fetch-mode: cors' \
  -H 'sec-fetch-site: same-site' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
curl -sS 'https://api.nasdaq.com/api/quote/TSLA/info?assetclass=stocks' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36' | jq > quote.json

curl 'https://api.nasdaq.com/api/quote/TSLA/option-chain?assetclass=stocks&limit=60' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7' \
  -H 'origin: https://www.nasdaq.com' \
  -H 'priority: u=1, i' \
  -H 'referer: https://www.nasdaq.com/' \
  -H 'sec-ch-ua: "Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"' \
  -H 'sec-fetch-dest: empty' \
  -H 'sec-fetch-mode: cors' \
  -H 'sec-fetch-site: same-site' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
curl -sS 'https://api.nasdaq.com/api/quote/TSLA/option-chain?assetclass=stocks&limit=100' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36' | jq > option-chain.json

curl 'https://api.nasdaq.com/api/quote/TSLA/option-chain?assetclass=stocks&limit=60&offset=60&fromdate=all&todate=undefined&excode=oprac&callput=callput&money=at&type=all' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7' \
  -H 'origin: https://www.nasdaq.com' \
  -H 'priority: u=1, i' \
  -H 'referer: https://www.nasdaq.com/' \
  -H 'sec-ch-ua: "Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"' \
  -H 'sec-fetch-dest: empty' \
  -H 'sec-fetch-mode: cors' \
  -H 'sec-fetch-site: same-site' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
curl -sS 'https://api.nasdaq.com/api/quote/TSLA/option-chain?assetclass=stocks&limit=500&offset=0&fromdate=all&todate=undefined&excode=oprac&callput=callput&money=at&type=all' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36' | jq > option-chain-all.json
