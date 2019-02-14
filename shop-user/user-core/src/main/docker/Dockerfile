FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD user-core.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9020

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","user-core.jar"]