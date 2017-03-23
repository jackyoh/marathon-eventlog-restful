#!/bin/bash
curl -X POST -H "Content-Type: application/json" http://192.168.1.223:8080/v2/apps -d @helloworld.json
