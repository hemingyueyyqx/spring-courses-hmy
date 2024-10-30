package org.example.springmvcexampleshmy.exception;

import lombok.*;

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
