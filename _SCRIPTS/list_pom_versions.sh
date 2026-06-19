#!/usr/bin/env bash

# Generated with assistance from OpenAI Codex.
# Purpose: Lists artifactId and version from Maven POM files, including *_pom.xml templates.

# Erklärung:
# #!: Shebang (Interpreter für dieses Script)
# env: sucht bash über PATH und startet sie

find . -path "*/target/*" -prune -o -type f \( -name "pom.xml" -o -name "*_pom.xml" \) -print0 |
while IFS= read -r -d '' pom; do

  result=$(
    awk '
      BEGIN {
        in_parent = 0
        artifactId = ""
        version = ""
        parent_version = ""
      }

      /<parent[ >]/ {
        in_parent = 1
      }

      /<\/parent>/ {
        in_parent = 0
      }

      {
        line = $0

        if (artifactId == "" && !in_parent && line ~ /<artifactId>/) {
          value = line
          sub(/^.*<artifactId>[[:space:]]*/, "", value)
          sub(/[[:space:]]*<\/artifactId>.*$/, "", value)
          artifactId = value
        }

        if (version == "" && !in_parent && line ~ /<version>/) {
          value = line
          sub(/^.*<version>[[:space:]]*/, "", value)
          sub(/[[:space:]]*<\/version>.*$/, "", value)
          version = value
        }

        if (parent_version == "" && in_parent && line ~ /<version>/) {
          value = line
          sub(/^.*<version>[[:space:]]*/, "", value)
          sub(/[[:space:]]*<\/version>.*$/, "", value)
          parent_version = value
        }
      }

      END {
        if (artifactId == "") artifactId = "UNKNOWN"
        if (version == "") version = parent_version
        if (version == "") version = "UNKNOWN"

        print artifactId "\t" version
      }
    ' "$pom"
  )

  artifactId=${result%%$'\t'*}
  version=${result#*$'\t'}

  printf "%s\t%s\t%s\n" "$artifactId" "$version" "$pom"

done | sort -t $'\t' -k1,1 | column -t -s $'\t'


# Datei ausführbar machen:
# chmod +x list_pom_versions.sh

# Im Project root ausführen:
# ./_SCRIPTS/list_pom_versions.sh | sort