# Unit Converter - Spring Boot Implementation Plan

## Overview
A Spring Boot web application with three pages for unit conversion: Length, Weight, and Temperature. Uses Thymeleaf for server-side rendering with form submission back to the same page.

## Project Structure

```
unit-converter/
├── pom.xml
└── src/
    └── main/
        ├── java/com/unitconverter/
        │   ├── UnitConverterApplication.java
        │   ├── controller/
        │   │   └── ConverterController.java
        │   └── service/
        │       └── ConversionService.java
        └── resources/
            ├── application.properties
            └── templates/
                ├── index.html
                ├── length.html
                ├── weight.html
                └── temperature.html
```

## Tech Stack
- Spring Boot 3.x
- Thymeleaf (server-side rendering)
- Maven
- Vanilla CSS with premium dark design

## Pages
1. **index.html** - Landing/home page with links to each converter
2. **length.html** - Length conversion (mm, cm, m, km, in, ft, yd, mi)
3. **weight.html** - Weight conversion (mg, g, kg, oz, lb)
4. **temperature.html** - Temperature conversion (°C, °F, K)
