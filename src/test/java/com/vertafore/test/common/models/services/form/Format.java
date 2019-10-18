package com.vertafore.test.common.models.services.form;

import com.vertafore.test.common.models.StateProvinceV1;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import net.serenitybdd.core.exceptions.SerenityManagedException;

public enum Format {
  ZIP_CODE,
  PHONE,
  TRUNCATE_DECIMALS,
  ROUND_TO_TWO_DECIMALS,
  SHORT_DATE_US,
  YES_NO_ABBREVIATION,
  STATE_ABBREVIATION;

  public static String formatValue(String value, Format format) {

    if (format == null || value == null) {
      return value;
    }

    switch (format) {
      case TRUNCATE_DECIMALS:
        try {
          DecimalFormat decimalFormat = new DecimalFormat("#");
          decimalFormat.setRoundingMode(RoundingMode.DOWN);
          return decimalFormat.format(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
          throw new SerenityManagedException("Could not be parsed using format: " + format, ex);
        }

      case ROUND_TO_TWO_DECIMALS:
        try {
          DecimalFormat decimalFormat2 = new DecimalFormat("#.00");
          decimalFormat2.setRoundingMode(RoundingMode.DOWN);
          return decimalFormat2.format(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
          throw new SerenityManagedException("Could not be parsed using format: " + format, ex);
        }

      case YES_NO_ABBREVIATION:
        return value.equalsIgnoreCase("true") ? "Y" : "N";

      case SHORT_DATE_US:
        DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.US);

        try {
          ZonedDateTime dateTime = ZonedDateTime.parse(value);
          return dateTime.format(dateTimeFormatter);
        } catch (DateTimeParseException e) {
          try {
            // for dates that don't include time
            return dateTimeFormatter.format(LocalDate.parse(value));
          } catch (DateTimeParseException ex) {
            throw new SerenityManagedException("Could not be parsed using format: " + format, ex);
          }
        }

      case PHONE:
        String phoneNumber = value.replaceAll("[^0-9.]", ""); // removes all non-numeric characters
        if (phoneNumber.length() >= 10) {
          String area = phoneNumber.substring(0, 3);
          String prefix = phoneNumber.substring(3, 6);
          String suffix = phoneNumber.substring(6, 10);
          String ext = "";

          if (phoneNumber.length() > 10) {
            ext = phoneNumber.substring(10);
          }

          if (!ext.isEmpty()) {
            return String.format("(%s) %s-%s Ext.%s", area, prefix, suffix, ext);
          } else {
            return String.format("(%s) %s-%s", area, prefix, suffix);
          }
        } else {
          throw new SerenityManagedException(
              "Could not be parsed using format: " + format, new RuntimeException());
        }

      case ZIP_CODE:
        String numericValue = value.replaceAll("[^0-9.]", ""); // removes all non-numeric characters
        if (numericValue.length() == 9) {
          return numericValue.substring(0, 5) + "-" + numericValue.substring(5);
        } else if (numericValue.length() == 5) {
          return numericValue;
        } else {
          throw new SerenityManagedException(
              "Could not be parsed using format: " + format, new RuntimeException());
        }

      case STATE_ABBREVIATION:
        try {
          return StateProvinceV1.getAbbreviation(StateProvinceV1.valueOf(value));
        } catch (IllegalArgumentException ex) {
          return value;
        }
    }

    throw new SerenityManagedException(
        "Could not be parsed using format: " + format, new RuntimeException());
  }
}
