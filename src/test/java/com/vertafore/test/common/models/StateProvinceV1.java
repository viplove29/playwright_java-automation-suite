package com.vertafore.test.common.models;

import java.util.HashMap;
import java.util.Map;

public enum StateProvinceV1 {
  ALABAMA,
  ALASKA,
  ARIZONA,
  ARKANSAS,
  CALIFORNIA,
  COLORADO,
  CONNECTICUT,
  DELAWARE,
  DISTRICT_OF_COLUMBIA,
  FLORIDA,
  GEORGIA,
  HAWAII,
  IDAHO,
  ILLINOIS,
  INDIANA,
  IOWA,
  KANSAS,
  KENTUCKY,
  LOUISIANA,
  MAINE,
  MARYLAND,
  MASSACHUSETTS,
  MICHIGAN,
  MINNESOTA,
  MISSISSIPPI,
  MISSOURI,
  MONTANA,
  NEBRASKA,
  NEVADA,
  NEW_HAMPSHIRE,
  NEW_JERSEY,
  NEW_MEXICO,
  NEW_YORK,
  NORTH_CAROLINA,
  NORTH_DAKOTA,
  OHIO,
  OKLAHOMA,
  OREGON,
  PENNSYLVANIA,
  RHODE_ISLAND,
  SOUTH_CAROLINA,
  SOUTH_DAKOTA,
  TENNESSEE,
  TEXAS,
  UTAH,
  VERMONT,
  VIRGINIA,
  WASHINGTON,
  WEST_VIRGINIA,
  WISCONSIN,
  WYOMING,
  AMERICAN_SAMOA,
  GUAM,
  MARSHALL_ISLANDS,
  MICRONESIA,
  NORTHERN_MARIANAS,
  PALAU,
  PUERTO_RICO,
  VIRGIN_ISLANDS,
  ARMED_FORCES_AFRICA,
  ARMED_FORCES_AMERICAS,
  ARMED_FORCES_CANADA,
  ARMED_FORCES_EUROPE,
  ARMED_FORCES_MIDDLE_EAST,
  ARMED_FORCES_PACIFIC;

  private static final Map<StateProvinceV1, String> abbreviationLookupMap = new HashMap<>();

