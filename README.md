Build docker image command
```
# docker build -t jackyoh/marathon-restful:0.0.1 .
```
Run container command
```
# docker run -ti -e MARATHON_RESTFUL_EVENTLOG_URL=http://192.168.1.8:8080/v2/events -p 8090:8080 --restart=always jackyoh/marathon-restful:0.0.1
```
