FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD file-core.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9010

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","file-core.jar"]