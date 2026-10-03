#!/bin/sh
# Helper script to manage local docker containers.
#
# Usage:
#   ./docker-containers.sh list            List all containers (running + stopped)
#   ./docker-containers.sh stopped          List stopped containers
#   ./docker-containers.sh prune            Remove all stopped containers
#   ./docker-containers.sh prune-project    Stop and remove this project's containers (compose down)
#   ./docker-containers.sh stop <id>        Stop a specific container by id/name
#   ./docker-containers.sh rm <id>          Force remove a specific container by id/name
#
# Not using the real "docker" binary (e.g. Lima with a shell alias)?
# Export DOCKER_CMD to point at the real command, since shell aliases
# are not expanded inside non-interactive scripts. For example:
#   export DOCKER_CMD="limactl shell default nerdctl"
#   ./docker-containers.sh list

set -e

DOCKER_CMD="${DOCKER_CMD:-docker}"
COMPOSE_FILE="$(dirname "$0")/docker-compose.yml"
COMPOSE="$DOCKER_CMD compose -f $COMPOSE_FILE"

case "$1" in
  list)
    $DOCKER_CMD ps -a
    ;;
  stopped)
    $DOCKER_CMD ps -a -f "status=exited"
    ;;
  prune)
    $DOCKER_CMD container prune -f
    ;;
  prune-project)
    $COMPOSE down -v
    ;;
  stop)
    if [ -z "$2" ]; then
      echo "Usage: $0 stop <container_id_or_name>"
      exit 1
    fi
    $DOCKER_CMD stop "$2"
    ;;
  rm)
    if [ -z "$2" ]; then
      echo "Usage: $0 rm <container_id_or_name>"
      exit 1
    fi
    $DOCKER_CMD rm -f "$2"
    ;;
  *)
    echo "Usage: $0 {list|stopped|prune|prune-project|stop <id>|rm <id>}"
    exit 1
    ;;
esac
