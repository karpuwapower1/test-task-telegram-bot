#!/bin/bash

sed -i 's#http://localhost:8080#'"PROXY_URL"'#' /home/app/package.json
exec "$@"
