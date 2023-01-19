package com.toy.hsmall.userinfo.model;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static java.util.regex.Pattern.matches;

@Getter
public class Email {
    private final String address;

    public Email(String address) {
        checkArgument(isNotEmpty(address), "address must be provided");
        checkArgument(
                address.length() >= 4 && address.length() <= 50,
                "address length must be between 4 and 50 characters"
        );
        checkArgument(checkAddress(address), "Invalid email address: " + address);

        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", address);
    }
}
