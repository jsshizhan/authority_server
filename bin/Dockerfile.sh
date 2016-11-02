FROM java:8

ENV JAR_NAME authority-application

COPY $JAR_NAME.jar $JAR_NAME.jar

EXPOSE 8080

CMD java -jar $JAR_NAME.jar