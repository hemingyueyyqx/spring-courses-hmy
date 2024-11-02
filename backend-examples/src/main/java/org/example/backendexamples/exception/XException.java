package org.example.backendexamples.exception;

import lombok.*;
import org.example.backendexamples.exception.Code;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XException extends RuntimeException{
    private Code code;
    private int number;
    private String message;
}
