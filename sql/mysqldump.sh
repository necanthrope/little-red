#!/bin/bash
mysqldump -u bbc_mysql --databases bbc_wordpress -h seannittner-mysql.c4nuiervvijb.us-west-1.rds.amazonaws.com --port 3306 -p \
| grep -v "50013 DEFINER" > bbc_dump.sql