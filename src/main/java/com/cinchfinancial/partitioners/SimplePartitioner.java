package com.cinchfinancial.partitioners;

/**
 * Created by jbhambhani on 2/29/16.
 */

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {

    public SimplePartitioner(VerifiableProperties props) {
    }

    /**
     * Uses the key to calculate a partition bucket id for routing
     * the data to the appropriate broker partition
     *
     * @return an integer between 0 and numPartitions-1
     */
    @Override
    public int partition(Object key, int numPartitions) {
        return key.hashCode() % numPartitions;
    }

}