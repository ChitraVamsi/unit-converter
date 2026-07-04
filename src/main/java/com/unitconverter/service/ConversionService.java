package com.unitconverter.service;

import org.springframework.stereotype.Service;

/**
 * Service class that handles all unit conversion calculations.
 * Each unit type is converted via a base unit (SI units where applicable).
 */
@Service
public class ConversionService {

    // ─────────────────────────────────────────────────────────────
    //  LENGTH CONVERSION
    //  Base unit: meter (m)
    // ─────────────────────────────────────────────────────────────

    /**
     * Converts a length value from one unit to another.
     *
     * @param value    the numeric value to convert
     * @param fromUnit the source unit (e.g. "mm", "cm", "m", etc.)
     * @param toUnit   the target unit
     * @return the converted value
     */
    public double convertLength(double value, String fromUnit, String toUnit) {
        // Convert input → meters
        double meters = toMeters(value, fromUnit);
        // Convert meters → target unit
        return fromMeters(meters, toUnit);
    }

    /** Returns the factor needed to convert the given unit to meters. */
    private double toMeters(double value, String unit) {
        return switch (unit.toLowerCase()) {
            case "mm"   -> value * 0.001;
            case "cm"   -> value * 0.01;
            case "m"    -> value;
            case "km"   -> value * 1_000.0;
            case "inch" -> value * 0.0254;
            case "ft"   -> value * 0.3048;
            case "yd"   -> value * 0.9144;
            case "mi"   -> value * 1_609.344;
            default     -> throw new IllegalArgumentException("Unknown length unit: " + unit);
        };
    }

    /** Converts a value expressed in meters to the given unit. */
    private double fromMeters(double meters, String unit) {
        return switch (unit.toLowerCase()) {
            case "mm"   -> meters / 0.001;
            case "cm"   -> meters / 0.01;
            case "m"    -> meters;
            case "km"   -> meters / 1_000.0;
            case "inch" -> meters / 0.0254;
            case "ft"   -> meters / 0.3048;
            case "yd"   -> meters / 0.9144;
            case "mi"   -> meters / 1_609.344;
            default     -> throw new IllegalArgumentException("Unknown length unit: " + unit);
        };
    }

    // ─────────────────────────────────────────────────────────────
    //  WEIGHT CONVERSION
    //  Base unit: gram (g)
    // ─────────────────────────────────────────────────────────────

    /**
     * Converts a weight value from one unit to another.
     *
     * @param value    the numeric value to convert
     * @param fromUnit the source unit (e.g. "mg", "g", "kg", "oz", "lb")
     * @param toUnit   the target unit
     * @return the converted value
     */
    public double convertWeight(double value, String fromUnit, String toUnit) {
        double grams = toGrams(value, fromUnit);
        return fromGrams(grams, toUnit);
    }

    /** Returns the equivalent value in grams. */
    private double toGrams(double value, String unit) {
        return switch (unit.toLowerCase()) {
            case "mg" -> value * 0.001;
            case "g"  -> value;
            case "kg" -> value * 1_000.0;
            case "oz" -> value * 28.349523125;
            case "lb" -> value * 453.59237;
            default   -> throw new IllegalArgumentException("Unknown weight unit: " + unit);
        };
    }

    /** Converts grams to the given unit. */
    private double fromGrams(double grams, String unit) {
        return switch (unit.toLowerCase()) {
            case "mg" -> grams / 0.001;
            case "g"  -> grams;
            case "kg" -> grams / 1_000.0;
            case "oz" -> grams / 28.349523125;
            case "lb" -> grams / 453.59237;
            default   -> throw new IllegalArgumentException("Unknown weight unit: " + unit);
        };
    }

    // ─────────────────────────────────────────────────────────────
    //  TEMPERATURE CONVERSION
    //  Celsius is used as the intermediate value.
    // ─────────────────────────────────────────────────────────────

    /**
     * Converts a temperature value from one scale to another.
     *
     * @param value    the numeric value to convert
     * @param fromUnit the source unit: "celsius", "fahrenheit", or "kelvin"
     * @param toUnit   the target unit
     * @return the converted value
     */
    public double convertTemperature(double value, String fromUnit, String toUnit) {
        // Convert input → Celsius
        double celsius = toCelsius(value, fromUnit);
        // Convert Celsius → target unit
        return fromCelsius(celsius, toUnit);
    }

    /** Converts any temperature unit to Celsius. */
    private double toCelsius(double value, String unit) {
        return switch (unit.toLowerCase()) {
            case "celsius"    -> value;
            case "fahrenheit" -> (value - 32.0) * 5.0 / 9.0;
            case "kelvin"     -> value - 273.15;
            default           -> throw new IllegalArgumentException("Unknown temperature unit: " + unit);
        };
    }

    /** Converts Celsius to the given temperature unit. */
    private double fromCelsius(double celsius, String unit) {
        return switch (unit.toLowerCase()) {
            case "celsius"    -> celsius;
            case "fahrenheit" -> celsius * 9.0 / 5.0 + 32.0;
            case "kelvin"     -> celsius + 273.15;
            default           -> throw new IllegalArgumentException("Unknown temperature unit: " + unit);
        };
    }
}
