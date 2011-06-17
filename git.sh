#! /bin/bash
#Git-script
message=$1
git pull
git add *
git commit -m "$message"
git push
