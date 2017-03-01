# Install
Download Source code command
```
#git clone https://github.com/jackyoh/marathon-eventlog-restful.git
```
Build docker image command
```
# docker build -t jackyoh/marathon-restful:0.0.1 .
```

Run container command
```
# docker run -ti -e MARATHON_RESTFUL_EVENTLOG_URL=http://192.168.1.8:8080/v2/events -p 8090:8080 --restart=always jackyoh/marathon-restful:0.0.1
```

Query your application status url for example:
```
http://192.168.1.223:8090/marathon-eventlog-restful/webapi/v2/apps
```

# REST API
* [GET /webapi/v2/apps] :List all application on Marathon framework
* [GET /webapi/v2/apps/{appId}] :Query application information on Marathon framework
* [GET /webapi/v2/taskstatus] :List all application status on Marathon framework
* [GET /webapi/v2/taskstatus/{appId}] :Query application status on Marathon framework
