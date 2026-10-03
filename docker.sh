#!/bin/sh
# Helper script to manage the local docker-compose stack.
#
# Usage:
#   ./docker.sh up        Start containers (detached)
#   ./docker.sh down      Stop and remove containers
#   ./docker.sh restart   Down then up
#   ./docker.sh logs      Follow logs
#   ./docker.sh ps        Show container status
#
# Not using the real "docker" binary (e.g. Lima with a shell alias)?
# Export DOCKER_CMD to point at the real command, since shell aliases
# are not expanded inside non-interactive scripts. For example:
#   export DOCKER_CMD="limactl shell default nerdctl"
#   ./docker.sh up

set -e

DOCKER_CMD="${DOCKER_CMD:-docker}"
COMPOSE_FILE="$(dirname "$0")/docker-compose.yml"
COMPOSE="$DOCKER_CMD compose -f $COMPOSE_FILE"

case "$1" in
  up)
    $COMPOSE up -d
    ;;
  down)
    $COMPOSE down
    ;;
  restart)
    $COMPOSE down
    $COMPOSE up -d
    ;;
  logs)
    $COMPOSE logs -f
    ;;
  ps)
    $COMPOSE ps
    ;;
  *)
    echo "Usage: $0 {up|down|restart|logs|ps}"
    exit 1
    ;;
esac
