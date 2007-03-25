#!/bin/sh
FILE=checkdigits-0.9.1
rm -rf $FILE
mkdir $FILE
cp -r LICENSE.txt README.txt java javascript $FILE
find $FILE -name '.svn' -print | xargs rm -rf
find $FILE -name '*~' -print  | xargs rm -f
find $FILE -name '\#*' -print | xargs rm -f

tar -czvf ${FILE}.tar.gz $FILE
rm -rf ${FILE}
