package isa.apoteka.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: ErrandService.proto")
public final class ErrandServiceGrpc {

  private ErrandServiceGrpc() {}

  public static final String SERVICE_NAME = "ErrandService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ErrandRequest,
      ErrandResponse> METHOD_NEW_ERRAND =
      io.grpc.MethodDescriptor.<ErrandRequest, ErrandResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ErrandService", "newErrand"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ErrandRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ErrandResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ErrandMedicationRequest,
      ErrandMedicationResponse> METHOD_ERRAND_MEDICATION =
      io.grpc.MethodDescriptor.<ErrandMedicationRequest, ErrandMedicationResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ErrandService", "errandMedication"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ErrandMedicationRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ErrandMedicationResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ErrandServiceStub newStub(io.grpc.Channel channel) {
    return new ErrandServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ErrandServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ErrandServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ErrandServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ErrandServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ErrandServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void newErrand(ErrandRequest request,
        io.grpc.stub.StreamObserver<ErrandResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_NEW_ERRAND, responseObserver);
    }

    /**
     */
    public void errandMedication(ErrandMedicationRequest request,
        io.grpc.stub.StreamObserver<ErrandMedicationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ERRAND_MEDICATION, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_NEW_ERRAND,
            asyncUnaryCall(
              new MethodHandlers<
                ErrandRequest,
                ErrandResponse>(
                  this, METHODID_NEW_ERRAND)))
          .addMethod(
            METHOD_ERRAND_MEDICATION,
            asyncUnaryCall(
              new MethodHandlers<
                ErrandMedicationRequest,
                ErrandMedicationResponse>(
                  this, METHODID_ERRAND_MEDICATION)))
          .build();
    }
  }

  /**
   */
  public static final class ErrandServiceStub extends io.grpc.stub.AbstractStub<ErrandServiceStub> {
    private ErrandServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ErrandServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ErrandServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ErrandServiceStub(channel, callOptions);
    }

    /**
     */
    public void newErrand(ErrandRequest request,
        io.grpc.stub.StreamObserver<ErrandResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_NEW_ERRAND, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void errandMedication(ErrandMedicationRequest request,
        io.grpc.stub.StreamObserver<ErrandMedicationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ERRAND_MEDICATION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ErrandServiceBlockingStub extends io.grpc.stub.AbstractStub<ErrandServiceBlockingStub> {
    private ErrandServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ErrandServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ErrandServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ErrandServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ErrandResponse newErrand(ErrandRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_NEW_ERRAND, getCallOptions(), request);
    }

    /**
     */
    public ErrandMedicationResponse errandMedication(ErrandMedicationRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ERRAND_MEDICATION, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ErrandServiceFutureStub extends io.grpc.stub.AbstractStub<ErrandServiceFutureStub> {
    private ErrandServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ErrandServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ErrandServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ErrandServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ErrandResponse> newErrand(
        ErrandRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_NEW_ERRAND, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ErrandMedicationResponse> errandMedication(
        ErrandMedicationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ERRAND_MEDICATION, getCallOptions()), request);
    }
  }

  private static final int METHODID_NEW_ERRAND = 0;
  private static final int METHODID_ERRAND_MEDICATION = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ErrandServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ErrandServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEW_ERRAND:
          serviceImpl.newErrand((ErrandRequest) request,
              (io.grpc.stub.StreamObserver<ErrandResponse>) responseObserver);
          break;
        case METHODID_ERRAND_MEDICATION:
          serviceImpl.errandMedication((ErrandMedicationRequest) request,
              (io.grpc.stub.StreamObserver<ErrandMedicationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ErrandServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ErrandServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ErrandServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ErrandServiceDescriptorSupplier())
              .addMethod(METHOD_NEW_ERRAND)
              .addMethod(METHOD_ERRAND_MEDICATION)
              .build();
        }
      }
    }
    return result;
  }
}
