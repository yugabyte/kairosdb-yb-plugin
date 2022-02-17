# kairosdb-yb-plugin
YugabyteDB plugin for Kairosdb

## Introduction

This is the Yugabyte datastore plugin implementation for KairosDB. This is completely inspired from the inbuilt Cassandra datastore implementation which comes packaged with KairosDB. The Cassandra plugin used to work as with YugabyteDB. But we decided to have a plugin for Yugabyte because of the following reasons:

1. **Cassandra stores ttl values as int32 and YugabyteDB as int64** - This was done to support larger ttl values. But Kairos expects ttl values as int32 and deserializes assuming that which results in error while reading the ttl values obtained from Yugabyte
2. **Yugabyte wants to remove USING TIMESTAMP clause from datapoints insert statements** - YugaByte is strongly consistent, and update order is controlled by the RAFT replication sequence.
   Therefore, when USING TIMESTAMP is used, YugaByte DB would incur a read penalty to make sure that
   an insert with a lower timestamp does not actually take place which is unnecessary.
   
   Under the covers, YugaByte DB implements user specified timestamps (i.e. the USING TIMESTAMP clause)
as an additional implicit column, which needs to be read on each insert.
   
## Building the Yugabyte from source

Clone this package and run _mvn package_ from the root folder.

## Installing the Yugabyte datastore plugin in Kairosdb

### Steps
1. Place the Yugabyte plugin jar in KAIROSDB_INSTALL/lib folder
2. For better performance also place the Yugabyte cassandra driver in the lib folder.
   Place _cassandra-driver-core-3.10.3-yb-2.jar_ in the lib folder and remove the _cassandra-driver-core-3.10.2.jar_ from there.
    
3. Comment H2 datastore and cassandra datastore in KAIROSDB_INSTALL/conf/kairosdb.conf file
4. Add Yugabyte datastore in the KAIROSDB_INSTALL/conf/kairosdb.conf file

The conf entry is _service.datastore_. It should be modified like this:
_service.datastore: "com.yugabyte.kairosdb.datastore.yugabytedb.YugabyteDBModule"_



