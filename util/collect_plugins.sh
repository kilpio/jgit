#!/usr/bin/env bash


FILE=${1}
echo $(cat ${FILE} | sed -E 's/^[[:space:]]/#/g'| tr -d '\15\32' | tr  '\n' '$' | sed -e 's/\$\#//g'  -e 's/\$/\n/g' | grep "Short-Name: " | sed -e 's/Short-Name\: //' | cut -d  ';' -f 1) >> installed
cat ${FILE} | sed -E 's/^[[:space:]]/#/g'| tr -d '\15\32' | tr  '\n' '$' | sed -e 's/\$\#//g'  -e 's/\$/\n/g' | grep "Plugin-Dependencies: " | sed -e 's/Plugin\-Dependencies: //' \
| cut -d  ';' -f 1 | tr ':' '\n' | cut -d ',' -f 2 | head -n -1 >> deps

