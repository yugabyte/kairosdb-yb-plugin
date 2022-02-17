package com.yugabyte.kairosdb.datastore.yugabytedb;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoadBalancingPolicy;

/**
 Created by bhawkins on 2/9/16.
 */
public interface CassandraClient
{
	Session getKeyspaceSession();

	Session getSession();

	String getKeyspace();

	String getReplication();

	LoadBalancingPolicy getWriteLoadBalancingPolicy();

	ClusterConfiguration getClusterConfiguration();

	void close();

	void init();
}
