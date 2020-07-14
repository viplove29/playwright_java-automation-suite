package com.vertafore.test.services.underwriters;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.xmpbox.type.GUIDType;

public class Underwriters {
    @JsonProperty("companyCode")
    private String companyCode;

    @JsonProperty("underwriterId")
    private GUIDType underwriterId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("companyAddressId")
    private GUIDType companyAddressId;

    public Underwriters() {}

}
