/*
 * Copyright 2016-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atomix.core.election.impl;

import io.atomix.core.election.LeaderElection;
import io.atomix.core.election.LeaderElectionBuilder;
import io.atomix.core.election.LeaderElectionConfig;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.protocol.PrimitiveProtocol;

import java.util.concurrent.CompletableFuture;

/**
 * Default implementation of {@code LeaderElectorBuilder}.
 */
public class LeaderElectionProxyBuilder<T> extends LeaderElectionBuilder<T> {
  public LeaderElectionProxyBuilder(String name, LeaderElectionConfig config, PrimitiveManagementService managementService) {
    super(name, config, managementService);
  }

  @Override
  @SuppressWarnings("unchecked")
  public CompletableFuture<LeaderElection<T>> buildAsync() {
    PrimitiveProtocol protocol = protocol();
    return managementService.getPrimitiveRegistry().createPrimitive(name(), primitiveType())
        .thenCompose(info -> managementService.getPartitionService()
            .getPartitionGroup(protocol)
            .getPartition(name())
            .getPrimitiveClient()
            .newProxy(name(), primitiveType(), protocol)
            .connect()
            .thenApply(proxy -> new TranscodingAsyncLeaderElection<T, byte[]>(new LeaderElectionProxy(proxy), serializer()::encode, serializer()::decode).sync()));
  }
}
