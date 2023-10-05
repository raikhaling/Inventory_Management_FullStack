package com.amnil.invbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

/**
 * The type String response with links.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StringResponseWithLinks extends RepresentationModel<StringResponseWithLinks> {

    /**
     * message
     */
    private String message;

}
