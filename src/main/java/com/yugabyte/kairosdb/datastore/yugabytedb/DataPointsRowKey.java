/*
 * Copyright 2016 KairosDB Authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.yugabyte.kairosdb.datastore.yugabytedb;

import java.nio.ByteBuffer;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Objects.requireNonNull;
import static org.kairosdb.util.Preconditions.requireNonNullOrEmpty;

public class DataPointsRowKey extends org.kairosdb.datastore.cassandra.DataPointsRowKey
{

	public DataPointsRowKey(String metricName, String clusterName, long timestamp, String dataType)
	{
		super(metricName, clusterName, timestamp, dataType, new TreeMap<String, String>());
	}

	public DataPointsRowKey(String metricName, String clusterName, long timestamp, String datatype,
							SortedMap<String, String> tags)
	{
		super(metricName, clusterName, timestamp, datatype, tags);

	}
}
