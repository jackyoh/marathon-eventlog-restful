#!/bin/bash
for((i=1;i<=15;i++))
do
   curl -X DELETE http://192.168.1.211:8080/v2/apps/helloworld${i}
done

