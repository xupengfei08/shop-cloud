FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD shop-zuul.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9010

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","shop-zuul.jar"]