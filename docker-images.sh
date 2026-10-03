#!/bin/sh
# Helper script to manage local docker images.
#
# Usage:
#   ./docker-images.sh list            List all images
#   ./docker-images.sh dangling        List dangling (untagged) images
#   ./docker-images.sh prune           Remove dangling images
#   ./docker-images.sh prune-all       Remove all unused images (not just dangling)
#   ./docker-images.sh prune-project   Remove images built by this project's docker-compose.yml
#   ./docker-images.sh rm <image_id>   Force remove a specific image by id/name
#
# Not using the real "docker" binary (e.g. Lima with a shell alias)?
# Export DOCKER_CMD to point at the real command, since shell aliases
# are not expanded inside non-interactive scripts. For example:
#   export DOCKER_CMD="limactl shell default nerdctl"
#   ./docker-images.sh list

set -e

DOCKER_CMD="${DOCKER_CMD:-docker}"
COMPOSE_FILE="$(dirname "$0")/docker-compose.yml"
COMPOSE="$DOCKER_CMD compose -f $COMPOSE_FILE"

case "$1" in
  list)
    $DOCKER_CMD images
    ;;
  dangling)
    $DOCKER_CMD images -f "dangling=true"
    ;;
  prune)
    $DOCKER_CMD image prune -f
    ;;
  prune-all)
    $DOCKER_CMD image prune -a -f
    ;;
  prune-project)
    $COMPOSE down --rmi all
    ;;
  rm)
    if [ -z "$2" ]; then
      echo "Usage: $0 rm <image_id_or_name>"
      exit 1
    fi
    $DOCKER_CMD rmi -f "$2"
    ;;
  *)
    echo "Usage: $0 {list|dangling|prune|prune-all|prune-project|rm <image_id>}"
    exit 1
    ;;
esac
