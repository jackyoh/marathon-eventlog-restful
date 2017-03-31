#!/bin/bash

generate_post_data()
{
  cat << EOF
{
  "id": "$1",
  "cmd": "while true; do\n echo 'Hello World'\n sleep 1\n done",
  "instances": 1
}
EOF
}

for((i=1;i<=15;i++))
do
   curl -H "Content-Type: application/json" -X POST -d "$(generate_post_data hello${i})" http://192.168.1.211:8080/v2/apps
done
