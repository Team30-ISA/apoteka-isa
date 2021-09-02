package isa.apoteka.grpc;

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErrandService.proto

/**
 * Protobuf type {@code ErrandMedicationRequest}
 */
public  final class ErrandMedicationRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ErrandMedicationRequest)
    ErrandMedicationRequestOrBuilder {
  // Use ErrandMedicationRequest.newBuilder() to construct.
  private ErrandMedicationRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ErrandMedicationRequest() {
    errandMedications_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ErrandMedicationRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              errandMedications_ = new java.util.ArrayList<ErrandMedication>();
              mutable_bitField0_ |= 0x00000001;
            }
            errandMedications_.add(
                input.readMessage(ErrandMedication.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        errandMedications_ = java.util.Collections.unmodifiableList(errandMedications_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ErrandServiceOuterClass.internal_static_ErrandMedicationRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ErrandServiceOuterClass.internal_static_ErrandMedicationRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ErrandMedicationRequest.class, ErrandMedicationRequest.Builder.class);
  }

  public static final int ERRANDMEDICATIONS_FIELD_NUMBER = 1;
  private java.util.List<ErrandMedication> errandMedications_;
  /**
   * <code>repeated .ErrandMedication errandMedications = 1;</code>
   */
  public java.util.List<ErrandMedication> getErrandMedicationsList() {
    return errandMedications_;
  }
  /**
   * <code>repeated .ErrandMedication errandMedications = 1;</code>
   */
  public java.util.List<? extends ErrandMedicationOrBuilder> 
      getErrandMedicationsOrBuilderList() {
    return errandMedications_;
  }
  /**
   * <code>repeated .ErrandMedication errandMedications = 1;</code>
   */
  public int getErrandMedicationsCount() {
    return errandMedications_.size();
  }
  /**
   * <code>repeated .ErrandMedication errandMedications = 1;</code>
   */
  public ErrandMedication getErrandMedications(int index) {
    return errandMedications_.get(index);
  }
  /**
   * <code>repeated .ErrandMedication errandMedications = 1;</code>
   */
  public ErrandMedicationOrBuilder getErrandMedicationsOrBuilder(
      int index) {
    return errandMedications_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < errandMedications_.size(); i++) {
      output.writeMessage(1, errandMedications_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < errandMedications_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, errandMedications_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof ErrandMedicationRequest)) {
      return super.equals(obj);
    }
    ErrandMedicationRequest other = (ErrandMedicationRequest) obj;

    boolean result = true;
    result = result && getErrandMedicationsList()
        .equals(other.getErrandMedicationsList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getErrandMedicationsCount() > 0) {
      hash = (37 * hash) + ERRANDMEDICATIONS_FIELD_NUMBER;
      hash = (53 * hash) + getErrandMedicationsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ErrandMedicationRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ErrandMedicationRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ErrandMedicationRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ErrandMedicationRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ErrandMedicationRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ErrandMedicationRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ErrandMedicationRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ErrandMedicationRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ErrandMedicationRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ErrandMedicationRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ErrandMedicationRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ErrandMedicationRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(ErrandMedicationRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ErrandMedicationRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ErrandMedicationRequest)
      ErrandMedicationRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ErrandServiceOuterClass.internal_static_ErrandMedicationRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ErrandServiceOuterClass.internal_static_ErrandMedicationRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ErrandMedicationRequest.class, ErrandMedicationRequest.Builder.class);
    }

    // Construct using ErrandMedicationRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getErrandMedicationsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (errandMedicationsBuilder_ == null) {
        errandMedications_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        errandMedicationsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ErrandServiceOuterClass.internal_static_ErrandMedicationRequest_descriptor;
    }

    public ErrandMedicationRequest getDefaultInstanceForType() {
      return ErrandMedicationRequest.getDefaultInstance();
    }

    public ErrandMedicationRequest build() {
      ErrandMedicationRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ErrandMedicationRequest buildPartial() {
      ErrandMedicationRequest result = new ErrandMedicationRequest(this);
      int from_bitField0_ = bitField0_;
      if (errandMedicationsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          errandMedications_ = java.util.Collections.unmodifiableList(errandMedications_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.errandMedications_ = errandMedications_;
      } else {
        result.errandMedications_ = errandMedicationsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ErrandMedicationRequest) {
        return mergeFrom((ErrandMedicationRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ErrandMedicationRequest other) {
      if (other == ErrandMedicationRequest.getDefaultInstance()) return this;
      if (errandMedicationsBuilder_ == null) {
        if (!other.errandMedications_.isEmpty()) {
          if (errandMedications_.isEmpty()) {
            errandMedications_ = other.errandMedications_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureErrandMedicationsIsMutable();
            errandMedications_.addAll(other.errandMedications_);
          }
          onChanged();
        }
      } else {
        if (!other.errandMedications_.isEmpty()) {
          if (errandMedicationsBuilder_.isEmpty()) {
            errandMedicationsBuilder_.dispose();
            errandMedicationsBuilder_ = null;
            errandMedications_ = other.errandMedications_;
            bitField0_ = (bitField0_ & ~0x00000001);
            errandMedicationsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getErrandMedicationsFieldBuilder() : null;
          } else {
            errandMedicationsBuilder_.addAllMessages(other.errandMedications_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      ErrandMedicationRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ErrandMedicationRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<ErrandMedication> errandMedications_ =
      java.util.Collections.emptyList();
    private void ensureErrandMedicationsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        errandMedications_ = new java.util.ArrayList<ErrandMedication>(errandMedications_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        ErrandMedication, ErrandMedication.Builder, ErrandMedicationOrBuilder> errandMedicationsBuilder_;

    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public java.util.List<ErrandMedication> getErrandMedicationsList() {
      if (errandMedicationsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(errandMedications_);
      } else {
        return errandMedicationsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public int getErrandMedicationsCount() {
      if (errandMedicationsBuilder_ == null) {
        return errandMedications_.size();
      } else {
        return errandMedicationsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public ErrandMedication getErrandMedications(int index) {
      if (errandMedicationsBuilder_ == null) {
        return errandMedications_.get(index);
      } else {
        return errandMedicationsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder setErrandMedications(
        int index, ErrandMedication value) {
      if (errandMedicationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrandMedicationsIsMutable();
        errandMedications_.set(index, value);
        onChanged();
      } else {
        errandMedicationsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder setErrandMedications(
        int index, ErrandMedication.Builder builderForValue) {
      if (errandMedicationsBuilder_ == null) {
        ensureErrandMedicationsIsMutable();
        errandMedications_.set(index, builderForValue.build());
        onChanged();
      } else {
        errandMedicationsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder addErrandMedications(ErrandMedication value) {
      if (errandMedicationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrandMedicationsIsMutable();
        errandMedications_.add(value);
        onChanged();
      } else {
        errandMedicationsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder addErrandMedications(
        int index, ErrandMedication value) {
      if (errandMedicationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrandMedicationsIsMutable();
        errandMedications_.add(index, value);
        onChanged();
      } else {
        errandMedicationsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder addErrandMedications(
        ErrandMedication.Builder builderForValue) {
      if (errandMedicationsBuilder_ == null) {
        ensureErrandMedicationsIsMutable();
        errandMedications_.add(builderForValue.build());
        onChanged();
      } else {
        errandMedicationsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder addErrandMedications(
        int index, ErrandMedication.Builder builderForValue) {
      if (errandMedicationsBuilder_ == null) {
        ensureErrandMedicationsIsMutable();
        errandMedications_.add(index, builderForValue.build());
        onChanged();
      } else {
        errandMedicationsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder addAllErrandMedications(
        java.lang.Iterable<? extends ErrandMedication> values) {
      if (errandMedicationsBuilder_ == null) {
        ensureErrandMedicationsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, errandMedications_);
        onChanged();
      } else {
        errandMedicationsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder clearErrandMedications() {
      if (errandMedicationsBuilder_ == null) {
        errandMedications_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        errandMedicationsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public Builder removeErrandMedications(int index) {
      if (errandMedicationsBuilder_ == null) {
        ensureErrandMedicationsIsMutable();
        errandMedications_.remove(index);
        onChanged();
      } else {
        errandMedicationsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public ErrandMedication.Builder getErrandMedicationsBuilder(
        int index) {
      return getErrandMedicationsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public ErrandMedicationOrBuilder getErrandMedicationsOrBuilder(
        int index) {
      if (errandMedicationsBuilder_ == null) {
        return errandMedications_.get(index);  } else {
        return errandMedicationsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public java.util.List<? extends ErrandMedicationOrBuilder> 
         getErrandMedicationsOrBuilderList() {
      if (errandMedicationsBuilder_ != null) {
        return errandMedicationsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(errandMedications_);
      }
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public ErrandMedication.Builder addErrandMedicationsBuilder() {
      return getErrandMedicationsFieldBuilder().addBuilder(
          ErrandMedication.getDefaultInstance());
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public ErrandMedication.Builder addErrandMedicationsBuilder(
        int index) {
      return getErrandMedicationsFieldBuilder().addBuilder(
          index, ErrandMedication.getDefaultInstance());
    }
    /**
     * <code>repeated .ErrandMedication errandMedications = 1;</code>
     */
    public java.util.List<ErrandMedication.Builder> 
         getErrandMedicationsBuilderList() {
      return getErrandMedicationsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        ErrandMedication, ErrandMedication.Builder, ErrandMedicationOrBuilder> 
        getErrandMedicationsFieldBuilder() {
      if (errandMedicationsBuilder_ == null) {
        errandMedicationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            ErrandMedication, ErrandMedication.Builder, ErrandMedicationOrBuilder>(
                errandMedications_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        errandMedications_ = null;
      }
      return errandMedicationsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:ErrandMedicationRequest)
  }

  // @@protoc_insertion_point(class_scope:ErrandMedicationRequest)
  private static final ErrandMedicationRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ErrandMedicationRequest();
  }

  public static ErrandMedicationRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ErrandMedicationRequest>
      PARSER = new com.google.protobuf.AbstractParser<ErrandMedicationRequest>() {
    public ErrandMedicationRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ErrandMedicationRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ErrandMedicationRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ErrandMedicationRequest> getParserForType() {
    return PARSER;
  }

  public ErrandMedicationRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
