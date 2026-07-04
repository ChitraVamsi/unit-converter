package com.unitconverter.controller;

import com.unitconverter.service.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * MVC Controller that maps HTTP requests to Thymeleaf template views.
 * Each converter page handles both GET (show form) and POST (process & show result).
 */
@Controller
public class ConverterController {

    private final ConversionService conversionService;

    public ConverterController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    // ─────────────────────────────────────────────────────────────
    //  HOME
    // ─────────────────────────────────────────────────────────────

    /** Displays the landing / home page. */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // ─────────────────────────────────────────────────────────────
    //  LENGTH
    // ─────────────────────────────────────────────────────────────

    /** Shows the length converter form (empty). */
    @GetMapping("/length")
    public String lengthForm() {
        return "length";
    }

    /**
     * Processes the length conversion form submission and re-renders the
     * page with the result so the user stays on the same URL (target="_self").
     */
    @PostMapping("/length")
    public String convertLength(
            @RequestParam(value = "value",    required = false) String valueStr,
            @RequestParam(value = "fromUnit", required = false) String fromUnit,
            @RequestParam(value = "toUnit",   required = false) String toUnit,
            Model model) {

        model.addAttribute("value",    valueStr);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit",   toUnit);

        try {
            if (valueStr == null || valueStr.isBlank()) {
                model.addAttribute("error", "Please enter a value to convert.");
                return "length";
            }
            double input = Double.parseDouble(valueStr.trim());
            double result = conversionService.convertLength(input, fromUnit, toUnit);
            model.addAttribute("result", formatResult(result));
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid number. Please enter a valid numeric value.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "length";
    }

    // ─────────────────────────────────────────────────────────────
    //  WEIGHT
    // ─────────────────────────────────────────────────────────────

    /** Shows the weight converter form (empty). */
    @GetMapping("/weight")
    public String weightForm() {
        return "weight";
    }

    /**
     * Processes the weight conversion form submission and re-renders the page
     * with the result.
     */
    @PostMapping("/weight")
    public String convertWeight(
            @RequestParam(value = "value",    required = false) String valueStr,
            @RequestParam(value = "fromUnit", required = false) String fromUnit,
            @RequestParam(value = "toUnit",   required = false) String toUnit,
            Model model) {

        model.addAttribute("value",    valueStr);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit",   toUnit);

        try {
            if (valueStr == null || valueStr.isBlank()) {
                model.addAttribute("error", "Please enter a value to convert.");
                return "weight";
            }
            double input = Double.parseDouble(valueStr.trim());
            double result = conversionService.convertWeight(input, fromUnit, toUnit);
            model.addAttribute("result", formatResult(result));
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid number. Please enter a valid numeric value.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "weight";
    }

    // ─────────────────────────────────────────────────────────────
    //  TEMPERATURE
    // ─────────────────────────────────────────────────────────────

    /** Shows the temperature converter form (empty). */
    @GetMapping("/temperature")
    public String temperatureForm() {
        return "temperature";
    }

    /**
     * Processes the temperature conversion form submission and re-renders
     * the page with the result.
     */
    @PostMapping("/temperature")
    public String convertTemperature(
            @RequestParam(value = "value",    required = false) String valueStr,
            @RequestParam(value = "fromUnit", required = false) String fromUnit,
            @RequestParam(value = "toUnit",   required = false) String toUnit,
            Model model) {

        model.addAttribute("value",    valueStr);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit",   toUnit);

        try {
            if (valueStr == null || valueStr.isBlank()) {
                model.addAttribute("error", "Please enter a value to convert.");
                return "temperature";
            }
            double input = Double.parseDouble(valueStr.trim());
            double result = conversionService.convertTemperature(input, fromUnit, toUnit);
            model.addAttribute("result", formatResult(result));
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid number. Please enter a valid numeric value.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "temperature";
    }

    // ─────────────────────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Formats the result to up to 8 significant decimal places,
     * stripping unnecessary trailing zeros.
     */
    private String formatResult(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "N/A";
        }
        // Use scientific notation for very large or very small numbers
        if (Math.abs(value) > 1e12 || (Math.abs(value) < 1e-6 && value != 0)) {
            return String.format("%.6e", value);
        }
        // Strip trailing zeros after the decimal
        String formatted = String.format("%.8f", value);
        formatted = formatted.replaceAll("0*$", "");
        formatted = formatted.replaceAll("\\.$", "");
        return formatted;
    }
}
