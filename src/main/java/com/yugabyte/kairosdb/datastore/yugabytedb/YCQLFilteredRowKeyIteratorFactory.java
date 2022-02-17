package com.yugabyte.kairosdb.datastore.yugabytedb;

import com.google.common.collect.SetMultimap;
import com.google.inject.assistedinject.Assisted;
import org.kairosdb.core.exception.DatastoreException;

import java.util.Iterator;

public interface YCQLFilteredRowKeyIteratorFactory {
    YCQLFilteredRowKeyIterator create(
            ClusterConnection var1,
            String var2,
            @Assisted("startTime") long var3,
            @Assisted("endTime") long var5,
            SetMultimap<String, String> var7) throws DatastoreException;
}
