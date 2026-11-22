#!/bin/sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
URL="$(sed -n 's/^distributionUrl=//p' "$ROOT/.mvn/wrapper/maven-wrapper.properties" | head -n 1)"
FILE="${URL##*/}"
NAME="${FILE%-bin.zip}"
CACHE="$HOME/.m2/wrapper/$NAME"
if [ ! -x "$CACHE/$NAME/bin/mvn" ]; then
    mkdir -p "$CACHE"
    if command -v curl >/dev/null 2>&1; then
        curl -fL "$URL" -o "$CACHE/$FILE"
    else
        wget -O "$CACHE/$FILE" "$URL"
    fi
    unzip -q -o "$CACHE/$FILE" -d "$CACHE"
    rm -f "$CACHE/$FILE"
fi
exec "$CACHE/$NAME/bin/mvn" "$@"
