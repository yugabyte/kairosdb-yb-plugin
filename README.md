# kairosdb-yb-plugin
YugabyteDB plugin for Kairosdb

## Introduction

This is the YugabyteDB datastore plugin implementation for KairosDB. This is completely inspired from the inbuilt Cassandra datastore implementation which comes packaged with KairosDB. The Cassandra plugin used to work as with YugabyteDB. But we decided to have a plugin for YugabyteDB because of the following reasons:

1. **Cassandra stores ttl values as int32 and YugabyteDB as int64** - This was done to support larger ttl values. But Kairos expects ttl values as int32 and deserializes assuming that which results in error while reading the ttl values obtained from YugabyteDB
2. **YugabyteDB wants to remove USING TIMESTAMP clause from datapoints insert statements** - YugaByte is strongly consistent, and update order is controlled by the RAFT replication sequence.
   Therefore, when USING TIMESTAMP is used, YugaByte DB would incur a read penalty to make sure that
   an insert with a lower timestamp does not actually take place which is unnecessary.
   
   Under the covers, YugaByte DB implements user specified timestamps (i.e. the USING TIMESTAMP clause)
as an additional implicit column, which needs to be read on each insert.

## Get the KairosDB YCQL Plugin jar
You can get the KairosDB YCQL Plugin jar in the following ways: 

### Build from source

Clone this repository and run _mvn package_ from the root folder. The jar will be generated in the `target` folder.

### Download the jar

You can download the latest plugin from the [Github releases page](https://github.com/yugabyte/kairosdb-yb-plugin/releases).
Alternatively, it will be available on [maven](https://mvnrepository.com/artifact/com.yugabyte/kairodb-ycql).

## Installing the YugabyteDB datastore plugin in Kairosdb

### Steps
1. Place the YugabyteDB plugin jar in KAIROSDB_INSTALL/lib folder
2. \[Optional] For better performance, replace _cassandra-driver-core-3.10.2.jar_ with YugabyteDB's [_cassandra-driver-core-3.10.3-yb-2.jar_](https://repo1.maven.org/maven2/com/yugabyte/cassandra-driver-core/3.10.3-yb-2/cassandra-driver-core-3.10.3-yb-2.jar) in the KAIROSDB_INSTALL/lib folder.
    
3. Comment H2 and cassandra datastores and add YugabyteDB datastore in the `KAIROSDB_INSTALL/conf/kairosdb.conf` file

   The conf entry is _service.datastore_. It should be modified like this:
   ```
   #Configure the datastore

   #service.datastore: "org.kairosdb.datastore.h2.H2Module"                                     
   #service.datastore: "org.kairosdb.datastore.cassandra.CassandraModule"                       
   service.datastore: "com.yugabyte.kairosdb.datastore.yugabytedb.YugabyteDBModule"
   ```



