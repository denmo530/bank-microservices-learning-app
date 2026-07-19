#!/bin/bash
for svc in accounts cards loans config-server eureka-server gateway-server; do
  (cd $svc && mvn clean package -DskipTests && docker build -t denmo530/$svc:latest . && docker push denmo530/$svc:latest)
done