  static {
    abbreviationLookupMap.put(StateProvinceV1.ALABAMA, "AL");
    abbreviationLookupMap.put(StateProvinceV1.ALASKA, "AK");
    abbreviationLookupMap.put(StateProvinceV1.ARIZONA, "AZ");
    abbreviationLookupMap.put(StateProvinceV1.ARKANSAS, "AR");
    abbreviationLookupMap.put(StateProvinceV1.CALIFORNIA, "CA");
    abbreviationLookupMap.put(StateProvinceV1.COLORADO, "CO");
    abbreviationLookupMap.put(StateProvinceV1.CONNECTICUT, "CT");
    abbreviationLookupMap.put(StateProvinceV1.DELAWARE, "DE");
    abbreviationLookupMap.put(StateProvinceV1.DISTRICT_OF_COLUMBIA, "DC");
    abbreviationLookupMap.put(StateProvinceV1.FLORIDA, "FL");
    abbreviationLookupMap.put(StateProvinceV1.GEORGIA, "GA");
    abbreviationLookupMap.put(StateProvinceV1.HAWAII, "HI");
    abbreviationLookupMap.put(StateProvinceV1.IDAHO, "ID");
    abbreviationLookupMap.put(StateProvinceV1.ILLINOIS, "IL");
    abbreviationLookupMap.put(StateProvinceV1.INDIANA, "IN");
    abbreviationLookupMap.put(StateProvinceV1.IOWA, "IA");
    abbreviationLookupMap.put(StateProvinceV1.KANSAS, "KS");
    abbreviationLookupMap.put(StateProvinceV1.KENTUCKY, "KY");
    abbreviationLookupMap.put(StateProvinceV1.LOUISIANA, "LA");
    abbreviationLookupMap.put(StateProvinceV1.MAINE, "ME");
    abbreviationLookupMap.put(StateProvinceV1.MARYLAND, "MD");
    abbreviationLookupMap.put(StateProvinceV1.MASSACHUSETTS, "MA");
    abbreviationLookupMap.put(StateProvinceV1.MICHIGAN, "MI");
    abbreviationLookupMap.put(StateProvinceV1.MINNESOTA, "MN");
    abbreviationLookupMap.put(StateProvinceV1.MISSISSIPPI, "MS");
    abbreviationLookupMap.put(StateProvinceV1.MISSOURI, "MO");
    abbreviationLookupMap.put(StateProvinceV1.MONTANA, "MT");
    abbreviationLookupMap.put(StateProvinceV1.NEBRASKA, "NE");
    abbreviationLookupMap.put(StateProvinceV1.NEVADA, "NV");
    abbreviationLookupMap.put(StateProvinceV1.NEW_HAMPSHIRE, "NH");
    abbreviationLookupMap.put(StateProvinceV1.NEW_JERSEY, "NJ");
    abbreviationLookupMap.put(StateProvinceV1.NEW_MEXICO, "NM");
    abbreviationLookupMap.put(StateProvinceV1.NEW_YORK, "NY");
    abbreviationLookupMap.put(StateProvinceV1.NORTH_CAROLINA, "NC");
    abbreviationLookupMap.put(StateProvinceV1.NORTH_DAKOTA, "ND");
    abbreviationLookupMap.put(StateProvinceV1.OHIO, "OH");
    abbreviationLookupMap.put(StateProvinceV1.OKLAHOMA, "OK");
    abbreviationLookupMap.put(StateProvinceV1.OREGON, "OR");
    abbreviationLookupMap.put(StateProvinceV1.PENNSYLVANIA, "PA");
    abbreviationLookupMap.put(StateProvinceV1.RHODE_ISLAND, "RI");
    abbreviationLookupMap.put(StateProvinceV1.SOUTH_CAROLINA, "SC");
    abbreviationLookupMap.put(StateProvinceV1.SOUTH_DAKOTA, "SD");
    abbreviationLookupMap.put(StateProvinceV1.TENNESSEE, "TN");
    abbreviationLookupMap.put(StateProvinceV1.TEXAS, "TX");
    abbreviationLookupMap.put(StateProvinceV1.UTAH, "UT");
    abbreviationLookupMap.put(StateProvinceV1.VERMONT, "VT");
    abbreviationLookupMap.put(StateProvinceV1.VIRGINIA, "VA");
    abbreviationLookupMap.put(StateProvinceV1.WASHINGTON, "WA");
    abbreviationLookupMap.put(StateProvinceV1.WEST_VIRGINIA, "WV");
    abbreviationLookupMap.put(StateProvinceV1.WISCONSIN, "WI");
    abbreviationLookupMap.put(StateProvinceV1.WYOMING, "WY");
    abbreviationLookupMap.put(StateProvinceV1.AMERICAN_SAMOA, "AS");
    abbreviationLookupMap.put(StateProvinceV1.GUAM, "GU");
    abbreviationLookupMap.put(StateProvinceV1.MARSHALL_ISLANDS, "MH");
    abbreviationLookupMap.put(StateProvinceV1.MICRONESIA, "FM");
    abbreviationLookupMap.put(StateProvinceV1.NORTHERN_MARIANAS, "MP");
    abbreviationLookupMap.put(StateProvinceV1.PALAU, "PU");
    abbreviationLookupMap.put(StateProvinceV1.PUERTO_RICO, "PR");
    abbreviationLookupMap.put(StateProvinceV1.VIRGIN_ISLANDS, "VI");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_AFRICA, "AE");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_AMERICAS, "AA");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_CANADA, "AE");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_EUROPE, "AE");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_MIDDLE_EAST, "AE");
    abbreviationLookupMap.put(StateProvinceV1.ARMED_FORCES_PACIFIC, "AP");
  }

  public static String getAbbreviation(Enum stateProvince) {
    return abbreviationLookupMap.get(stateProvince);
  }
}
