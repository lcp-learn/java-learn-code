##FROM lcp-zulu15:1.0
#FROM openjdk:15-oracle
#
##设定时区
#RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#RUN echo 'Asia/Shanghai' >/etc/timezone
#
#ADD *.jar /logic-process/app.jar
#EXPOSE 5005
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","/logic-process/app.jar"]

FROM bellsoft/liberica-openjdk-debian:8
RUN echo 'Asia/Shanghai' >/etc/timezone

ADD *.jar /logic-process/app.jar
EXPOSE 20887
EXPOSE 5005
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005",
"/logic-process/app.jar"]
#ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","/logic-process/app.jar"]
#ENTRYPOINT ["java","-jar" ,"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8888","-Dspring.profiles.active=sit","/app.jar"]
