#!/bin/sh

# wait-for-it.sh

host="$1"
shift
cmd="$@"

until nc -z "$host" 3306; do
  >&2 echo "MySQL indisponivel - sleeping"
  sleep 1
done

>&2 echo "MySQL esta funcionando - executing command"
exec $cmd