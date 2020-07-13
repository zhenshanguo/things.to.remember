FROM docker.artifactory.a.intuit.com/money/profile/account-service/service/account-service:cocoon AS cocoon
FROM docker.intuit.com/oicp/standard/maven/amzn-maven-corretto8:1.0.0 AS build
COPY --from=cocoon /root/.m2 /root/.m2

# USER root needed for build, since CPD does not allow root user for gold images.
# intermediate containers will be discarded at final stage, runtime image will be executed with non root user
USER root

# The following ARG and 2 LABEL are used by Jenkinsfile command
# to identify this intermediate container, for extraction of
# code coverage and other reported values.
ARG build
LABEL build=${build}
LABEL image=build
ARG MVN_SETTINGS=settings.xml
COPY package /usr/src/package
COPY project.properties /usr/src/project.properties
COPY app/pom.xml /usr/src/app/pom.xml
COPY pom.xml /usr/src/pom.xml
COPY ${MVN_SETTINGS} /usr/src/settings.xml

RUN mvn -f /usr/src/pom.xml -s /usr/src/settings.xml dependency:go-offline
COPY app /usr/src/app

RUN mvn -f /usr/src/pom.xml -s /usr/src/settings.xml clean install

FROM docker.intuit.com/oicp/standard/corretto-8.x/amzn2-corretto:8
ARG DOCKER_TAGS=latest
ARG JIRA_PROJECT=https://jira.intuit.com/projects/AS
ARG DOCKER_IMAGE_NAME=docker.artifactory.a.intuit.com/money/profile/account-service/service/account-service:${DOCKER_TAGS}
ARG SERVICE_LINK=https://devportal.intuit.com/app/dp/resource/1947824431026009179

LABEL maintainer=some_email@intuit.com \
      app=account-service \
      app-scope=runtime \
      build=${build}

EXPOSE 8080

# Switch to root for installation and some other operations
USER root

# switch to PST timezone
RUN unlink /etc/localtime && ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime && echo America/Los_Angeles > /etc/timezone

COPY --from=build /usr/src/app/target/account-service.jar /app/account-service-app.jar
RUN chmod 644 /app/account-service-app.jar

# Download latest contrast.jar
RUN curl -o /app/contrast/javaagent/contrast.jar https://artifact.intuit.com/artifactory/generic-local/dev/security/ssdlc/contrast/java/latest/contrast.jar

COPY --from=build /usr/src/package/target/build.params.json /build.params.json
RUN chmod 644 /build.params.json

COPY --from=build /usr/src/package/entry.sh /app/entry.sh
RUN chmod 644 /app/entry.sh

# The /app/tmp directory is needed for CPD
RUN mkdir /app/tmp
RUN chown appuser:appuser /app -R

# Remove unnecessary tools
RUN ["/home/appuser/post_harden.sh"]

USER appuser
CMD ["/bin/sh", "/app/entry.sh"]
