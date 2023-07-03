# we will use openjdk 8 with alpine as it is a very small linux distro
FROM openjdk:11
# copy the packaged jar file into our docker image
COPY target/vislegis-1.0.0.jar /visLegis.jar
# set the startup command to execute the jar
CMD ["java", "-jar", "/visLegis.jar"]
