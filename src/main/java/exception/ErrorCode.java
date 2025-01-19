package exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements  CodeInterface {
    FailedToCreateMongoTemplate(0, "failed to create mongo template"),
    NotSupportedOrderRequest(1, "order request failed"),
    FailedToFindTemplate(2, "failed to find template");

    private final Integer code;
    private String message;


    @Override
    public void setMessage(String message) {
        this.message = this.message + message;
    }
}
