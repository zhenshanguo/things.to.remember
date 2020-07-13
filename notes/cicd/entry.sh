#!/bin/sh

if [ -f /etc/secrets/application.properties ]; then
  JAVA_OPTS="${JAVA_OPTS} -Dspring.config.location=/etc/secrets/application.properties"
fi

if [ -n "${APP_ENV}" ]; then
  JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${APP_ENV}"
fi

if [ "${APP_ENV}" = "prf" ] || [ "${APP_ENV}" = "prf-use2" ] || [ "${APP_ENV}" = "prd" ] || [ "${APP_ENV}" = "prd-use2" ]; then
JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockExperimentalVMOptions \
    -XX:+UseCGroupMemoryLimitForHeap \
    -XX:MaxRAMFraction=2 \
    -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError \
    -XX:+ParallelRefProcEnabled -XX:+PrintClassHistogram \
    -XX:+UseG1GC \
    -XX:+UseStringDeduplication -XX:-UseSplitVerifier \
    -XX:CompressedClassSpaceSize=2g -XX:MaxGCPauseMillis=200 \
    -XX:MaxMetaspaceSize=1024m -XX:MetaspaceSize=256m -Xms6144m \
    -Xmx6144m -XX:CompressedClassSpaceSize=512m -XX:MaxPermSize=256m \
    -XX:PermSize=256m -XX:SurvivorRatio=8
  -XshowSettings:vm -Dlog_level=INFO -Djavax.net.ssl.sessionCacheSize=20480"
elif [ "${APP_ENV}" = "stg" ] || [ "${APP_ENV}" = "sbx" ] || [ "${APP_ENV}" = "stg-use2" ] || [ "${APP_ENV}" = "sbx-use2"  ]; then
    JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockExperimentalVMOptions \
      -XX:+UseG1GC -XX:+UseStringDeduplication \
      -XX:+UseCGroupMemoryLimitForHeap \
      -XX:MaxRAMFraction=2 \
      -XshowSettings:vm -Dlog_level=INFO"
else
JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockExperimentalVMOptions \
      -XX:+UseG1GC -XX:+UseStringDeduplication \
      -XX:+UseCGroupMemoryLimitForHeap \
      -XX:MaxRAMFraction=2 \
      -XshowSettings:vm"
fi


JAVA_OPTS="${JAVA_OPTS} -Duser.timezone=PST8PDT \
   -DTZ=PST8PDT -DisThreadContextMapInheritable=true \
   -Dfile.encoding=UTF-8 -Djava.io.tmpdir=/app/tmp -Dlogging.config=classpath:log4j2-iks-spring.xml"
   
# Is contrast enabled, yes or no
contrastassess_enabled=no
# ENV for contrast assessment
contrastassess_env=qal
contrastassess_jar="/app/contrast/javaagent/contrast.jar"
if [ "${contrastassess_enabled}" = "yes" ] && [ "${APP_ENV}" = "${contrastassess_env}" ]; then
  JAVA_OPTS="${JAVA_OPTS} -javaagent:${contrastassess_jar}"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.dir=/app/contrast/javaagent"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.application.code=1947824431026009179"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.application.name=Intuit.sbg.payments.account"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.standalone.appname=Intuit.sbg.payments.account"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.inspect.allclasses=false -Dcontrast.process.codesources=false"
  JAVA_OPTS="${JAVA_OPTS} -Dcontrast.inventory.libraries=false"
fi

appdynamics_jar="/app/appdynamics/javaagent.jar"
if [[ -r ${appdynamics_jar} && -f /etc/secrets/appd-account-access-key ]]; then

    export APPDYNAMICS_CONTROLLER_PORT=443
    export APPDYNAMICS_CONTROLLER_SSL_ENABLED=true

    export APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY=`cat /etc/secrets/appd-account-access-key`

    JAVA_OPTS="$JAVA_OPTS -javaagent:${appdynamics_jar}"
    JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.applicationName=${L1}-${L2}-${APP_NAME}-${APP_ENV}"
    JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.tierName=${APPDYNAMICS_AGENT_TIER_NAME}"
    JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.nodeName=${APPDYNAMICS_AGENT_TIER_NAME}_${HOSTNAME}"
    JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.maxMetrics=${APPDYNAMICS_AGENT_MAX_METRICS}"
fi

exec java $JAVA_OPTS -jar /app/account-service-app.jar
