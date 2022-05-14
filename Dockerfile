FROM openjdk:11-bullseye
VOLUME /tmp
ADD build/libs/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]