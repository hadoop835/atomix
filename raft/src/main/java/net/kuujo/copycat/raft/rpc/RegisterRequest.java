/*
 * Copyright 2015 the original author or authors.
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
package net.kuujo.copycat.raft.rpc;

import net.kuujo.copycat.io.Buffer;
import net.kuujo.copycat.io.serializer.Serializer;
import net.kuujo.copycat.io.util.ReferenceManager;

import java.util.Objects;

/**
 * Protocol register client request.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class RegisterRequest extends ClientRequest<RegisterRequest> {
  private static final ThreadLocal<Builder> builder = new ThreadLocal<Builder>() {
    @Override
    protected Builder initialValue() {
      return new Builder();
    }
  };

  /**
   * Returns a new register client request builder.
   *
   * @return A new register client request builder.
   */
  public static Builder builder() {
    return builder.get().reset();
  }

  /**
   * Returns a register client request builder for an existing request.
   *
   * @param request The request to build.
   * @return The register client request builder.
   */
  public static Builder builder(RegisterRequest request) {
    return builder.get().reset(request);
  }

  public RegisterRequest(ReferenceManager<RegisterRequest> referenceManager) {
    super(referenceManager);
  }

  @Override
  public Type type() {
    return Type.REGISTER;
  }

  @Override
  public void writeObject(Buffer buffer, Serializer serializer) {

  }

  @Override
  public void readObject(Buffer buffer, Serializer serializer) {

  }

  @Override
  public String toString() {
    return String.format("%s", getClass().getSimpleName());
  }

  /**
   * Register client request builder.
   */
  public static class Builder extends ClientRequest.Builder<Builder, RegisterRequest> {

    private Builder() {
      super(RegisterRequest::new);
    }

    @Override
    public int hashCode() {
      return Objects.hash(request);
    }

    @Override
    public boolean equals(Object object) {
      return object instanceof Builder && ((Builder) object).request.equals(request);
    }

    @Override
    public String toString() {
      return String.format("%s[request=%s]", getClass().getCanonicalName(), request);
    }

  }

}
