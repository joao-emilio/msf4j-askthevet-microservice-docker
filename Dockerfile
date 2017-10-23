FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER joaoemilio@devthinkers.com
VOLUME /tmp
ADD target/askthevet-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
