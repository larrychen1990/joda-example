#! /bin/sh

PROJECT_ROOT=/home/steve/workspace/joda-example

CP=${CLASSPATH}:${PROJECT_ROOT}/target/classes:${PROJECT_ROOT}/lib/joda-time-1.6.jar:${PROJECT_ROOT}/lib/log4j-1.2.15.jar

JAVA_HOME=/usr
ANT_HOME=/usr

${ANT_HOME}/bin/ant -f ${PROJECT_ROOT}/build.xml all

${JAVA_HOME}/bin/java -classpath ${CP} com.makotogroup.joda.KeyConcepts
${JAVA_HOME}/bin/java -classpath ${CP} com.makotogroup.joda.CreatingJodaTimeObjects
${JAVA_HOME}/bin/java -classpath ${CP} com.makotogroup.joda.ManipulatingTime
${JAVA_HOME}/bin/java -classpath ${CP} com.makotogroup.joda.FormattingTime
