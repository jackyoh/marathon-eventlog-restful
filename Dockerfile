FROM centos:7.2.1511
MAINTAINER Docker Jack <jack@is-land.com.tw>

RUN yum install -y curl git lsof telnet wget zip unzip

#INSALL JAVA
RUN curl -LO 'http://download.oracle.com/otn-pub/java/jdk/7u71-b14/jdk-7u71-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie' && \
    tar zxvf jdk-7u71-linux-x64.tar.gz && \
    rm -f jdk-7u71-linux-x64.tar.gz
ENV JAVA_HOME /jdk1.7.0_71
ENV PATH $JAVA_HOME/bin:$PATH


#INSTALL GRADLE
RUN wget https://services.gradle.org/distributions/gradle-3.4-bin.zip && \
    unzip gradle-3.4-bin.zip && \
    rm -f gradle-3.4-bin.zip
    
ENV GRADLE_HOME /gradle-3.4
ENV PATH $GRADLE_HOME/bin:$PATH


#INSTALL TOMCAT
RUN wget https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.73/bin/apache-tomcat-7.0.73.tar.gz && \
    tar zxvf apache-tomcat-7.0.73.tar.gz && \
    rm -f apache-tomcat-7.0.73.tar.gz

#CLONE GITHUB CODE
RUN git clone https://github.com/jackyoh/marathon-eventlog-restful.git && \
    cd marathon-eventlog-restful && gradle clean build -x test

#COPY WAR FILE TO TOMCAT CONTAINER
RUN cp /marathon-eventlog-restful/build/libs/marathon-eventlog-restful.war /apache-tomcat-7.0.73/webapps

#RUN Tomcat Service
ADD ./start.sh /start.sh
CMD ["/bin/bash", "/start.sh"]
