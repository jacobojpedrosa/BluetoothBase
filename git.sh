#! /bin/bash
repo=`pwd`
message=$1
cd $repo
echo
echo "Updating repo... "$repo
git pull
echo
echo "Adding changes..."
git add *
echo
echo "Committing changes..."
git commit -vm "$message"
echo
echo "Pushing to remote repo..."
git push
echo "DONE!!"
