/*
 * Copyright 2018-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atomix.protocols.raft.partition;

import io.atomix.primitive.protocol.PrimitiveProtocol;
import io.atomix.primitive.partition.PartitionGroupFactory;
import io.atomix.protocols.raft.RaftProtocol;

/**
 * Raft partition group factory.
 */
public class RaftPartitionGroupFactory implements PartitionGroupFactory<RaftPartitionGroupConfig, RaftPartitionGroup> {
  @Override
  public PrimitiveProtocol.Type type() {
    return RaftProtocol.TYPE;
  }

  @Override
  public RaftPartitionGroup create(RaftPartitionGroupConfig config) {
    return new RaftPartitionGroup(config);
  }
}
