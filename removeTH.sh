#!/bin/bash

# Ensure a file name is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <filename>"
    exit 1
fi

source="$1"
echo "Parsing: $source"

tempfile=$(mktemp)

# **`IFS= `** to preserve leading and trailing whitespace in the input line, which is critical for accurate processing of file lines.
# **`-r`**: This option to `read` prevents backslash escapes from being interpreted.
while IFS= read -r line; do
    echo "$line" | sed 's/[ ]*th:[a-zA-Z\-]*="[^"]*"//g' >> "$tempfile"
done < "$source"

output="removed-${1}"
mv "$tempfile" "$output"

echo "Output file: $output"
