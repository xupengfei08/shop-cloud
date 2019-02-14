FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD shop-eureka.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9000

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","shop-eureka.jar"]