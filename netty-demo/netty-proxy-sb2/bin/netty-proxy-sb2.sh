#!/usr/bin/env bash
#
# Startup script for spring boot uber jar using jetty embeded server under centos, macos.

NAME=$(echo $(basename $0) | sed -e 's/^[SK][0-9]*//' -e 's/\.sh$//')

ITEM_NAME=${NAME
JAVA=`which java`
JAVA_OPTIONS="$JAVA_OPTIONS -Dappname=$ITEM_NAME"
TMPDIR=/duitang/tmp/${ITEM_NAME}/

LOGS="/tmp/$ITEM_NAME"
APP_PID="${LOGS}/${ITEM_NAME}.pid"

APP_HOME="/tmp/${ITEM_NAME}"

mkdir -p ${LOGS} ${TMPDIR}

usage()
{
    echo "Usage: ${0##*/} [-d] {start|stop|run|restart|check} "
    exit 1
}

[ $# -gt 0 ] || usage

##################################################
# Some utility functions
##################################################
running()
{
  if [ -f "$1" ]
  then
    local PID=$(cat "$1" 2>/dev/null) || return 1
    kill -0 "$PID" 2>/dev/null
    return
  fi
  rm -f "$1"
  return 1
}

DEBUG=0
while [[ $1 = -* ]]; do
  case $1 in
    -d) DEBUG=1 ;;
  esac
  shift
done
ACTION=$1
shift


##################################################
# Must define JAVA arg
##################################################
if [ -z "$JAVA" ]
then
  JAVA=$(which java)
fi 

if [ -z "$JAVA" ]
then
  echo "Cannot find a Java JDK cfg. Please set JAVA." 2>&2
  exit 1
fi

#####################################################
# Must define LOGS
#####################################################
if [ -z "$LOGS" ]
then
  echo "Cannot find a LOGS cfg. Please set LOGS." 2>&2
  exit 1
fi

JAVA_OPTIONS=("$JAVA_OPTIONS" "-Djetty.logs=$LOGS")
#####################################################
# Add jetty properties to Java VM options.
#####################################################

JAVA_OPTIONS=("$JAVA_OPTIONS" "-Djava.io.tmpdir=$TMPDIR")

# This is how the Jetty server will be started
JAVA_OPTIONS="$JAVA_OPTIONS -server -Xms4g -Xmx4g -XX:PermSize=96m -XX:MaxPermSize=384m -Xmn2g"
JAVA_OPTIONS="$JAVA_OPTIONS -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection"
JAVA_OPTIONS="$JAVA_OPTIONS -XX:CMSMaxAbortablePrecleanTime=5000 -XX:+CMSClassUnloadingEnabled"
JAVA_OPTIONS="$JAVA_OPTIONS -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80"
JAVA_OPTIONS="$JAVA_OPTIONS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOGS/java.hprof"
# JAVA_OPTIONS="$JAVA_OPTIONS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=11902"

#####################################################

STARTER=${APP_HOME}/${NAME}.jar

RUN_ARGS=(${JAVA_OPTIONS[@]} -jar "$STARTER" ${APP_ARGS[*]})

RUN_CMD=("$JAVA" ${RUN_ARGS[@]})

#####################################################
# Comment these out after you're happy with what
# the script is doing.
#####################################################
if (( DEBUG ))
then
  echo "APP_HOME     =  $APP_HOME"
  echo "APP_PID      =  $APP_PID"
  echo "STARTER    =  $STARTER"
  echo "APP_ARGS     =  ${APP_ARGS[*]}"
  echo "JAVA_OPTIONS   =  ${JAVA_OPTIONS[*]}"
  echo "JAVA           =  $JAVA"
  echo "RUN_CMD        =  ${RUN_CMD[@]}"
fi

echo ""

##################################################
# Do the action
##################################################
case "$ACTION" in
  start)
    echo -n "Starting ${ITEM_NAME}: "

    if running ${APP_PID}
    then
      echo "Already Running $(cat ${APP_PID})!"
      exit 1
    fi

    "${RUN_CMD[@]}" > "$LOGS/stdout.log" 2>&1 &
    disown $!
    echo $! > "$APP_PID"
    echo "deploy `date`" >> "$LOGS/start.log"
    echo OK

    ;;

  stop)
    echo -n "Stopping ${ITEM_NAME}: "

    if [ ! -f "$APP_PID" ] ; then
      echo "ERROR: no pid found at $APP_PID"
      exit 1
    fi

    PID=$(cat "$APP_PID" 2>/dev/null)
    if [ -z "$PID" ] ; then
      echo "ERROR: no pid id found in $APP_PID"
      exit 1
    fi
    kill "$PID" 2>/dev/null

    TIMEOUT=10
    while running $APP_PID; do
      if (( TIMEOUT-- == 0 )); then
        kill -KILL "$PID" 2>/dev/null
      fi

      sleep 1
    done

    rm -f "$APP_PID"
    echo OK

    ;;

  restart)
    JETTY_SH=$0
    "$JETTY_SH" stop "$@"
    "$JETTY_SH" start "$@"
    ;;


  run)
    echo "Running $NAME: "

    if running "$APP_PID"
    then
      echo Already Running $(cat "$APP_PID")!
      exit 1
    fi

    exec "${RUN_CMD[@]}"

    ;;

  status)
    echo "Checking arguments to Jetty: "
    echo "APP_HOME     =  $APP_HOME"
    echo "APP_PID      =  $APP_PID"
    echo "STARTER    =  $STARTER"
    echo "LOGS     =  $LOGS"
    echo "JAVA           =  $JAVA"
    echo "JAVA_OPTIONS   =  ${JAVA_OPTIONS[*]}"
    echo "APP_ARGS     =  ${APP_ARGS[*]}"
    echo "RUN_CMD        =  ${RUN_CMD[*]}"
    echo

    if running "$APP_PID"
    then
      echo "Jetty running pid=$(< "$APP_PID")"
      exit 0
    fi
    exit 1

    ;;

  *)
    usage

    ;;
esac

exit 0