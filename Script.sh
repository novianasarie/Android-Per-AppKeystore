#!/bin/sh

#  Script.sh
#  SafeBrowser
#
#  Created by Abhinav on 7/30/13.
#  Copyright (c) 2013 Zscaler. All rights reserved.
if [ -z $1 ]; then
echo "Usage: importcert.sh <CA cert PEM file>"
exit 1
fi

CACERT=$1
BCJAR=bounty.jar

TRUSTSTORE=res/raw/zstruststore.bks
ALIAS=`openssl x509 -inform PEM -subject_hash -noout -in $CACERT`

echo "Adding certificate to $TRUSTSTORE..."
keytool -import -v -trustcacerts -alias $ALIAS \
-file $CACERT \
-keystore $TRUSTSTORE -storetype BKS \
-providerclass org.bouncycastle.jce.provider.BouncyCastleProvider \
-providerpath $BCJAR \
-storepass zstrust

echo ""
echo "Added '$CACERT' with alias '$ALIAS' to $TRUSTSTORE..."