FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD order-core.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9030

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","order-core.jar"]