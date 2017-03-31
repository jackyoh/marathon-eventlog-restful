#!/bin/bash
for((i=1;i<=35;i++))
do
   bash create-app.sh
   sleep 25
   bash delete-app.sh
   sleep 2
done
